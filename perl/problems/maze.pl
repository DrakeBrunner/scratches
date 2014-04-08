#!/usr/bin/perl

use strict;
use warnings;
use 5.010;

my $input;
if (@ARGV) {
    $input = $ARGV[0];
}
else {
    print "File: ";
    chomp($input = <STDIN>);
}

open FILE, $input or die "Could not open file: $!";

# Store input file into 2-dimensional array
my @maze;
my ($i, $j);
$i = 0;
my ($start_ref, $goal_ref);
while (<FILE>) {
    chomp(my $line = $_);

    $start_ref = [$i, index($line, "S")] if /S/;
    $goal_ref = [$i, index($line, "G")] if /G/;

    $maze[$i++] = [split //, $line];
}

print "Initial check\n";
&check(\@maze);

# Add number to the array until it reaches goal
my $distance = 1;
while (not($i == $$goal_ref[0] && $j == $$goal_ref[1])) {
    ($i, $j) = &move(\@maze, $i, $j, \$distance);
}

# Subroutines
# Adds number to the array
sub move {
    my $maze_ref = shift;
    my ($i, $j, $distance) = @_;

    # Change it to the distance from start if adjacent is space
    $$maze_ref[$i+1][$j] = $$distance if $$maze_ref[$i+1][$j] eq " ";
    $$maze_ref[$i-1][$j] = $$distance if $$maze_ref[$i-1][$j] eq " ";
    $$maze_ref[$i][$j+1] = $$distance if $$maze_ref[$i][$j+1] eq " ";
    $$maze_ref[$i][$j-1] = $$distance if $$maze_ref[$i][$j-1] eq " ";

    # If "this" distance is shorter than the one already there, change it
    $$maze_ref[$i+1][$j] = $$distance if $$maze_ref[$i+1][$j] =~ /\d+/
        and $$distance < $$maze_ref[$i+1][$j];
    $$maze_ref[$i+1][$j] = $$distance if $$maze_ref[$i-1][$j] =~ /\d+/
        and $$distance < $$maze_ref[$i-1][$j];
    $$maze_ref[$i+1][$j] = $$distance if $$maze_ref[$i][$j+1] =~ /\d+/
        and $$distance < $$maze_ref[$i][$j+1];
    $$maze_ref[$i+1][$j] = $$distance if $$maze_ref[$i][$j-1] =~ /\d+/
        and $$distance < $$maze_ref[$i][$j-1];

    $$distance++;
    return ($i, $j);

}

# Prints out the whole array
sub check {
    my $maze = shift;
    for (my $i = 0; $i < @$maze; $i++) {
        print join("", @{$$maze[$i]}), "\n";
    }
}
