#!/usr/bin/perl

use strict;
use warnings;

#This script subtracts the sum of the square from the square of the sum
print "To which number do you want to calc?: ";
chomp(my $lim = <STDIN>);

printf "The answer is: %d\n", &square_of_sum() - &sum_of_square();

sub square_of_sum {
	my $sum = 0;

	for (my $i = 0; $i <= $lim; $i++) {
		$sum += $i;
	}
	return $sum**2;
}

sub sum_of_square {
	my $sum = 0;

	for (my $i = 0; $i <= $lim; $i++) {
			$sum += $i**2;
	}	
	return $sum;
}
