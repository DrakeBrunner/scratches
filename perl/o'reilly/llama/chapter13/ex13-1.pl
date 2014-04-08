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
chdir $dir or die "Could not move to $dir: $!";
for (sort glob "*") {
    print "$_ ";
}
