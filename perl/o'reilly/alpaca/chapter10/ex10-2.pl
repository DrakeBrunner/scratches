#!/usr/bin/perl

use strict;
use warnings;

require 'ex10-1.pm';

my($month, $date, $day, $year) = (localtime)[4, 3, 6, 5];
$year += 1900;

printf "Today is %s %s %d, %d\n",
    Oogaboogoo::date::day($day),
    Oogaboogoo::date::month($month), $date, $year;
