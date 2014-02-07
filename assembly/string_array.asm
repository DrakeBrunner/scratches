j main

length:
li $v0, 5		# Prepare to read int
syscall
move $s0, $v0	# Memorize length of the array
jr $ra

alloc:
move $a0, $s0
li $v0, 9		# Prepare to dynamically allocate memory
syscall
move $sp, $v0	# Change stack pointer to the beginning of array
jr $ra

main:
jal length		# Get length of the array
jal alloc		# allacate memory
move $s1, $sp	# Save the stack pointer for beginning of the array

addi $t0, $t0, 1	# Increment counter (i++)

move $a0, $sp
addi $a1, $s0, 1	# Length of string + 1 for '\0'

# store
li $v0, 8
syscall

li $v0, 10		# Exit program
syscall
