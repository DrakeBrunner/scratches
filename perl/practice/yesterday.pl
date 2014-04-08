#This program displays the time of 24 hours before.
($sec, $min, $hour, $date, $month, $year, $day, $day_passed, $summer_time) = localtime();
printf("%4d\/%02d\/%02d (%s) %02d:%02d:%02d\n", $year + 1900, $month + 1, $date - 1, ("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")[$week_day], $hour + $summer_time, $min, $sec);	#decrement date
print("Day passed from this year's Jan. 1 is: $day_passed\n");
printf("Summertime is %s", $summer_time ? "$summer_time hours" : "Off.\n");
