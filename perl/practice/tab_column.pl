#!/usr/bin/perl

use strict;
use warnings;

chomp(my $first_line = <STDIN>);
my $length = scalar(split /\t/, $first_line);

my %rows;
while (<STDIN>) {
    chomp;
    my @row = split /\t/;
    push @row, "" for 1..$length - scalar @row;
    # Assuming there's ALWAYS a row ID
    my $id = shift @row;
    $rows{$id} = [@row];
}

foreach my $rowID (keys %rows) {
    for (my $i = 0; $i < @{$rows{$rowID}}; $i++) {
        # Column 1 being the id
        printf "missing column #%d in %s\n", $i + 1, $rowID
            if $rows{$rowID}[$i] eq "";
    }
}
