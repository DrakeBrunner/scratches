#!/usr/bin/perl

use 5.010;
use strict;
use warnings;

say "To where do you want to move? (will create if it doesn't exist)";
chomp(my $dir = <STDIN>);
unless (-e $dir) {
    print "Dir doesn't exist. Creating. Permission should be...?: ";
    mkdir $dir, oct(<STDIN>) or die "Couldn't create $dir: $!";
}

opendir DH, $dir;
for (sort {$a cmp $b} readdir DH) {
    next if $_ eq "." or $_ eq "..";
    print "$_ ";
}
closedir DH;
