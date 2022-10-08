"use strict";

document.addEventListener('DOMContentLoaded', main);

function main() {
  bet_for_toffees();
  sum_5_numbers();
  blink_text('Magic!');
}


async function bet_for_toffees() {

  const red = document.querySelector('#red');
  const blue = document.querySelector('#blue');
  const gains = document.querySelector('#gains');
  const p = Math.random();
  console.log(p);

  while (true) {
    let n = Math.random();
    console.log(n);
    let pill_color = await get_pill_color_from_user_click();
    resolve_gains(pill_color, n, p);
  }

  function get_pill_color_from_user_click() {
    let p = new Promise( (accept_callback, reject_callback) => {
      red.addEventListener('click', (event) => accept_callback('red'));
      blue.addEventListener('click', (event) => accept_callback('blue'));
    });
    return p;
  }

  function resolve_gains(pill_color, n, p) {
    if (pill_color === 'red') {
      if (n<=p) {
        gain_toffee(gains);
      } else {
        lose_toffee(gains);
      }
    } else {
      if (n>p) {
        gain_toffee(gains);
      } else {
        lose_toffee(gains);
      }
    }
  }

  function gain_toffee() {
    let gain, _;
    [gain, _] = gains.innerText.split(" ")
    gains.innerText = (parseInt(gain)+1) + " caramels";
  }

  function lose_toffee() {
    let gain, _;
    [gain, _] = gains.innerText.split(" ")
    gains.innerText = (parseInt(gain)-1) + " caramels";
  }
}


async function sum_5_numbers() {

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

  function update_sum(i, j) {
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


async function blink_text(text) {

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
