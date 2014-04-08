#This program displays the largest prime factor
print "Enter number to display the greatest prime factor: ";
$number = <STDIN>;
chomp $number;

if ($number < 2) {
    print "Don't kid me!\n";
    exit();
}

#Maximum prime factor cannot exceed half of $number
for (my $n = int($number / 2); $n > 0; $n--) {
    if ($number % $n == 0) {
        $result = &divide( $n );
        if ($result != 0) {
            print "Greatest prime factor for $number is $result.\n";
            exit();
        }
    }
}

print "That number is a prime number.\n";

sub divide {
    local $sample = @_[0];

#   for (my $n = 2; $n <= $sample; $n++) {
    for (my $n = int($number / 2); $n > 0; $n--) {
        if ($sample % $n == 0) {
            if ($sample == $n) {
                return $sample;
            }
            $sample = &divide( $sample / $n );
        }
    }
    return 0;
}
