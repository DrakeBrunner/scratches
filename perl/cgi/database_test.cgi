#!/usr/bin/perl

use strict;
use warnings;

use NigoroJr::CGI::Receive;
use DBI;

print "Content-type: text/http\n";
print "\n";

# Store to hash
my $received;
if ($ENV{'REQUEST_METHOD'} eq "POST") {
    chomp($received = <STDIN>);
}
else {
    $received = $ENV{'QUERY_STRING'};
}

my %form = %{ NigoroJr::CGI::Receive::get_hash($received) };

open FILE, ">writeTo/database_test.txt";
foreach my $key (keys %form) {
    print FILE "$key\n$form{$key}\n";
}
close FILE;

# Use database
my $d = "DBI:mysql:OTimer";
my $u = "cgi_worker";
my $p = "cgi%ohayou";

my $dbh = DBI->connect($d, $u, $p);

# my $sth = $dbh->prepare("insert into cgi_test (id,name,age,sex) values (23456,Tet,19,M)");
my $sth = $dbh->prepare("insert into cgi_test values ($form{id},'$form{name}',$form{age},'$form{sex}')");
$sth->execute;
$sth->finish;

$dbh->disconnect;
