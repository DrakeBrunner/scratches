# Completely copied from:
# https://github.com/brimcfadden/MIPS-practice/blob/master/fib.asm

j main                      # Jump to the main() procedure.

fib:
    blt   $a0, 2, basecase  # if n < 2:

    addi  $t0, $a0, -1      # Calculate n-1.

    addi  $sp, $sp, -8      # Save the variables, because 
    sw    $ra, 4($sp)       # fib(n-1) is about to be called.
    sw    $t0, 0($sp)

    addi  $a0, $t0, 0       # Put fib(n-1) in the argument register
    jal   fib               # Call fib(n-1). Result will be in $v0.

    lw    $t0, 0($sp)       # (n-1)
    addi  $sp, $sp, 4       # Pop the variable off the stack again.
   
    addi  $t0, $t0, -1      # Calculate n-2.

    addi  $sp, $sp, -4      # Save the variables, because 
    sw    $v0, 0($sp)       # fib(n-2) is about to be called.

    addi  $a0, $t0, 0       # Save (n-2) to the argument register and call fib(n-2).
    jal   fib               # Result will be in $v0 again.

    lw    $t1, 0($sp)       # result of fib(n-1)
    lw    $ra, 4($sp)       # Original return address
    addi  $sp, $sp,8        # Pop the variables off the stack.

    add   $v0, $t1, $v0     # Calculate result of fib(n-1) + result of fib(n-2).
    jr    $ra               # Return result by returning to the caller.

basecase:
    addi  $v0, $a0, 0       # The parameter is the return value.
    jr    $ra

main:
    addi  $a0, $zero, 15    # Call fib(15).
    jal   fib               # Result should be in $v0.
