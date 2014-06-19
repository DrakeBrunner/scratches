#!/usr/bin/perl

use strict;
use warnings;

use utf8;
use JSON;
use Encode;
#use Unicode::Japanese;
use Lingua::JA::Regular::Unicode;
use Data::Dumper;

my $input;
open my $json_fh, "default.json";
# open my $json_fh, "$ENV{HOME}/Dropbox/act09_default.json";
# "slurp" file
do {
    local $/;
    $input = <$json_fh>;
};

$input = encode_utf8($input);
my $data = decode_json($input);

# DEBUG
#print Dumper($data->{define}{"rom-kana"});
#print Dumper($data);
$data = $data->{define}{"rom-kana"};

# Create hash of array
my $out_hash;
for my $rule (sort keys %$data) {
    # Hiragana is in second entry
    my $hira = $data->{$rule}->[1];
    $rule =~ s/,/&comma/g;
    $hira = decode "utf8", $hira;
    my $kana_z = hiragana2katakana $hira;
    my $kana_h = katakana_z2h $kana_z;
    print "$rule,$hira,$kana_z,$kana_h", "\n";
}
