#!/usr/bin/perl

use strict;
use warnings;

print "Enter directory you want to check: ";
chomp (my $dir = <STDIN>);

opendir DH, $dir or die "Can't open $dir: $!";

for my $file (sort readdir DH) {
    # next if it's a directory
    next if $file =~ /^\..*/ or -d $file;
    print "one file in $dir is $file.\n";
}
closedir DH;
