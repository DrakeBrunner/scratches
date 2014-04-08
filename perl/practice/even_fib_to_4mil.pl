#!/usr/bin/perl

use strict;
use warnings;

# Count the sum of even numbers in a
# fibonacci sequence until the term reaches certain number

print "To which number do you want to count?\n=> ";
chomp(my $lim = <STDIN>);

my $sum = 0;
my @seq = (1, 1);

do {
    $seq[2] = $seq[0] + $seq[1];

    $sum = $sum + $seq[2] if ($seq[2] % 2 == 0);

    $seq[0] = $seq[1];
    $seq[1] = $seq[2];
} while ($seq[2] < $lim);

print "$sum\n";
