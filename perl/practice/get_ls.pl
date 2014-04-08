#!/usr/bin/perl

use strict;
use warnings;

# This script will output the result of 'ls -l'
# By default, it outputs the command "ls -l /" to the file "ls.out"

open OUT, ">ls.out";

my $sys_dir = "/";
my @list = `ls -l $sys_dir`;
for (@list) {
    print OUT "$_";
}
