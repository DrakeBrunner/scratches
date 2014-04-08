use Benchmark;
my $bench1 = new Benchmark;

# Palindrom of two 3-digit numbers
# For example, 993 * 913 = 906609
print "How many digits?\nI strongly recommend not doing over 4: ";
$digit = <STDIN>;
chomp $digit;

$max_term = 10**$digit - 1;
$minimum = 10**$digit - 10**($digit - 1);

for ($i = $max_term; $i >= 10**($digit - 1); $i--) {
    for ($j = $max_term; $j >= $minimum; $j--) {
        #Exclude if neither is the multiple of 11
        if ($i % 11 != 0 && $j % 11 != 0){
            next;
        }

        $product = $i * $j;

        #Reverse string
        while ($product) {
            push @sample, chop($product);
        }
        #Join with empty string
        $product = join("", @sample);

        #Format array!
        @sample = ();

        if ($product == $i * $j) {
            unshift @matched, $product;

            #Enter (update) value to hash
            if ($matched[0] > $matched[1]) {
                $hash{key_i} = $i;
                $hash{key_j} = $j;
            }
            #Sort array
            if ($matched[0] < $matched[1]) {
                $matched[0] = $matched[1];
                #Cleanup
                pop @matched;
            }
        }
    }
}
print "Maximum palindrome is $matched[0] which is $hash{key_i} x $hash{key_j}\n";

$bench2 = new Benchmark;
$dif = timediff($bench2, $bench1);
print timestr($dif), "\n";
