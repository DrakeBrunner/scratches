#!/usr/bin/perl

use 5.010;
use strict;
use warnings;

given ($ARGV[0]) {
    when ($_ ~~ undef)   { say "Invalid argument!"; exit }
    when (/\D/)                     { say "Argument is not a number!"; exit }
    when (&divisors($_) ~~ 0)   { say "$_ is a prime number" }
    default { print join(", ", &divisors($_)), "\n" }
}

sub divisors {
    my $number = shift;

    my @divisors = grep { $number % $_ == 0 } (2 .. $number / 2);
    return @divisors;
}
