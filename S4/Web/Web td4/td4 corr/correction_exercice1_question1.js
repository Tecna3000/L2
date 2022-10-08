"use strict";

// Que fait le code de l'énoncé: demande à l'utilisateur d'entrer 
// cinq nombres et affiche l'opération et son résultat

// Meilleurs noms pour f, s et ch :
//  f: sum_5_numbers
//  s: sum
// ch: sum_string ou sum_display

document.addEventListener('DOMContentLoaded', sum_5_numbers);

function sum_5_numbers (event) {

  const number = document.querySelector('#number');
  const next = document.querySelector('#next');
  const sum_display = document.querySelector('#sum');

  let j;
  let n = 0;
  let sum = 0;

  next.addEventListener('click', update_sum);


  function update_sum(event) {
    if (n < 5) {
      j = parseInt(number.value);
      if (n === 0) {
        sum_display.innerText = "" + j;
      } else {
        sum_display.innerText = sum_display.innerText + ' + ' + j;
      }
      sum = sum + j;
      number.value = "";
      n = n + 1;
    }
    if (n == 5) {
      sum_display.innerText = sum_display.innerText + ' = ' + sum;
      n=0;
    }
  }
}


