#!/usr/bin/perl

use strict;
use warnings;

# This Perl script is for testing buffer flush
# Set it to Autoflush
$| = 1;

print "Starting";

for (my $i = 0; $i < 5; $i++) {
    print ".";
    sleep 1;
}

print "\nEnd!\n";
