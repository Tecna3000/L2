#!/usr/bin/env python3
"""
Read a pushdown automaton and a word, returns:
 * ERROR if non deterministic
 * YES if word is recognized
 * NO if word is rejected
"""

from automaton import StackAutomaton, EPSILON, error, warn
import sys
import pdb  # for debugging


##################

def is_deterministic(a: 'StackAutomaton') -> bool:
    for (source, letter, head, push, dest) in a.transitions:
        for (source1, letter1, head1, push1, dest1) in a.transitions:
            if source == source1 and letter == letter1 and head == head1 and dest != dest1:
                return False
            elif source == source1 and head == head1 and letter == EPSILON and letter1 != EPSILON:
                return False

    return True



##################


def recognizes(a:'StackAutomaton', word:str)->bool:
    
  state = a.initial.name
  stack = a.initial_stack
  word += "%"
  found = True
  for i in range (len(word)) :
     if not found:
         return False
     found = False
     
     for (source, letter, head, push, dest) in a.transitions :
       if (source == state) and (letter != word[i]) and (stack[0] == head) and (letter == "%") and (dest in a.acceptstates or source == dest):
         test = "%"
         while test == "%":
             for (source, letter, head, push, dest) in a.transitions :
                 test = "a"
                 if (source == state) and (letter == "%") and (stack[0] == head):
                    test = "%"
                    if push == "%":
                      stack = stack[1:]
                      state = dest
                      found = True
                      break
                    stack = stack[1:]
                    stack = ''.join(push) + stack
                    state = dest
                    found = True
                    break
       if (source == state) and (letter == word[i]) and (stack[0] == head):
         if push == "%":
           stack = stack[1:]
           state = dest
           found = True
           break
         stack = stack[1:]
         stack = ''.join(push) + stack
         state = dest
         found = True
         break
   
  return state in a.acceptstates


####################
if __name__ == "__main__":
    if len(sys.argv) != 3:
        usagestring = "Usage: {} <automaton-file.ap> <word-to-recognize>"
        error(usagestring.format(sys.argv[0]))

    automatonfile = sys.argv[1]
    word = sys.argv[2]

    a = StackAutomaton("dummy")
    a.from_txtfile(automatonfile)
    print(a)

    if not is_deterministic(a):
        print("ERROR: the automaton is not deterministic")
    elif recognizes(a, word):
        print("YES: the word is reconized")
    else:
        print("NO: the word is not reconized")
