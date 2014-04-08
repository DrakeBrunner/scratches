#!/usr/bin/perl

use strict;
use warnings;

print "To how much? ";
chomp(my $limit = <STDIN>);

my $current_num;
for ($current_num = 1; $current_num <= $limit; $current_num++) {    
    if ($current_num % 15 == 0) {
        print "FizzBuzz";
    }
    elsif ($current_num % 3 == 0) {
        print "Fizz";
    }
    elsif ($current_num % 5 == 0) {
        print "Buzz";
    }
    else {
        print "$current_num";
    }
    # Hate it when there's a comma at the very end
    print ", " unless $current_num == $limit;
}
