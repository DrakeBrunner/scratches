#!/usr/bin/perl

use strict;
use warnings;

#This is a program that converts decimals to decihex.

print "Enter decimal numbers: ";
chomp(my $input = <STDIN>);

printf "Hex number is %04X\n", $input;
