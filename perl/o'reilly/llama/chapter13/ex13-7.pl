#!/usr/bin/perl

use strict;
use warnings;

my ($switch, $link_of, $link_to) = @ARGV;
if ($switch =~ /-s/) {
    symlink $link_of, $link_to or die $!;
}
else {
    link $link_of, $link_to or die $!;
}
