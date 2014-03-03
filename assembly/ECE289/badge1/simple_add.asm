j main

arith:
add $ra, $a0, $a1 # Add $a0 and $a1, put it into $ra

main:
addi $a0, $zero, 5 # Set $a0 to 5
addi $a1, $zero, 3 # Set $a1 to 3
jal arith