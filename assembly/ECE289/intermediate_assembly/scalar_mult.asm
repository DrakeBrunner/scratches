# Calculates the scalar multiplication of a matrix

# m         => $s0
# col       => $s1
# row       => $s2
# scalar    => $s3

j main

# Multiplies and sets the first address of the matrix to $s4
matrix_scalar_multiply:
    move $t0, $ra   # Save return address

    # Allocate columns
    move $a0, $s1
    li $v0, 9
    syscall
    move $s4, $v0   # Address of first element in matrix

    # Variables used only for allocating
    li $t3, 0       # loop variable for allocating rows
    move $t4, $s4   # for allocating each "row"

    li $t1, 0       # Column counter
    li $t2, 0       # Row counter
    move $t6, $s4   # iterate through rows (one row to the next)
    move $t5, $s0   # copy of original matrix
    # $t8 is used to iterate through "the" row

# Allocate rows
loop:
    bge $t3, $s1, outer_loop
    # Allocate rows for each columns
    move $a0, $s2
    li $v0, 9
    syscall

    # save newly allocated array
    sw $v0, ($t4)
    addi $t4, $t4, 4
    # increment
    addi $t3, $t3, 1

    j loop

# Iterate vertically
outer_loop:
    bge $t1, $s1, exit

    lw $t8, ($t6)   # $t8 contains address of first entry in a row

    j inner_loop
outer_loop_2:
    # increment
    addi $t6, $t6, 4    # Move to next row
    addi $t1, $t1, 1
    j outer_loop

exit:
    # return
    jr $t0

# Iterate horizontally
inner_loop:
    bge $t2, $s2, outer_loop_2

    # Scalar multiply
    # Read from original matrix
    lw $t7, ($t5)
    mul $t7, $t7, $s3   # multiply by the scalar

    # and store that to the new array
    sw $t7, ($t8)   # $t8 is the address to the row

    # move pointers
    addi $t8, $t8, 4
    addi $t5, $t5, 4

    # increment j
    addi $t2, $t2, 1
    j inner_loop

# End of function

main:
    # Columns
    li $s1, 5
    # Rows
    li $s2, 3
    # Scalar
    li $s3, 4

    # Allocate
    mul $a0, $s1, $s2
    li $v0, 9
    syscall
    # Address of first element in matrix
    move $s0, $v0
    move $t2, $s0   # going through the array when input

    # input data
    mul $t0, $s1, $s2

    # initialize counter variable
    li $t1, 0
input:
    bge $t1, $t0, end_input

    li $v0, 5
    syscall
    # store input int
    sw $v0, ($t2)
    addi $t2, $t2, 4

    addi $t1, $t1, 1

    j input
end_input:

    jal matrix_scalar_multiply

    # Exit
    li $v0, 10
    syscall
