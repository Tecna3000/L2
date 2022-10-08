#!/usr/bin/env python3
"""
Applies Kleene's star, concatenation and union of automata.
"""

from automaton import Automaton, EPSILON, State, error, warn
import sys
import pdb # for debugging

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
            maxstate = max(int(a),maxstate)
        except ValueError:
            pass
    return str(maxstate + 1)
##################

def concat(a1:Automaton, a2:Automaton)->Automaton:
  a1star_a2 = a1star.deepcopy()
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

if __name__ == "__main__" :
  if len(sys.argv) != 3:
    usagestring = "Usage: {} <automaton-file1.af> <automaton-file2.af>"
    error(usagestring.format(sys.argv[0]))

  # First automaton, argv[1]
  a1 = Automaton("dummy")
  a1.from_txtfile(sys.argv[1])
  a1.to_graphviz(a1.name+".gv")
  print(a1)

  # Second automaton, argv[2]
  a2 = Automaton("dummy")
  a2.from_txtfile(sys.argv[2])
  a2.to_graphviz(a2.name+".gv")
  print(a2)
    
  a1star = kleene(a1)
  print()
  print(a1star)
  a1star.to_graphviz("a1star.gv")

  a1a2 = concat(a1, a2)
  print()
  print(a1a2)
  a1a2.to_graphviz("a1a2.gv")

  a1ora2 = union(a1, a2)
  print()
  print(a1ora2)
  a1ora2.to_graphviz("a1ora2.gv")

