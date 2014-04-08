#!/usr/bin/perl

use strict;
use warnings;

my $i = 970179;
print "num: $i\n";
print &check_if_palindrome($i), "\n";

sub check_if_palindrome {
    my $check = shift;

    # Exception for 10
    return 0 if $check == 10;

    my $digits = 0;
    $digits++ while ($check / 10 ** $digits > 1);
    for (my $i = 1; $i <= int($digits / 2); $i++) {
        print "#$i\n";
        print "one: ", int($check / 10 ** ($digits - (2 * $i - 1))), "\n";
        print "two: ", $check % 10, "\n";

        return 0 unless int($check / 10 ** ($digits - (2 * $i - 1)))
                    == $check % 10;
        $check %= 10 ** ($digits - (2 * $i - 1));
        $check = int($check / 10);
        print "check is $check\n";
    }
    return 1;
}
