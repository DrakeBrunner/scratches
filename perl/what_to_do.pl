#!/usr/bin/perl

use strict;
use warnings;

use List::Util::WeightedChoice qw( choose_weighted );

open my $input_fh, "list.txt" or die $!;

my %list;
while (<$input_fh>) {
    chomp;
    # Skip comment lines
    next if /^(#|\s*$)/;

    my ($what, $weight) = split /\s*,\s*/, $_;
    $list{$what} = $weight;
}

# Make weight of entries without weight the same as max
my $max = 0;
grep { $max = $_ if defined $_ and $_ > $max } values %list;
grep { $list{$_} = $max unless defined $list{$_} } keys %list;

my $choice = choose_weighted([keys %list], [values %list]);

print "$choice\n";
