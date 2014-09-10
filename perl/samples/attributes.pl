#!/usr/bin/perl

use strict;
#use warnings;

test();

sub MODIFY_CODE_ATTRIBUTES {
    my ($pkg, $ref, @attrs) = @_;

    print "MODIFY_CODE_ATTRIBUTES CALLED!\n";
    print "PKG: $pkg\n";
    print "REF: $ref\n";
    do {
        local $" = ", ";
        print "ATTRS: @attrs\n";
    };

    # Can't have return values
    #return "error";
    return;
}

sub test : attribute foo bar baz {
    print "IN TEST\n";
}
