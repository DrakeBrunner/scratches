#!/usr/bin/perl

use warnings;

our $var = 'my var';

print "BEFORE: $var\n";
{
    # my $var = 'baz';
    local $var = 'baz';
    # our $var = 'baz';
    print "IN BLOCK: $var\n";
}

print "AFTER: $var\n";
print "\n";

###

{
    # my $foo = 'here';
    local $foo = 'here';
    test_sub();
}

sub test_sub {
    print(($foo // "undef"), "\n");
}
