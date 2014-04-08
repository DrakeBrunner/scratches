#!/usr/bin/perl

use strict;
use warnings;

use Benchmark;
my $bench1 = new Benchmark;

# Palindrom of two 3-digit numbers
# For example, 993 * 913 = 906609

print "How many digits? ";
chomp(my $digits = <STDIN>);

my $max = 10 ** $digits - 1;

my ($i, $j, $i_start);
my ($subsection, $n, $twice);

$i_start = $max;
for ($subsection = 1; $subsection < ($max / 2 + 1); $subsection++) {
    $i = $i_start;
    $j = $i;
    # Repeat 2 times
    for ($twice = 0; $twice < 2; $twice++) {
        for ($n = 0; $n < $subsection; $n++) {
            # print "checking $i and $j which is ", $i * $j, "\n";
            goto end if &check_if_palindrome($i * $j);
            $i++;
            $j--;
        }
        # Initialize for the next round
        $i = $i_start;
        $j = $i - 1;
    }
    $i_start--;
}
# Label "end" for breaking out of loop nest
end:
printf "Maximum palindrome is %d which is %d x %d\n" ,$i * $j, $i, $j;

#################################
#######    Subroutine    ########
#################################
sub check_if_palindrome {
    my $check = shift;

    # Exception for 10
    return 0 if $check == 10;

    my $digits = 0;
    $digits++ while ($check / 10 ** $digits > 1);
    for (my $i = 1; $i <= int($digits / 2); $i++) {
        # If it's a 4-digits number, for instance, 1221,
        # 1221 / 10 ** 3 must equal 1221 % 10 to be palindrome
        return 0 unless int($check / 10 ** ($digits - (2 * $i - 1)))
                    == $check % 10;
        $check %= 10 ** ($digits - (2 * $i - 1));
        $check = int($check / 10);
    }
    return 1;
}

# Benchmark
my $bench2 = new Benchmark;
my $dif = timediff($bench2, $bench1);
print timestr($dif), "\n";
