# brainfuck

A brainfuck interpreter library.
Brainfuck code is first parsed into a simplistic AST.
The AST execution continuously augments a virtual machine
containing register cells and a pointer to the current position.

Brainfuck input and output respects clojure's `*in*` and `*out*`,
for instance:

```clojure
(with-out-str
  (with-in-str "foobar"
    (run "-,+[-.,+]"))) ;; #=> "foobar"
```

The AST is a simple list of vectors, containing either one
or two members. The first member is the character representing
the operation, the second member is only present for the `\[` operation
and contains the branch's code.

The AST interpreter augments a virtual machine implemented as
a clojure persistent hash-map with two keys:

- `reg`: Arbitrary width (grown as needed) register cells
- `pos`: Current cell offset

The initial virtual machine is thus defined as:

```clojure
{:reg [0]
 :pos 0}
```

This library exports three functions:

- `parse`: Given an input brainfuck program, yields a simplistic AST.
- `exec`: Augments a virtual machine with the result of the next operation.
- `run`: Parse and execute a program with an initial virtual machine.

## License

Copyright © 2015 Pierre-Yves Ritschard

Distributed under the MIT License, see LICENSE for details.

