#!/usr/bin/perl

# test swapping array elements using sub routines

use strict;
use warnings;

my @array = 1..5;

print "before\n", join(" ", @array), "\n";

&swap(\@array);

print "after\n", join(" ", @array), "\n";

sub swap {
    my $ref = shift;
    print "\npassed ref: ", $ref, "\n";

    printf "ref[0] = %d\n", $$ref[0];
    printf "ref[4] = %d\n", $$ref[4];
    print "\nswapping [0] and [4]\n\n";
    my $tmp = $$ref[0];
    $$ref[0] = $$ref[4];
    $$ref[4] = $tmp;

    printf "ref[0] = %d\n", $$ref[0];
    printf "ref[4] = %d\n", $$ref[4];

    return;
}
