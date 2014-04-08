#!/usr/bin/perl

use strict;
use warnings;

#This program will copy the selected files

print "Input original and copy file name separated by one or more space\n";
chomp(my $input = <STDIN>);

my @file = split(/\s+/, $input);
open F, "$file[0]" or die "Could not open $file[0]";
open F2, ">$file[1]" or die "Could not open $file[1]";

print F2 while (<F>);

close F2;
close F;
