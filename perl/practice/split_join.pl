#This program splits the input and reconnect with . in between.

print "Input strings: ";
$string = <STDIN>;
chomp($string);

@array = split(//, $string);
$joined = join(".", @array);

print "$joined\n";
