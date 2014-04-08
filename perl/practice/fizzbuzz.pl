#!/usr/bin/perl

use strict;
use warnings;

# FizzBuzz program
print "To how much do you want to \"FizzBuzz\"? ";
chomp(my $limit = <STDIN>);

for (my $i = 1; $i <= $limit; $i++) {
    my $flag = 0;
    if ($i % 3 == 0) {
        print "Fizz";
        $flag = 1;
    }
    if ($i % 5 == 0) {
        print "Buzz";
    }
    else {
        if ($flag == 1) {
            goto connect_numbers;
        }
        print $i;
    }
    connect_numbers:
    if ($i == $limit) {
        last;
    }
    print ", ";
}
print "\n";
