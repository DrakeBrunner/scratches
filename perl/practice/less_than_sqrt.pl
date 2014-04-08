#!/usr/bin/perl

use strict;
use warnings;
use POSIX;

my $n = 1.0;

while ( (my $result = sqrt((21 * $n) / 2)) <= 50) {
    print "$n\n" unless $result =~ /\D/;
    $n++;
}
