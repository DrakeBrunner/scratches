#!/usr/bin/perl

use strict;
use warnings;

my $text;
do {
    local $/;
    $text = <DATA>;
};

# Use %+ hash to access named captures
print $+{name} . "\n" if $text =~ m/(?<name>b.z)/;
# Python-style named capture
# print $+{name} . "\n" if $text =~ m/(?P<name>b.z)/;

my $exp = '(.)\1';
print defined("aa" =~ m/$exp/) . "\n";
print defined("aa" =~ m/${exp}/) . "\n";
print defined("aa0" =~ m/${exp}0/) . "\n";

my $something = "hello, world!";
print "Matched!\n" if $something =~ /(?>.*?)/;

__DATA__
foobar
baz bang!
