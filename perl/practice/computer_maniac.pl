#!/usr/bin/perl

use strict;
use warnings;

#This script lets you be a computer maniac who knows every technical terms

#Open dictionary file
open DIC, "dictionary.txt" or die "Cannot open dictionary file: $!";

my @noun_dic;
my @verb_dic;

while (<DIC>) {
    if (/(.*),\s?(.*)/) {
        my $word = $1;
        my $category = $2;
        push @noun_dic, $word if $category == 1;
        push @verb_dic, $word if $category == 0;
    }
}
my $noun = @noun_dic;
my $verb = @verb_dic;


if ($noun < 2 || $verb < 2) {
    print "レパートリーが少なすぎるぜ！！\n";
    exit();
}


print "ああ、パソコンなんてあんなもん余裕だぜ？
だってさ、@noun_dic[int( rand($noun) )]を@verb_dic[int( rand($verb))]して@noun_dic[int( rand($noun) )]も@verb_dic[int( rand($verb))]すればちょちょいのちょいさ！\n";

if ($noun >= 4 || $verb >= 4) {
    print "それに@noun_dic[int( rand($noun) )]？
そんなこともわからないの！？
あれは@verb_dic[int( rand($verb) )]しながら@noun_dic[int( rand($noun) )]を@verb_dic[int( rand($verb) )]で全部解決だよ！
まったくぅ、話しにならないなぁ(-_-)\n";
}
