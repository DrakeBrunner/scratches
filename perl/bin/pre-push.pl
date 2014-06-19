#!/usr/bin/perl

use warnings;
use strict;

my $script_file = "$ENV{HOME}/src/perl/bin/twitter.pl";

my $message = "";
my $log = `git log -n1`;
for my $line (split "\n", $log) {
    next if $line =~ /^(commit|Date|Author)/;
    if ($line =~ /^\s*(\w.*)/) {
        $message = $1;
    }
}

exec "perl $script_file $message";
