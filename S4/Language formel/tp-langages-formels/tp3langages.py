#!/usr/bin/env python3
"""
Read a grammar and a word, returns:
 * YES if word is generated
 * NO otherwise
"""

from automaton import Grammar, EPSILON, error, warn
import sys
import pdb  # for debugging


##################

def generates(a: 'Grammar', word: str) -> bool:
    table = []
    for i in range(len(word)):
        cells = []
        for j in range(len(word) - i):
            cells += ["0"]
        table += [cells]

    for row in range(len(word)):
        if row == 0:
            for column in range(len(word)):
                for (source, prod) in a.rules:
                    if not prod:
                        prod = ["%"]

                    if word[column] == prod[0]:
                        if table[row][column] == "0":
                            table[row][column] = source
                        else:
                            table[row][column] += source

        if row == 0:
            continue

        for column in range(len(word) - row):
            row_box1 = 0
            column_box1 = column
            row_box2 = row - 1
            column_box2 = column + 1
            solutions = []

            while row_box1 != row:
                box1 = table[row_box1][column_box1]
                box2 = table[row_box2][column_box2]

                for i in range(len(box1)):
                    for j in range(len(box2)):
                        solutions.append(box1[i] + box2[j])

                row_box1 += 1
                row_box2 -= 1
                column_box2 += 1

            for (source, prod) in a.rules:
                if not prod:
                    prod = ["%"]

                for sol in range(len(solutions)):

                    if solutions[sol] == ''.join(prod):
                        if table[row][column] == "0":
                            table[row][column] = source
                        elif not (source in table[row][column]):
                            table[row][column] += source

    for i in range(len(table)):
        print(table[len(table) - i - 1])
    if table[len(table) - 1][0] == "0":
        return False
    else:
        return True


##################

if __name__ == "__main__":
    if len(sys.argv) != 3:
        usagestring = "Usage: {} <grammar-file.gr> <word-to-recognize>"
        error(usagestring.format(sys.argv[0]))

    grammarfile = sys.argv[1]
    word = sys.argv[2]

    a = Grammar("dummy")
    a.from_txtfile(grammarfile)
    print(a)

    if generates(a, word):
        print("YES: the word is generated")
    else:
        print("NO: the word is not generated")
