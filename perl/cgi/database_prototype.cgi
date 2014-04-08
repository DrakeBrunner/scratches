#!/usr/bin/perl

use strict;
use warnings;

use NigoroJr::CGI::Receive;

# Prototype CGI for processing user name and timer record.
# Use this CGI with the Android app, "O-Timer"

print "Content-type: text/html\n";
print "\n";

my $received;
$ENV{'REQUEST_METHOD'} eq "POST"
    ? chomp($received = <STDIN>)
    : $received = $ENV{'QUERY_STRING'};

my %form = %{ &get_hash($received) };

foreach $key (keys %form) {
}
