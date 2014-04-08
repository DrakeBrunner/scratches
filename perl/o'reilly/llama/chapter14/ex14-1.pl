#!/usr/bin/perl

use 5.010;
use strict;
use warnings;

my @numbers = qw( 17 1000 04 1.50 3.14159 -10 1.5 4 2001 90210 665 );

say "Before sort:";
for (@numbers) {
    printf "%8g\n", $_;
}

my @sorted_numbers = sort { $a <=> $b } @numbers;

say "After sort:";
for (@sorted_numbers) {
    printf "%8g\n", $_;
}
