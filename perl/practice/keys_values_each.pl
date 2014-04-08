#!/usr/bin/perl

use strict;
use warnings;

#This program is to know how "keys, values, and each" functions work.

my ($keys, $values);
my %string_hash = ("a" => "A", "i" => "I", "u" => "U", "e" => "E", "o" => "O");

print "List of keys: ", keys %string_hash, "\n";

print "List of values: ", values %string_hash, "\n";

print "List of keys and values: ";
print ("$keys -> $values\n") while (($keys, $values) = each(%string_hash));
print "\n";
