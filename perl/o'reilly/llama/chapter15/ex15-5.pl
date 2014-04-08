#!/usr/bin/perl

use 5.010;
use strict;
use warnings;

print "Your favorite number is? ";
chomp(my $favorite = <STDIN>);

given ($ARGV[0]) {
    when (/\D/)                                 { say "Argument is not a number!"; exit }

    my @divisors = &divisors($_);

    when (@divisors ~~ 2)                   { say "$_ is an even number."; continue }
    when ( ! (@divisors ~~ 2))              { say "$_ is an odd number."; continue }
    when (@divisors ~~ $favorite)           { say "$_ is divisible by your favorite number!"; continue }
    when (@divisors ~~ 0)                   { say "$_ is a prime number" }
    default { print join(", ", &divisors($_)), "\n" }
}

sub divisors {
    my $number = shift;

    my @divisors = grep { $number % $_ == 0 } (2 .. $number / 2);
    return @divisors;
}
