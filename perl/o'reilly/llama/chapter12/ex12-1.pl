#!/usr/bin/perl

use strict;
use warnings;

for (@ARGV) {
    &test($_);
    print "\n";
}

sub test {
    my $file = shift;
    my @attrib;

    unless (-e $file) {
        print "$file does not exist!";
        return;
    }

    push @attrib, "readable"     if -r _;
    push @attrib, "writable"     if -w _;
    push @attrib, "executable"   if -x _;

    print "$file ";

    unless (@attrib) {
        print "exists.";
        return;
    }

    my $last_attrib = pop @attrib;
    print "is ";
    print join(", ", @attrib), " and " if @attrib >= 1;
    print "$last_attrib.";
}
