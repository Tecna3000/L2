 #!/usr/bin/env python3
"""
Read an automaton and a word, returns:
 * YES if word is recognized
 * NO if word is rejected
Determinises the automaton if it's non deterministic
"""

from typing import Set, List
from automaton import Automaton, EPSILON, State, error, warn
import sys
import pdb # for debugging

##################

def is_deterministic(a:Automaton)->bool:
    for(source,symb,dest) in a.transitions:
          for (source1, symb1, dest1) in a.transitions:
             if source == source1 and symb == symb1 and dest!=dest1 or symb == EPSILON  :
               return False
    return True
      
##################
  
def recognizes(a:Automaton, word:str)->bool:
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

def determinise(a:Automaton):
    remove_epsilon_transitions(a)
    trans_reduction(a)
  
##################

def epsilon_transitions(a:Automaton):
    list_epsilon_transitions=[]
    for(source,symb,dest) in a.transitions:
           if symb == EPSILON  :
              trans = source,symb,dest
              list_epsilon_transitions.append( trans)
    return list_epsilon_transitions


#################


def remove_epsilon_transitions (a :Automaton) : 
    transitions = a.transitions
    e_transitions = epsilon_transitions(a)
    for trans in transitions:
        for trans2 in e_transitions:
            if trans[0] == trans2[2]:
             a.add_transition(trans2[0], trans[1], trans[2])
             a.remove_transition(trans2[0], trans2[1], trans2[2])
             a.make_accept(trans[2])
             
#####################

def trans_reduction(a) :
  new_a = Automaton("det")
  new_states = [a.initial.name] 
  while len(new_states) !=0:
    for symb in a.alphabet :
      dest = set([]) 
      for trans in a.transitions :
         if trans[0] in new_states[0] and trans[1]== symb:  
                t =( trans[2] ) 
                dest = dest.union(set([t]))
      if dest!= set ([]):
         new_a.add_transition(str(new_states[0]),symb, str(dest))  
         for state in a.acceptstates:
             if state in dest :
               new_a.make_accept(str(dest))      
         if dest not in  new_states :
             new_states.append(dest)
    new_states.pop(0)
  return new_a          
#####################        
    


            
#####################
if __name__ == "__main__" :
  if len(sys.argv) != 3:
    usagestring = "Usage: {} <automaton-file.af> <word-to-recognize>"
    error(usagestring.format(sys.argv[0]))

  automatonfile = sys.argv[1]  
  word = sys.argv[2]

  a = Automaton("dummy")
  a.from_txtfile(automatonfile)
  if not is_deterministic(a) :
    determinise(a)
  if recognizes(a, word):
    print("YES")
  else:
    print("NO")
