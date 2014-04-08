#!/usr/bin/perl

use strict;
use warnings;

# This program displays the largest prime factor

# Input number
print "Enter number to display the greatest prime factor: ";
chomp(my $user_input_number = <STDIN>);

# Don't Calculate if it is under 2
if ($user_input_number < 2) {
    print "Don't kid me!\n";
    exit;
}

# Main Script

# Because maximum prime factor
# must not be more than half of $user_input_number
for (my $n = int($user_input_number / 2); $n > 0; $n--) {
    if ($user_input_number % $n == 0) {

        # Give value to subroutine
        my $result = &test_if_prime_number_for( $n );

        if ($result == 1) {
            print "Greatest prime factor for $user_input_number is $n.\n";
            exit;
        }
    }
}

print "That number is a prime number.\n";

sub test_if_prime_number_for {
    my $test_this_number = shift;

    for (my $n = int($test_this_number / 2); $n > 0; $n--) {
        return 1 if $n == 1;
        return 0 if $test_this_number % $n == 0;
    }
}
