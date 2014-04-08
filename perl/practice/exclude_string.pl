#!/usr/bin/perl

use strict;
use warnings;

# This program seems to (I don't remember why I wrote this)
# exclude words that starts with an "a" and ends with a "y"
# It then displays all the other input

print "Input sentence: ";
chomp(my $input = <STDIN>);

my @array = split /\s+/, $input;

my @array_2 = grep {!/^a.*y$/} @array;

print join(" ", @array_2), "\n";
