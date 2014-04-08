#!/usr/bin/perl

use strict;
use warnings;

my @set;

if (@ARGV) {
    @set = @ARGV;
}
else {
    chomp(my $n = <STDIN>);

    for (my $i = 0; $i < $n; $i++) {
        for (my $j = 0; $j < $n + 1; $j++) {
            chomp(@set[$i][$j] = <STDIN>);
        }
    }
}
