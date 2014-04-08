#!/usr/bin/perl

use strict;
use warnings;

#This is a program that counts the number of integers
#that can be divided by 2, 3, and 5 within the array of 1 through 100.

my %count;
my @array = (1 .. 100);
print "\"Counting the number of integers that can be divided by 2, 3, and 5 within the range of 1 through 100...\"\n";

foreach my $value (@array){
	$count{2}++ if ($value % 2 == 0);

	$count{3}++ if ($value % 3 == 0);

	$count{5}++ if ($value % 5 == 0);
}
print "Number of...\n";
printf "2: %d\n", $count{2} if exists $count{2};
printf "3: %d\n", $count{3} if exists $count{3};
printf "5: %d\n", $count{5} if exists $count{5};
