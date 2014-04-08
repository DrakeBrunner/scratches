#!/usr/bin/perl

use strict;
use warnings;


print "This program sums up the number that are multiples of 3 or 5\n";
print "To which number do you want to count? ";
chomp(my $lim = <STDIN>);

my $sum = 0;
for (my $i = 0; 3 * $i < $lim; $i++) {
    $sum = $sum + 3 * $i;
}
for (my $i = 0; 5 * $i < $lim; $i++) {
    $sum = $sum + 5 * $i;
}
# Multiples of 15 will be duplicated so exclude them
for (my $i = 0; 15 * $i < $lim; $i++) {
    $sum = $sum - 15 * $i;
}

print "Result: $sum\n";
