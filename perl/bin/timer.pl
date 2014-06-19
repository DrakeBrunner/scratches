#!/usr/bin/perl

use strict;
use warnings;

use Time::Piece;

# This is a script that show how many days, hours, minutes, and seconds
# have passed since a certain event_name.
#
# This program uses the file ~/.config/timer.dat
# The format of the file must be:
#
# EventName %m/%d/%y %H:%M:%S
#
# TODO: option to add/remove/reorder events so that no need to manually edit
#
# Author: Naoki Mizuno
# Version: 4.0.0
# Last Updated: March 25th, 2014

my $config_file = "$ENV{HOME}/.config/timer.dat";
open my $config_fh, "$config_file" or die "Could not open config file: $!";
my $current_time = localtime;
my %events;

read_file();
print_all();

# Read data from file and store them into hash
sub read_file {
    # Used to display in the order in the config file
    my $order = 0;

    while (<$config_fh>) {
        chomp(my $line = $_);

        # skip comment lines (empty or starting with #, ", or //)
        next if $line =~ m!^(\s*$|#|"|//)!;

        # Remove event name
        my $event_name = $1 if $line =~ s/^(.*?)\s+//;

        # e.g. 10/15/96 is October 15th, 1996
        my $event = localtime(Time::Piece->strptime($line, "%D %T"));

        $events{$event_name}{order} = $order;
        $events{$event_name}{diff} = $current_time - $event;
        $events{$event_name}{when} = $event;

        $order++;
    }
}

# Print all the data
sub print_all {
    # Current time
    print "Current time: ", $current_time, "\n\n";
    foreach my $event_name (sort { $events{$a}{order} <=> $events{$b}{order} } keys %events) {
        printf "%-20s(%s)\n", $event_name , $events{$event_name}{when};
        print "  ", $events{$event_name}{diff}->pretty, "\n\n";
    }
}
