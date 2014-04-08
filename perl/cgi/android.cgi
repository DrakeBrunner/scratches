#!/usr/bin/perl

use strict;
use warnings;
use URI::Escape;
use Encode qw(decode encode);
use utf8;

# You may need to install URI::Escape via cpan

print "Content-type: text/html\n";
print "\n";

my $received;
if ($ENV{'REQUEST_METHOD'} eq "POST") {
    chomp($received = <STDIN>);
}
else {
    $received = $ENV{'QUERY_STRING'};
}

my $username = "anonymous";

my %form;
my ($name, $value);
my @pairs = split /&/, $received;
foreach my $pair (@pairs) {
    ($name, $value) = split /=/, $pair;

    # Replace the '+' with ' ' (spaces)
    $name =~ s/\+/ /g;
    $value =~ s/\+/ /g;
    # Decode hex string
    $name = uri_unescape($name);
    $value = uri_unescape($value);
    # Decode to utf-8
    $name = decode('UTF-8', $name);
    $value = decode('UTF-8', $value);

    $form{$name} = $value;
}

$username = $form{username} if defined $form{username};

if ($form{type} eq "debug") {
    open FILE, ">>writeTo/debug.txt" or die "Couldn't open file! $!";
}
elsif ($form{type} eq "records") {
    open FILE, ">>writeTo/$username.txt" or die "Couldn't open file! $!";
}

# Modify message
foreach my $key (keys %form) {
    print FILE "$key\n$form{$key}\n\n";
}

close FILE;
