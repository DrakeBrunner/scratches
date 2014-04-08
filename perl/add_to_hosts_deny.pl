#!/usr/bin/perl

use strict;
use warnings;

use DateTime;

# Run this script with root privilage.
# This script looks at the /var/log/messages file and add IP addresses that
# failed to login via ssh a certain number of times/days and add them to
# /etc/hosts.deny
# The script assumes that the log file is taken weekly to files that are named
# as following:
# /var/log/messages.weekly/{4-digit year}.week{week number}
#
# TODO: Add help
# TODO: Don't show addresses that are already in the hosts files
# TODO: Don't show current week's record after changing week number.
# TODO: Allow week specification suc as "curren" or "1 week ago"

my $just_count = "true";
my $thresh = 1000;

while (1) {

    # Find which file to open. Open the log file for the previous week
    my ($year, $week) = &get_year_week();

# If the user picks his/her own week, program goes back to here
restart:

    # Open log file
    my $log_file = "/var/log/messages.weekly/$year.week$week.log";
    open LOG_FILE, $log_file or die "Failed to open Log File: $!";
    print "Log file: $log_file\n";

    # Sample of failed login
    # Sep  3 05:07:29 nigorojr sshd[4516]: Invalid user anonymous from 222.231.57.68
    # Sep  3 05:07:30 nigorojr sshd[4518]: Invalid user postmaster from 222.231.57.68
    # Sep  3 09:17:59 nigorojr sshd[9743]: Invalid user admin from 189.26.255.11
    # Sep  3 13:12:24 nigorojr sshd[12306]: Invalid user ubnt from 190.29.99.249
    # Sep  3 19:17:58 nigorojr sshd[12836]: Invalid user vyatta from 62.193.218.223
    # Sep  3 23:28:24 nigorojr sshd[13230]: Invalid user guest from 77.68.106.224

    my %invalid_logins;
    my $ip_pattern = qr/\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}/;
    my $user_pattern = qr/Invalid user (.*?) from/;
    while (<LOG_FILE>) {
        my $line = $_;
        my ($ip, $username);
        if ($line =~ m/$ip_pattern/) {
            $ip = $&;
        }
        if ($line =~ m/$user_pattern/) {
            if ($just_count eq "false") {
                $username = $1;

                if (defined $invalid_logins{$ip}{$username}) {
                    $invalid_logins{$ip}{$username} += 1;
                }
                else {
                    $invalid_logins{$ip}{$username} = 1;
                }
            }
            else {
                if (defined $invalid_logins{$ip}) {
                    $invalid_logins{$ip} += 1;
                }
                else {
                    $invalid_logins{$ip} = 1;
                }
            }
        }
    }

    if ($just_count eq "false") {
        foreach my $ip (keys %invalid_logins) {
            print "$ip\n";
            foreach my $name (keys %{ $invalid_logins{$ip} }) {
                printf "%-15s => %5d\n", $name,  $invalid_logins{$ip}{$name};
            }
            print "\n";
        }
    }
    else {
        my $i = 1;
        my $line_drawn = "false";
        foreach my $ip (reverse sort { $invalid_logins{$a} <=> $invalid_logins{$b} } keys %invalid_logins) {
            # Draw line above threshold
            if ($invalid_logins{$ip} < $thresh and $line_drawn ne "true") {
                print "-" for (1..30);
                print "\n";
                $line_drawn = "true";
            }
            printf "%3d %-15s => %5d\n", $i++, $ip, $invalid_logins{$ip};
            # Set default range to threshold, do nothing if non-endl whitespace
            # was entered, else execute default. Add mode for no prompt
        }

        print "\nWhich number(s) do you want to add to /etc/hosts.deny? (hit enter to quit)\n";
        print "Tip: 'w(eek )\$num' will open log file from different week (e.g. week 10)\n> ";
        chomp(my $input = <STDIN>);

        my ($begin, $end);
        # If the user inputs the week
        if ($input =~ m/w(?:eek)?\s*(\d+)/) {
            $week = $1;
            goto restart;
        }
        elsif ($input =~ m/(\d+)\s*,?\s*(\d+)?/) {
            if (defined $1 and $1 ne "") {
                $begin = $1;
            }

            if (defined $2) {
                $end = $2 if defined $2;
                # Go easy on reversed order
                ($end, $begin) = ($begin, $end) if $begin > $end;
            }
        }
        # Exit if invalid selection or no selection
        else {
            print "Thanks for using!\n";
            exit;
        }

        my @addresses = reverse sort { $invalid_logins{$a} <=> $invalid_logins{$b} } keys %invalid_logins;

        # End this loop if there's no invalid logins
        next unless @addresses;

        $end = $begin if not defined $end;
        # Get all the contents to an array
        open HOSTS_READ, "/etc/hosts.deny" or die "$!";
        my @current_hosts;
        while (<HOSTS_READ>) {
            my $line = $_;
            chomp $line;
            # /etc/hosts.deny is written like ALL:102.168.18.35
            $line = substr $line, 4;
            push @current_hosts, $line;
        }
        close HOSTS_READ;

        # Open /etc/hosts.deny
        open HOSTS, ">>/etc/hosts.deny" or die "Couldn't open /etc/hosts.deny file: $!";
        for (my $i = $begin - 1; $i <= $end - 1; $i++) {
            if (not &check_duplicate($addresses[$i], @current_hosts)) {
                print HOSTS "ALL:$addresses[$i]\n";
                print STDOUT "Added $addresses[$i]\n";
            }
            else {
                print STDOUT "  ! Skipped $addresses[$i] because it's already denied\n";
            }
            print "\n";
        }
    }

}   # End of while loop

# Returns the year and week number.
sub get_year_week {
    my $year = DateTime->now->year;
    my $week = DateTime->now->week_number;
    # If it's the first week of the year, use log from the last week of the
    # previous year (DateTime.pm doc says 1..53)
    if ($week == 1) {
        $year -= 1;
        $week = 53;
    }
    else {
        $week -= 1;
    }

    return ($year, $week);
}

# Looks at the passed array and find if there's already that address in the
# hosts file. Returns 1 if there is, 0 if not.
sub check_duplicate {
    my $address = shift;
    my @current_hosts = @_;

    for (my $i = 0; $i < @current_hosts; $i++) {
        return 1 if $address eq $current_hosts[$i];
    }
    return 0;
}
