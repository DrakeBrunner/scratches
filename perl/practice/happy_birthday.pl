#!/usr/bin/perl

use strict;
use warnings;

#This clever script congratulates you when it's your birthday
print "When is your birthday?(MM/DD)\n";
chomp(my $birthday = <STDIN>);

$birthday =~ /(\d+)(?:\/?|\s+)(\d+)/;
my $birth_month = $1;
my $birth_date = $2;

my ($date, $month) = (localtime)[3, 4];
$month++;

print "Happy Birthday!!!\n" if $date == $birth_date and $month == $birth_month;
