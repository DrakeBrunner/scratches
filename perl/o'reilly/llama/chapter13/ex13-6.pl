#!/usr/bin/perl

use 5.010;
use strict;
use warnings;

my ($link_of, $link_to) = @ARGV;
link $link_of, $link_to or die $!;
