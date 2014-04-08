#!/usr/bin/perl

use strict;
use warnings;

# This script displays the component of numbers that
# are sorted in a descending order in a product table

my %numbers;

print "Max: ";
chomp(my $max = <STDIN>);

my $count = 0;
for (my $i = 1; $i <= $max; $i++) {
    for (my $j = $i; $j <= $max; $j++) {
        $numbers{$i * $j} = $i;
    }
}

for my $num (sort {$b <=> $a} keys %numbers) {
    printf "%3d = %2d x %d\n", $num, $numbers{$num}, $num / $numbers{$num};
}
