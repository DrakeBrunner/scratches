#!/usr/bin/perl

use strict;
use warnings;

# This program will display the fibonacci sequence

print "How many terms? ";
chomp(my $max_term = <STDIN>);

my $first_term = 0;
my $second_term = 1;

for (my $n = 0; $n < $max_term; $n++) {
    my $sum_of_two_previous_terms = $first_term + $second_term;

    print "$sum_of_two_previous_terms ";

    $first_term = $second_term;
    $second_term = $sum_of_two_previous_terms;
}

print "\n";
