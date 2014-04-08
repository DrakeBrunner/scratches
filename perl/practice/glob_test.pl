#!/usr/bin/perl

use 5.010;
use strict;
use warnings;

my @glob = <*>;
for (@glob) {
    print "one file is $_.\n";
}

say "Dir to process: ";
chomp (my $dir_to_process = <STDIN>);

@glob = <$dir_to_process/* $dir_to_process/.*>;
for (@glob) {
    next if -d $_;
    print "one file is $_.\n";
}
