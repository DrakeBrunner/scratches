#!/usr/bin/perl

use 5.010;
use strict;
use warnings;

my $oldest_so_far = 0;
my $oldest_file_so_far = undef;

for (@ARGV) {
    next if -d $_;
    $oldest_so_far = -M $_ if -M $_ > $oldest_so_far;
    $oldest_file_so_far = $_;
}
unless ($oldest_so_far) {
    say "No file(s) specified!";
    exit;
}
print "Oldest file was $oldest_file_so_far which was modified $oldest_so_far days ago.\n";
