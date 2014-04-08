#!/usr/bin/perl

use strict;
use warnings;

my %values;
while (<>) {
    chomp;
    my ($letter, $start, $end, $val) = split "\t| ";

    next if (defined $values{$letter}{skip_rest});

    if ($val > 0.3) {
        $values{$letter}{min} = $start
            if not defined $values{$letter}{min} or $values{$letter}{min} > $start;
        $values{$letter}{max} = $end
            if not defined $values{$letter}{max} or $values{$letter}{max} < $end;
    }
    elsif (defined $values{$letter}{min} and defined $values{$letter}{max}) {
        $values{$letter}{skip_rest} = "true";
    }
}

foreach my $letter (sort keys %values) {
    print "$letter $values{$letter}{min} $values{$letter}{max}\n";
}
