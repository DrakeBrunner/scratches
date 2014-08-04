#!/usr/bin/perl

use strict;
use warnings;

use List::MoreUtils qw( apply );
use Readonly;

my @input_numbers = (1 .. 5);
my @output_numbers = apply { $_ *= 2 } @input_numbers;
print "@input_numbers\n";
print "@output_numbers\n";

my @sample_texts = "a" .. "f";
my $foo = test_wantarray(@sample_texts);
print "$foo\n";
my @foo = test_wantarray(@sample_texts);
print "@foo\n";

my $sample_text = "hello world";
test_shift($sample_text);
print "$sample_text\n";

for ($sample_text) {
    print "SAMPLE: $_\n";
}

sub test_wantarray {
    die "You gotta want array!" unless defined wantarray;

    my @copy = @_;

    for my $text (@copy) {
        $text = chr(ord($text) + 1);
    }

    local $" = " :: ";
    return wantarray ? @copy : "@copy";
}

sub test_shift {
    my $ref = \@_;

    for (@$ref) {
        $_ = "baz";
    }
}
