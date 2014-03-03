# Read in a string and count the number of letters
# ASCII code for '\n' (new line) is 0x0000000a and 10 in decimal

j main

input_letter:
# Allocate address
addi $a0, $zero, 4	# Get 4 bytes
li $v0, 9
syscall		# Address set to $v0

# Read one character
move $a0, $v0		# Use the address allocated
addi $a1, $zero, 2	# 2 letters (letter + '\0')
li $v0, 8
syscall

addi $s1, $s1, 1	# Increment length of string

lw $t0, ($a0)	# Get the ASCII code
bne $t0, 10, input_letter	# Repeat if letter entered is not '\n'
move $sp, $s0	# Restore $sp
addi $s1, $s1, -1	# Don't count the trailing '\n' as a letter
jr $ra

main:
move $s0, $sp	# Save stack pointer
addi $s1, $zero, 0	# Initialize length of string

jal input_letter

move $a0, $s1	# Print letter count
li $v0, 1
syscall

li $v0, 10	# Exit
syscall