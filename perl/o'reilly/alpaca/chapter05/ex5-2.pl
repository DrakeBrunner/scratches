#!/usr/bin/perl

use strict;
use warnings;

open DAT, "coconet.dat" or die "Couldn't open file: $!";

my %transmission;
my $all = "All Machines$$";
while (<DAT>) {
    chomp;
    next if /^#/;
    my ($source, $destination, $bytes) = split;
    $transmission{$source}{$destination} += $bytes;
    $transmission{$source}{$all} += $bytes;
}

my @sources =
    sort { $transmission{$b}{$all} <=> $transmission{$a}{$all} }
    keys %transmission;

for my $source (@sources) {
    print "$source: $transmission{$source}{$all} bytes sent\n";
    my @destination =
        sort { $transmission{$source}{$b} <=> $transmission{$source}{$a} }
        keys %{ $transmission{$source} };
    for my $destination (@destination) {
        next if $destination eq $all;
        print "  $source => $destination: ",
          "$transmission{$source}{$destination} bytes\n";
    }
    print "\n";
}
