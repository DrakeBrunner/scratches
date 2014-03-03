# This program is an assembly inplementation for the following C code snippet
# int n, add_sub;
# scanf("%d", &n);
# if (n == 0) {
#     int i, j;
#     scanf("%d %d", &i, &j);
#     add_sub = i + j;
# }
# else
#     add_sub = n - 1;
# int * reg = &add_sub;
# printf("%d\n", *reg);
#
# In written form, the program first prompts for a number. If the input
# number is equal to zero, the program will prompt for two more numbers and
# store the sum of those two numbers into a temporary variable and save that
# address. If the number is not equal to zero, it will subtract 1 from the
# number and store the address of the result.
# The program will print the content of the saved address at the end.

# Read int
addi $v0, $zero, 5
syscall
move $t0, $v0

# if (input == 0)
beqz $t0, addNums
# else
subi $t0, $t0, 1
# Store content of $t0 to $s0 (= $sp)
move $s0, $sp
sw $t0, ($s0)
j print

addNums:
# Input first number
addi $v0, $zero, 5
syscall
# First number stored in $t0
move $t0, $v0
# Input second number
addi $v0, $zero, 5
syscall
move $t1, $v0

# Add the two numbers
add $t2, $t0, $t1
# Save $t2 to address $sp
sw $t2, ($sp)
# Save memory address
move $s0, $sp
j print

print:
addi $v0, $zero, 1
# $s0 contains the address
lw $a0, ($s0)
syscall

# exit program
addi $v0, $zero, 10
syscall