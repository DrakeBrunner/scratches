#!/usr/bin/perl

use strict;
use warnings;

use Apache::ParseLog;
use Date::Parse;
use Time::Piece;
use Time::Seconds;
use Data::Dumper;
use Geo::IP;

# Description
# Checks for number of invalid ssh access, if there were users that logged in
# since last check, and HTTP access per page.
# I wanted to use GeoIP2 instead of Geo::IP, but I couldn't install
# the required Math::Int128 module because of the CPU I'm using.

my $last_check_file = "$ENV{HOME}/.myconfig/last_check.log";
my $enable_geo = "false";

my $httpd_conf = "/usr/local/apache2/conf/httpd.conf";

my $base = new Apache::ParseLog($httpd_conf);
$base = $base->config(
    errorlog    => "/home/www/logs/error_log",
    transferlog  => "/home/www/logs/access_log",
);

my $log_object = $base->getTransferLog();
my %host = $log_object->host();
# print Dumper(%host), "\n";
my %visitorbydate = $log_object->visitorbydate();

my $gi = Geo::IP->open("/usr/local/share/GeoIP/GeoLiteCity.dat", GEOIP_STANDARD);

open my $last_check_fh, $last_check_file or die "Failed to open last check file $last_check_file: $!";
my $line;
# Skip comment line starting from #
do {
    chomp($line = <$last_check_fh>);
} while ($line =~ /\s*#/);
close $last_check_fh;

my ($sec, $min, $hour, $m_date, $month, $year) = (strptime($line))[0..5];
# Correct the year/month
$month++;
$year += 1900;

my $now = Time::Piece::localtime;
my $date = Time::Piece->strptime("$year $month $m_date", "%Y %m %d");
print "Date of last check was: $month/$m_date/$year\n";
while ($date->year != $now->year
        or $date->day_of_month != $now->day_of_month
        or $date->mon != $now->mon) {
    # Iterate through each date
    $month = $date->mon;
    $m_date = $date->day_of_month;
    $year = $date->year;
    print "Visitor on $month/$m_date/$year: ", $visitorbydate{"$month/$m_date/$year"}, "\n" if defined $visitorbydate{"$month/$m_date/$year"};
    $date += ONE_DAY;
}
print "Total unique visitors: ", scalar keys %host, "\n";

if ($enable_geo eq "true") {
    # See where the visitors come from
    my @top_addr = sort {$host{$b} <=> $host{$a}} keys %host;
    foreach my $addr (@top_addr) {
        unless ($addr =~ /\d+\.\d+\.\d+\.\d+/) {
            print "$addr $host{$addr}\n";
            next;
        }
        my $record = $gi->record_by_addr($addr);
        next unless defined $record;
        printf "%-15s %20s %d\n", $record->country_name, $record->city, $host{$addr};
    }
}

# Update date of last check
# Uncomment for practical use
open my $last_check_fh_write, ">", $last_check_file or die "Failed to open last check file for writing $last_check_file: $!";
print $last_check_fh_write $now;
close $last_check_fh_write;
