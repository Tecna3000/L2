#!/usr/bin/env python3
"""
Read an automaton and a word, returns:
 * ERROR if non deterministic
 * YES if word is recognized
 * NO if word is rejected
"""

from automaton import Automaton, EPSILON, error, warn
import sys
import pdb # for debugging

##################

def is_deterministic(a:'Automaton')->bool:
  for(source,symb,dest) in a.transitions:
      for (source1, symb1, dest1) in a.transitions:
         if source == source1 and symb == symb1 and dest!=dest1 or symb == EPSILON  :
           return False
  return True
  
##################
  
def recognizes(a:'Automaton', word:str)->bool:
  this_state = str(a.initial) 
  if word == EPSILON and this_state in a.acceptstates:  
      return True
  for l in word :
      if l not in a.statesdict[this_state].transitions:
         return False
      this_state = str(list(a.statesdict[this_state].transitions[l])[0])
  if (this_state not in a.acceptstates):
      return False
  return True 

##################

if __name__ == "__main__" :
  if len(sys.argv) != 3:
    usagestring = "Usage: {} <automaton-file.af> <word-to-recognize>"
    error(usagestring.format(sys.argv[0]))

  automatonfile = sys.argv[1]  
  word = sys.argv[2]

  a = Automaton("dummy")
  a.from_txtfile(automatonfile)
       

  if not is_deterministic(a) :
    print("ERROR")
  elif recognizes(a, word):
    print("YES")
  else:
    print("NO")

