#!/usr/bin/perl

use strict;
use warnings;

package Oogaboogoo::date;

my @day = qw( ark dip wap sen pop sep kir );
my @month = qw( diz pod bod rod sip was lin sen kun fiz nap dep );

sub day {
    my $num = shift;
    die if $num < 0 or $num > 6;
    return $day[$num];
}

sub month {
    my $num = shift;
    die if $num < 0 or $num > 11;
    return $month[$num];
}

1;
