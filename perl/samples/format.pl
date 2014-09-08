#!/usr/bin/perl

use strict;
use warnings;

my $lines = "foobar\nbaz\nbarbar\n";
my $top = "fizbaz";

format STDOUT_TOP =
@|||||||||||||||||||||||||||||||||||||||||
"something in the middle"
                ^<<<<<<<<<
                $top
~               @<<<<<<<<<<<<<<<<
                q{} # Empty line suppressed
.

format STDOUT =
@<<<<<<<<<<<<<<<< @||...           @>>>>>>
"left",           "middle column", "right"
@<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< ^*
"barbaz",                       $lines
~                               ^*
                                $lines
~                               ^>>>>>>>>>>
                                $lines
.

write STDOUT;
