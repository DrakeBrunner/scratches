#This program displays the prime factors
print "Enter number to display prime factors: ";
$number = <STDIN>;
chomp $number;

if ($number < 2) {
    print "Don't kid me!\n";
    exit();
}
$null = $number;

for (my $n = 1; $n <= $number; $n++) {
    my $count = 0;
    if ($null % $n == 0) {
        while ($null % $n == 0) {
            $null = $null / $n;
            $count++;

            if ($n == 1) {
                last;
            }
        }
    }
    else {
        next;
    }
    unshift @prime_table, $n;
    unshift @multiply, $count;
}

#Cut the two edge elements of array
pop @prime_table;
pop @multiply;
#shift @prime_table;
#shift @multiply;

if (@prime_table[0] == $number) {
    print "That number is a prime number.\n";
}
else {
    for (my $n = @prime_table - 1; $n > 0; $n--) {
        print "($prime_table[$n] ** $multiply[$n]) * ";
    }
    print "($prime_table[0] ** $multiply[0])";
}

print "\n";
