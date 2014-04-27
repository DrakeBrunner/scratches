# Compiler

## Specifications
### Language
The language is defined to be similar to LISP. I defined so in order for the
parsing to be less tedious.

    
    <code>
        <loop type="do-while">
            <cond>
                <o>&lt;</o>
                    <v>a</v>
                    <c>20</c>
            </cond>

            <body>
                <setq>
                    <v>a</v>
                    <o>+</o>
                        <v>a</v>
                        <c>2</c>
                </setq>
            </body>
        </loop>
    </code>

This code is the translation of the following C code snippet.

    do {
        a = a + 2;
    } while (a < 20);

### Variables
* You can't have more than 8 variables

### Assignments
* Left operand must be a variable
* Constants must all be integers

### Operators
* +, -, ==, !=, >, >=, <, <= are the operators that you can use
* Operands must be either constant or variable
* Combination of constant and variable is valid (`a + 2`)
* Variables must come first (`a < 20` instead of `20 > a`)
* You can't subtract variables. Only constants
