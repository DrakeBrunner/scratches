#!/usr/bin/perl

use strict;
use warnings;

local($^I, @ARGV) = (".bak", "input.in");
# Just local(@ARGV) = ("input.in") does not work

# while (<$file_handle>) does not work
while (<>) {
    # Insert 'Hi! ' at the beginning if not already there
    s/^/HI! / if ! /^HI! /;
    print;
}
