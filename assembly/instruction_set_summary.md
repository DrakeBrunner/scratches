# Summary of instructions for MIPS

## Jump
* j LABEL Unconditionally jump to LABEL
* jal LABEL Unconditionally jump to LABEL, but store where to return to into
  $ra
* jr $REG Unconditionally jump to register address of $REG

## Branch
* blt $a, $b, LABEL Jump to LABEL if $a < $b is true
* bgt $a, $b, LABEL Jump to LABEL if $a > $b is true
* ble $a, $b, LABEL Jump to LABEL if $a <= $b is true
* bge $a, $b, LABEL Jump to LABEL if $a >= $b is true
* beq $a, $b, LABEL Jump to LABEL if $a == $b is true
* bne $a, $b, LABEL Jump to LABEL if $a != $b is true

# Arithmetic
* add $a, $b, $c Set $a = $b + $c
* addi $a, $b, NUM Set $a = $b + NUM
* sub $a, $b, $c Set $a = $b - $c

When you want to subtract an int, for example, `$a = $b - 3`, use `addi $a,
$b, -3` or `subi $a, $b, 3`.

# Lable
LABEL_NAME: creates a label

# Others
* move $a, $b Copies the content of address $b to $a
* li $v0, SYS_CODE loads the system code
    * 1 prints content of `$a0`
    * 4 prints string starting from address `$a0`
    * 5 reads int and store to `$v0`
    * 8 reads string starting from `$a0` for length `$a1`
    * 9 allocates `$a0` amout of memory and store allocated memory to `$v0`
    * 10 exits the program
* syscall executes the system code loaded to $v0
