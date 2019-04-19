# Java Expression Parser

An expression parser I made for fun in a couple of hours

## Usage

Invoke `Driver`'s `main` function, and enter an expression when prompted for input.

Example input:
```
Expression: sin(π / 6) * max(1, 5 + 3 * 7) ^ 2
```

Example output:
```
Parsed: (sin((π / 6.0)) * (max(1.0, (5.0 + (3.0 * 7.0))) ^ 2.0))
Value: 337.99999999999994
```

## Technical Details

- Uses the [Shunting-yard algorithm](https://en.wikipedia.org/wiki/Shunting-yard_algorithm) to parse expressions with precedence and associativity taken into account
- Uses functional programming to implement operators and functions

## Features
- Not just operators: also supports constants and functions! (See `Driver.java` for a list of defaults)
- Has a custom stack implementation that I built for practice
