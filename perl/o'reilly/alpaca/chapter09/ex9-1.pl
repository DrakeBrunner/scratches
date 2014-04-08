#!/usr/bin/perl

use strict;
use warnings;

my @sorted =
    map $_->[0],
    sort { $a->[1] <=> $b->[1] }
    map [$_, -s $_],
    glob "/bin/*";

for (my $i = 0; $i < 10; $i++) {
    print "$sorted[$i]\n";
}
