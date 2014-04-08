#!/usr/bin/perl

use strict;
use warnings;

my @array;

# Generate random array
for my $i (0..9) {
    $array[$i] = int(rand(1000));
}
# Testing array
# @array = qw(863 52 628 888 32 303 607 388 944 91);

# Before sort
print "Before\n", join(" ", @array), "\n";

&quick_sort(\@array, 0, @array - 1);

# After sort
print "After\n", join(" ", @array), "\n";

#### Subroutines ####

# Returns pivot index
# pivot index is the second largest of 3 samples
sub pivot {
    my $array_ref = shift;
    my $left = shift;
    my $right = shift;

    my $mid = int(($left + $right) / 2);

    if ($$array_ref[$left] > $$array_ref[$right]) {
        return $$array_ref[$mid] > $$array_ref[$left] ? $left :
            $$array_ref[$mid] > $$array_ref[$right] ? $mid : $right;
    }
    else {
        return $$array_ref[$mid] > $$array_ref[$right] ? $right :
            $$array_ref[$mid] > $$array_ref[$left] ? $mid : $left;
    }
}

# Sorts value from $left to $right
# Returns the index of next center
sub partition {
    my $array_ref = shift;
    my $left = shift;
    my $right = shift;
    my $pivot_index = shift;

    # swap $pivot_index and $left
    my $tmp = $$array_ref[$left];
    $$array_ref[$left] = $$array_ref[$pivot_index];
    $$array_ref[$pivot_index] = $tmp;

    $pivot_index = $left;

    my $i = $left + 1;
    my $j = $right;

    while (1) {
        $i++ while $i < @$array_ref && $$array_ref[$i] < $$array_ref[$pivot_index];
        $j-- while $j > 0 && $$array_ref[$j] > $$array_ref[$pivot_index];

        last if ($i >= $j);

        # swap $i and $j
        my $tmp = $$array_ref[$i];
        $$array_ref[$i] = $$array_ref[$j];
        $$array_ref[$j] = $tmp;

        $i++;
        $j--;
    }

    # swap j and left
    $tmp = $$array_ref[$j];
    $$array_ref[$j] = $$array_ref[$left];
    $$array_ref[$left] = $tmp;

    return $j;
}

# quick sort
sub quick_sort {
    my $array_ref = shift;
    my $left = shift;
    my $right = shift;

    my @rstack;
    my @lstack;
    my $stack = 1;

    push @lstack, $left;
    push @rstack, $right;

    # return if $left >= $right;
    while ($stack > 0) {

        # pop
        $left = pop @lstack;
        $right = pop @rstack;
        $stack--;

        next if ($right - $left <= 0);

        my $pivot_index = &pivot($array_ref, $left, $right);
        my $center = &partition($array_ref, $left, $right, $pivot_index);

        # Right of center
        if ($center + 1 < $right) {
            push @lstack, $center + 1;
            push @rstack, $right;
            $stack++;
        }
        # Left of center
        if ($center - 1 > $left) {
            push @lstack, $left;
            push @rstack, $center - 1;
            $stack++;
        }
    }   # End of while loop
}
