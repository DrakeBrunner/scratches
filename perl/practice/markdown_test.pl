#!/usr/bin/perl

use strict;
use warnings;

use Text::Markdown;

my $m = Text::Markdown->new;
my $text = <<EOF
Testing markdown
================
`Code here`
__italic__
*BOLD*

A paragraph,  
new line 
and no new line

2 spaces  <-here

2 spaces  
and new line here

2 spaces  \nand escape char here
EOF
;

print "Original\n$text\n";
my $html = $m->markdown($text);
print "Converted\n$html\n";

my $text2 = "This\nis";
$text2 =~ s/(\S)\n(\S)/$1  \n$2/g;
print "$text2\n";
