
# Read string with length 5
li $v0, 8
addi $a0, $sp, 0
addi $s0, $a0, 0	# Memorize the address of string
addi $a1, $zero, 5
syscall

# Print the string previously read
li $v0, 4
addi $a0, $s0, 0
syscall

# Read in number
li $v0, 5
syscall
move $t0, $v0		# Save read number to $t0

# Modify the number a little bit
addi $t0, $t0, 5

# Print out interger contained in $t0
li $v0, 1
move $a0, $t0
syscall

# Exit the program
li $v0, 10
syscall
