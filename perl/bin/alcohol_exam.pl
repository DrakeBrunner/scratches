#!/usr/bin/perl

use strict;


open ANSWER, "alcohol.txt" or die "Couldn't open: $!";

print "What's the name of your input file? ";
chomp(my $my_exam = <STDIN>);
open MY_EXAM, $my_exam or die "Couldn't open: $!";

my %questions;
my $line_count;
my @alphabet = qw( A B C D );
my $question_tmp;

# For continuing loop when question is found
my $question_found = 0;

while (<ANSWER>) {
    chomp;
    $line_count++;

    unless ($question_found == 1) {
        # Skip this if question if found
        next unless /^\d+/;
        $question_found = 1;

        if (/\s+/) {
            # Save the question if
            # current line is a question
            $question_tmp = $';
            # Reset line count, which
            # helps display the alphabet
            $line_count = 0;
        }
    }

    # If the line is the answer
    # if (/\*+$/) {
    if (/\s*(.*?)\*+$/) {
        my $right_answer = $1;
        $questions{$question_tmp} = "@alphabet[($line_count / 2) - 1], $right_answer";

        # Don't forget to reset question found status!
        $question_found = 0;
    }
}

while (<MY_EXAM>) {
    chomp;

    if (/^(\d+)\:\s*/) {
        my $line_num = $1;
        print "$line_num\n$questions{$'}\n\n";
    }
}
