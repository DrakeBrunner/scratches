# Input numbers and store them into an array
#
# Implementation of the following code:
# int n = 5;
# int * a;
# int i;
# for (i = 0; i < n; i++) {
#     a[i] = {input}
# }

j main

length:
li $v0, 5		# Prepare to read int
syscall
move $s0, $v0	# Memorize length of the array
jr $ra

for:

addi $t0, $t0, 1	# Increment counter (i++)

# store
li $v0, 5
syscall
sw $v0, 4($sp)	# Store input number to stack
addi $sp, $sp, 4	# Forward stack pointer

blt $t0, $s0, for	# Loop again (i < $s0)
jal end

main:
jal length		# Get length of the array
addi $s1, $sp, 0	# Save the stack pointer for beginning of the array
jal for

end:
li $v0, 10		# Exit program
syscall