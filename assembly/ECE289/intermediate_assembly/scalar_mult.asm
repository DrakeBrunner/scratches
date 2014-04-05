# Calculates the scalar multiplication of a matrix
# vim: ft=mips expandtab sw=4

# m         => $s0
# col       => $s1
# row       => $s2
# scalar    => $s3

j main

# Multiplies and sets the first address of the matrix to $s4
# rtrn addr => $t0
# i         => $t1
# j         => $t2
# ptr_col   => $t3
# ptr_row   => $t4
# ptr_orig  => $t5
matrix_scalar_multiply:
    move $t0, $ra   # Save return address

    mul $t6, $s1, 4 # how many bytes to alloc?
    # Allocate columns
    move $a0, $t6
    li $v0, 9
    syscall
    move $s4, $v0   # Address of first element in matrix

    move $t3, $s4   # for allocating each "row"

    move $t3, $s4   # iterate vertically
    move $t5, $s0   # copy of original matrix

    # init counter vars
    li $t1, 0
    li $t2, 0

# Allocate rows
alloc_loop:
    bge $t1, $s1, outer_loop
    # Allocate rows for each columns
    mul $t6, $s2, 4 # How many byets?
    move $a0, $t6
    li $v0, 9
    syscall

    # save newly allocated array
    sw $v0, ($t3)
    addi $t3, $t3, 4
    addi $t1, $t1, 1    # i++

    j alloc_loop

# Iterate vertically
outer_loop:
    # init
    li $t1, 0
    move $t3, $s4
.L1:
    bge $t1, $s1, exit

    j inner_loop
outer_loop_2:
    # increment
    addi $t3, $t3, 4    # Move to next row
    addi $t1, $t1, 1
    j .L1

exit:
    # return
    jr $t0

# Iterate horizontally
inner_loop:
    li $t2, 0
    lw $t4, ($t3)       # load address of row
.L2:
    bge $t2, $s2, outer_loop_2  # exit inner for loop

    # Scalar multiply
    lw $t7, ($t5)       # Read from original matrix
    mul $t7, $t7, $s3   # multiply by the scalar

    # and store that to the new array
    sw $t7, ($t4)   # $t8 is the address to the row

    # move pointers
    addi $t4, $t4, 4    # of matrix to copy to
    addi $t5, $t5, 4    # of original matrix (copy from)

    addi $t2, $t2, 1    # increment j
    j .L2

# End of function

main:
    # Columns
    li $s1, 5
    # Rows
    li $s2, 3
    # Scalar
    li $s3, 4

    # Allocate
    mul $t6, $s1, $s2
    mul $a0, $t6, 4   # How many byets to allocate?
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
