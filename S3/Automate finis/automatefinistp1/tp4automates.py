#!/usr/bin/env python3
"""
Read a regular expression and returns:
 * YES if word is recognized
 * NO if word is rejected"""

from typing import Set, List
from automaton import Automaton, EPSILON, State, error, warn, RegExpReader
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

def determinise(a:Automaton):
    remove_epsilon_transitions(a)
    trans_reduction(a)
  
##################

def epsilon_transitions(a:Automaton):
    list_epsilon_transitions=[]
    for(source,symb,dest) in a.transitions:
           if symb == EPSILON  :
              trans = source,symb,dest
              list_epsilon_transitions.append(trans)
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


##################


def kleene(a1:Automaton)->Automaton:
  a1star = a1.deepcopy()
  a1star.name = "a1star"
  
  for state in a1star.acceptstates:
      a1star.add_transition(state,EPSILON,a1star.initial.name)
  a1star.add_transition(nouvel_etat(a1),EPSILON,a1.initial.name)
  a1star.initial = a1star.statesdict[nouvel_etat(a1)]
  a1star.make_accept(nouvel_etat(a1))

  return a1star


##################

def nouvel_etat(a1:Automaton)->str:
    """Trouve un nouveau nom d'état supérieur au max dans `a1`"""
    maxstate = -1
    for a in a1.states :
        try :
            maxstate = max(int(a), maxstate)
        except ValueError:
            pass
    return str(maxstate + 1)
##################

def concat(a1:Automaton, a2:Automaton)->Automaton:
  a1star_a2 = a1.deepcopy()
  a1star_a2.name = "a1star_a2"
  nom_nouvel_etat = nouvel_etat(a1star_a2)
  
  for trans in a2.transitions:
      if trans[0] == a2.initial.name:        
          a1star_a2.add_transition(nom_nouvel_etat, trans[1], str(int(trans[2]) + int(nom_nouvel_etat)))
      else :
          a1star_a2.add_transition(str(int(trans[0]) +int(nom_nouvel_etat)),trans[1], str(int(trans[2]) +int(nom_nouvel_etat)))
  
  for s in a2.states :               
     if s in a2.acceptstates : 
        a1star_a2.make_accept(str(int(s)+ int(nom_nouvel_etat))) 
  for s in a1star_a2.acceptstates:
      if int(s[0])< int(nom_nouvel_etat):
          a1star_a2.add_transition(s, EPSILON, nom_nouvel_etat)
  for s in a1star_a2.acceptstates :
    if int(s[0]) < int(nom_nouvel_etat):
        a1star_a2.make_accept(s, False)
  return a1star_a2 

##################

def union(a1:Automaton, a2:Automaton)->Automaton:
  a1star_a2_or_a3 = a1.deepcopy()
  a1star_a2_or_a3.name = "a1star_a2_or_a3"
  nom_nouvel_etat = nouvel_etat(a1star_a2_or_a3)
  for s in a2.transitions:
      if s[0] == a2.initial.name:
          a1star_a2_or_a3.add_transition(nom_nouvel_etat, s[1], str(int(s[2])+ 1 + int(nom_nouvel_etat)))
      else:
          a1star_a2_or_a3.add_transition(str(int(s[0])+ 1 + int(nom_nouvel_etat)),s[1], str(int(s[2])+ 1 + int(nom_nouvel_etat) ))
  nom_nouvel_etat2 = nouvel_etat(a1star_a2_or_a3)
  a1star_a2_or_a3.add_transition(nom_nouvel_etat2,EPSILON,a1star_a2_or_a3.initial.name)
  a1star_a2_or_a3.add_transition(nom_nouvel_etat2,EPSILON,nom_nouvel_etat)
  a1star_a2_or_a3.initial=a1star_a2_or_a3.statesdict[nom_nouvel_etat2]
  
  for s in a2.states :
      if s in a2.acceptstates :
          a1star_a2_or_a3.make_accept(str(int(s)+ int(nom_nouvel_etat)))
  

  return a1star_a2_or_a3
  
##################
   
def regexp_to_automaton(re:str)->Automaton:
  """
  Moore's algorithm: regular expression `re` -> non-deterministic automaton
  """
  postfix = RegExpReader(regexp).to_postfix()
  stack:List[Automaton] = []
  
  for p in postfix:
      if p!= "*" and p != "+" and p!= "." :
          a = Automaton(p)
          a.add_transition("0", p ,"1")
          a.make_accept("1")
          stack.append(a)
          
      elif p == "+":
          a1 = stack.pop();
          a2 = stack.pop();
          stack.append(union(a2, a1))
         
          
      elif p =="*":
         a = stack.pop();
         stack.append(kleene(a))
  
      elif p == ".":
          a1 = stack.pop();
          a2 = stack.pop();
          stack.append(concat(a2, a1))
   
  return stack[0]
  
##################

if __name__ == "__main__" :

  if len(sys.argv) != 3:
    usagestring = "Usage: {} <regular-expression> <word-to-recognize>"
    error(usagestring.format(sys.argv[0]))

  regexp = sys.argv[1]  
  word = sys.argv[2]

  a = regexp_to_automaton(regexp)
  determinise(a)
  if recognizes(a, word):
    print("YES")
  else:
    print("NO")

