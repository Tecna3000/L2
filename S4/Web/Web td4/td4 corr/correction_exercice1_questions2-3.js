"use strict";

// Version with async/await

document.addEventListener('DOMContentLoaded', main);


function main() {
  // both of the function below run asynchronously
  // without blocking each other despite
  // both involving an infinite loop
  sum_5_numbers();
  blink_text('Magic!');
}

async function sum_5_numbers () {

  const number = document.querySelector('#number');
  const next = document.querySelector('#next');
  const sum_display = document.querySelector('#sum');

  let j;

  while (true) {
    let sum = 0;
    for (let i=0; i<5; i++) {
      j = await get_next_user_number();
      update_sum(i, j);
  }

  function update_sum (i, j) {
    if (i > 0) {
        sum_display.innerText  = sum_display.innerText + ' + ' + j;
      } else {
        sum_display.innerText  = '' + j;
      }
      sum = sum + j;
      number.value = "";
    }
    sum_display.innerText = sum_display.innerText + ' = ' + sum;
  }

  function get_next_user_number() {
    let p = new Promise( (accept_callback, reject_callback) => {
      next.addEventListener('click', (event) => {
        let user_number = parseInt(number.value);
        accept_callback(user_number);
      });
    });
    return p;
  }
}


async function blink_text (text) {

  let d = document.createElement("div");
  d.innerText = text;
  d.style.display = 'block';
  document.querySelector('body').appendChild(d);
  let div_visible = true;

  while (true) {
    await one_second();
    if (div_visible === true) {
      div_visible = false;
      d.style.display = 'none';
    } else {
      div_visible = true;
      d.style.display = 'block'
    }
  }
    
  function one_second() {
    let p = new Promise( (accept_callback, reject_callback) => {
      setInterval(accept_callback, 1000);
    });
    return p;
  }
}
