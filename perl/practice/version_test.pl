#!/usr/bin/perl

use strict;
use warnings;

open my $fh, "data.in" or die "$!";

my %table;
while (<$fh>) {
    chomp;
    my ($date, $data, $version, $identifier) = split;

    if (not defined $table{$date}{$identifier}{version}
            or $table{$date}{$identifier}{version} < $version) {
        $table{$date}{$identifier}{version} = $version;
        $table{$date}{$identifier}{data} = $data;
    }
}

close $fh;

foreach my $date (sort keys %table) {
    foreach my $identifier (sort keys $table{$date}) {
        my $data = $table{$date}{$identifier}{data};
        $table{$date}{$identifier}{version} = 0;
        my $version = $table{$date}{$identifier}{version};
        print "$date\t$data\t$version\t$identifier\n";
    }
}
