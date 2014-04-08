#!/usr/bin/perl

use strict;
use warnings;

open F1, "file1.in" or die "$!";
open F2, "file2.in" or die "$!";

while (defined(my $f1 = <F1>) and defined(my $f2 = <F2>)) {
    chomp $f1;
    chomp $f2;
    print "$f1,$f2\n";
}
