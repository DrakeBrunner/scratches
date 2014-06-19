#!/usr/bin/perl

use strict;
use warnings;
use File::Path;

my @subdirs_to_make = qw(lab homework);

print "From what to what? Separate by comma: ";

<STDIN> =~ /(\d+)\s*,?(\s*(\d*)?)/;
my $start = $1;
my $end = $2 eq "" ? $start : $2;

for my $week_number ($start..$end) {
    my $dir_name = sprintf "week_%02d", $week_number;

    if (-d "$dir_name") {
        for (@subdirs_to_make) {
            mkpath "$dir_name/$_";
        }
    }
    else {
        mkdir "$dir_name" or die "Failed to create $dir_name: $!";
        redo;
    }
}
