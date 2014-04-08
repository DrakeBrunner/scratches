#!/usr/bin/perl

use strict;
use warnings;

# Used to get week number
use Time::Piece;
use Getopt::Long;

use 5.010;

# Take a look at the log (messages, sshd) for a certain week.
# This script assumes that the logs are rotated weekly, and looks at
# /var/log/messages.weekly/ for messages,
# /var/log/sshd/ for sshd logs.
#
# You can get the file name of the log file with:
#
# ./view_log.pl LOG_TYPE [WEEK_NUM] [YEAR]
#
# The current week number and year will be used when not specified.
#
# This is a quick-and-dirty implementation.

# vim: expandtab sw=4 ts=4 tw=78 foldmethod=marker

# Constants
my $MESSAGES_LOCATION = "/var/log/messages.weekly";
my $SSHD_LOCATION = "/var/log/sshd";

my $TYPE_MESSAGES = "mes";
my $TYPE_SSHD = "sshd";
my $TYPE_INVALID = "invalid";

my $show_help = 0;
GetOptions(
    "help|h" => \$show_help,
);
if ($show_help) {
    print_help();
    exit;
}

if (@ARGV) {
    has_arg(@ARGV);
}
else {
    has_no_arg(shift);
}

sub has_arg {
    my $log_type = shift;
    my ($year, $week_num) = parse_year_week(@_);

    $week_num = Time::Piece::localtime->week if not defined $week_num;
    $year = Time::Piece::localtime->year if not defined $year;

    print get_file_name(parse_log_type($log_type), $year, $week_num);
}

# If there is no argument passed to the script
sub has_no_arg {
    # Prompt for what to check if not given by ARGV
    print "What log do you want to check? ";
    chomp(my $log_type = <STDIN>);

    $log_type = parse_log_type($log_type);

    given ($log_type) {
        when ($_ eq $TYPE_SSHD) {
            view_sshd();
        }
        when ($_ eq $TYPE_MESSAGES) {
            view_messages();
        }
        # Invalid log type
        default {
            exit;
        }
    }
}

# Checks whether the given log type (messages, sshd, etc) is valid and if not,
# prints a help message. Returns the log type that can be interpreted in the
# program.
sub parse_log_type { #{{{
    my $log_type = shift;

    given ($log_type) {
        when (/mes(sages)?/) {
            return $TYPE_MESSAGES;
        }
        when (/ssh(d)?/) {
            return $TYPE_SSHD;
        }
        default {
            print_help();
            return $TYPE_INVALID;
        }
    }
} #}}}

sub print_help { #{{{
    print <<EOF;
Usage:
    $0 [LOG_TYPE] [WEEK_NUM] [YEAR]

    When no argument is given, the program will prompt the user for the type
    of log and the week. The program will print the file name when no
    argument is given. If a valid log type is given as an argument, but no
    week number is specified, the program will give the file name of the log
    file. This is useful when you want to pass the file name to a text editor
    like vim.

        sudo vim `$0 ssh`

    to open the sshd log in Vim.

Types of log
    The following types of log are currently supported:

    messages:   Type "mes(sages)"
    sshd:       Type "ssh(d)"
EOF
} #}}}

# Displays a help message and prompts the user to specify the week number and
# year.
sub get_year_week { #{{{
    print <<EOF;
You can specify the year by separating by space. For example,
    "2014 5" is week 5 of 2014
    "14 5" is the same as the above (interprets as 20XX)
    "this", any other one word, or empty input means this week
    "last" means last week
    "-2" means 2 weeks before
Enter week number:
EOF

    chomp(my $input = <STDIN>);
    return parse_year_week($input);
} #}}}

# Parses the given input into the year and the week number.
# See the help message in get_year_week() subroutine for details.
sub parse_year_week { #{{{
    # This subroutine is used by 2 other subroutines: get_year_week() and
    # has_arg(). get_year_week() passes a string whereas has_arg() passes a
    # list.
    my $input = join " ", @_;
    my ($year, $week_num);

    # 1 word (or number)
    if (scalar(split " ", $input) == 1) {
        my $t = Time::Piece::localtime;
        $year = $t->year;
        $week_num = $t->week;

        my $subtract_week = 0;
        if ($input eq "this") {
            # Do nothing
        }
        # If 'last' or '-N' was entered
        elsif ($input eq "last") {
            $subtract_week = 1;
        }
        elsif ($input < 0) {
            $subtract_week = abs $input;
        }
        # Set week number to input if it's a positive number
        else {
            $week_num = $input;
        }

        # Subtract from current week number
        if ($week_num - $subtract_week < 1) {
            $year--;
            # wk52 --> wk53 --> wk1 --> wk2
            $week_num = 53 + ($week_num - $subtract_week);
        }
        else {
            $week_num -= $subtract_week;
        }
    }
    # 2 numbers
    elsif (scalar(split " ", $input) == 2) {
        ($year, $week_num) = split " ", $input;
        # Make it 20XX if only the last 2 digits are entered
        $year += 2000 if $year < 1000;
    }
    # Empty input
    else {
        my $t = Time::Piece::localtime;
        $year = $t->year;
        $week_num = $t->week;
    }

    return ($year, $week_num);
} #}}}

sub view_sshd { #{{{
    my ($year, $week_num) = get_year_week();
    my $file_name = get_file_name($TYPE_SSHD, $year, $week_num);

    open my $log_fh, $file_name or die "Error opening $file_name: $!";

    # Print everything in file for now
    while (<$log_fh>) {
        print;
    }
} #}}}

sub view_messages { #{{{
    my ($year, $week_num) = get_year_week();
    my $file_name = get_file_name($TYPE_SSHD, $year, $week_num);

    open my $log_fh, $file_name or die "Error opening $file_name: $!";

    # Print everything in file for now
    while (<$log_fh>) {
        print;
    }
} #}}}

# Returns the file name of the log files. This subroutine takes into account
# of the fact that syslog-ng sees the first week of the year as week 0.
sub get_file_name { #{{{
    my $log_type = shift;
    my $year = shift;
    my $week_num = shift;

    # In syslog-ng, first week is week 0
    $week_num--;

    my $file_name = "";
    given ($log_type) {
        when ($_ eq $TYPE_SSHD) {
            $file_name = sprintf "%s/%d.%02d.sshd.log", $SSHD_LOCATION, $year, $week_num;
        }
        when ($_ eq $TYPE_MESSAGES) {
            $file_name = sprintf "%s/%d.week%02d.log", $MESSAGES_LOCATION, $year, $week_num;
        }
        default {
            die "Invalid log type!";
        }
    }

    return $file_name;
} #}}}
