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

### Assignments
* Left operand must be a variable
* You can do things like `a = 2 + 2 * 3` but it will be evaluated from the
beginning
* Constants must all be integers

### Operators
* +, -, ==, !=, >, >=, <, <= are the operators that you can use
* `2 + 2 * 3` will be evaluated from the beginning
