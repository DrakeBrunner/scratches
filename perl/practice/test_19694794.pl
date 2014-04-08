#!/usr/bin/perl

use strict;
use warnings;

use Data::Dumper;

my $in_file = "input.txt";
# User-specified region
my $range_start = 10;
my $range_end = 25;

open my $fh, $in_file or die $!;

my %result;
while (<$fh>) {
    my ($id, $start, $end) = split " ", $_;
    next unless $start =~ /\d/;

    # Swap if START is larger than END
    ($start, $end) = ($end, $start) if $start > $end;

    push @{$result{$id}}, [$start, $end]
    #$result{$id} = [$start, $end]
        if $start >= $range_start and $end <= $range_end;
}

print Dumper(%result);
