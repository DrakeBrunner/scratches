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

open my $input_fh, $input or die "Could not open file: $!";

# Store input file into 2-dimensional array
my @maze;
my $i = 0;
# Start and goal coordinates
my ($start_ref, $goal_ref);

# Check row-by-row
while (<$input_fh>) {
    chomp(my $line = $_);

    # Assume there is only one S and G in maze
    # [row, col, dist] in array
    $start_ref = [$i, index($line, "S"), 0] if /S/;
    $goal_ref = [$i, index($line, "G")] if /G/;

    $maze[$i++] = [split //, $line];
}

print "Initial check\n";
check(\@maze);

# Do a BFS
my @q;
my @dist;
push @q, $start_ref;

while (scalar @q != 0) {
    my @cur = @{shift @q};
    my $first = shift @cur;
    my $second = shift @cur;
    my $distance = shift @cur;

    unless (defined $dist[$first][$second]
        or $maze[$first][$second] eq "*") {
        # Push adjacent grids to queue
        push @q, [$first - 1, $second, $distance + 1] if $first - 1 >= 0;
        push @q, [$first + 1, $second, $distance + 1] if $first + 1 < @maze;
        push @q, [$first, $second - 1, $distance + 1] if $second - 1 >= 0;
        push @q, [$first, $second + 1, $distance + 1] if $second + 1 < @{$maze[0]};

        $dist[$first][$second] = $distance;
    }

    if ($first == $$goal_ref[0] and $second == $$goal_ref[1]) {
        push @$goal_ref, $distance + 1;
        last;
    }
}

# Mark the path
my $f = $$goal_ref[0];
my $s = $$goal_ref[1];
my $d = $$goal_ref[2];

until ($d == 1) {
    if (defined $dist[$f - 1][$s] and $dist[$f - 1][$s] == $d - 1) {
        $maze[$f - 1][$s] = '$';
        $f--;
    }
    elsif (defined $dist[$f + 1][$s] and $dist[$f + 1][$s] == $d - 1) {
        $maze[$f + 1][$s] = '$';
        $f++;
    }
    elsif (defined $dist[$f][$s - 1] and $dist[$f][$s - 1] == $d - 1) {
        $maze[$f][$s - 1] = '$';
        $s--;
    }
    elsif (defined $dist[$f][$s + 1] and $dist[$f][$s + 1] == $d - 1) {
        $maze[$f][$s + 1] = '$';
        $s++;
    }

    $d--;
}

check(\@maze);

# Prints out the whole array
sub check {
    my $maze = shift;
    for (my $i = 0; $i < @$maze; $i++) {
        print join("", @{$$maze[$i]}), "\n";
    }
}
