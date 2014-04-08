#!/usr/bin/perl

use 5.010;
use strict;
use warnings;

chomp(my $input = <STDIN>);

given ($input) {
    when ($_ % 3 == 0) { print "Fizz "; continue }
    when ($_ % 5 == 0) { print "Bin "; continue }
    when ($_ % 7 == 0) { print "Sausage " }
}
print "\n";
