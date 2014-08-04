#!/usr/bin/perl

use strict;
use warnings;

use Data::Dumper;

my %hash = (
    "foo\n"  => "bar\n",
    "baz"    => "qux",
    "foobar" => "quux\n",
    "foobaz" => "foobarbaz",
);
my $NEW_LINE;
$NEW_LINE = "\n";
print chomp(%hash, $NEW_LINE);  # Prints 3 (2 from values + $NEW_LINE)

# Use chomp with array
my @arr = ("foo\n", "bar", "baz\n");
$NEW_LINE = "\n";               # Gets reset in previous `chomp`
print chomp(@arr, $NEW_LINE);   # Prints 3

__END__
# Do not work
print chomp @arr, $str;     # print( chomp(@arr), $str )
print chomp(@arr, "\n");    # Can't modify constant "\n"
