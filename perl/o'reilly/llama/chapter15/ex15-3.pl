#!/usr/bin/perl

use 5.010;
use strict;
use warnings;

my @attrib;
for (@ARGV) {
    exit unless -e;
    when ( -r ) { push @attrib, "readable"; continue }
    when ( -w ) { push @attrib, "writable"; continue }
    when ( -x ) { push @attrib, "executable" }
}
unless (@attrib) {
    say "File exists.";
    exit;
}

my $last_attrib = pop @attrib;
print "File is ", join(", ", @attrib), " and $last_attrib.\n";
