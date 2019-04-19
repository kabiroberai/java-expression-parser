# Java Expression Parser

An expression parser I made for fun in a couple of hours

## Technical Details

- Uses the [Shunting-yard algorithm](https://en.wikipedia.org/wiki/Shunting-yard_algorithm) to parse expressions with precedence and associativity taken into account
- Uses functional programming to implement operators and functions

## Features
- Not just operators: also supports constants and functions! (See `Driver.java` for a list of defaults)
- Has a custom stack implementation that I built for practice
