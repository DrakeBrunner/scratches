#!/usr/bin/perl

my %scores = (
    "barney" => 107,
    "fred"   => 86,
    "dino"   => 27,
    "bamm"   => 107,
);

print "Sorted scores are ",
    join(" ", sort { $scores{$a} <=> $scores{$b} or $a cmp $b } values %scores),
    ".\n";
