"use strict";

document.addEventListener('DOMContentLoaded', main);

function main (event) {

  const counter = document.querySelector('#counter');
  const result = document.querySelector('#result');
  const start = document.querySelector('#start');

  const counter2 = document.querySelector('#counter2');
  const result2 = document.querySelector('#result2');
  const start2 = document.querySelector('#start2');

  const counter3 = document.querySelector('#counter3');
  const result3 = document.querySelector('#result3');
  const start3 = document.querySelector('#start3');

  fizz_buzz(counter, result, start, "Fizz", "Buzz");
  fizz_buzz(counter2, result2, start2, "Ding", "Dong");
  fizz_buzz(counter3, result3, start3, "Arg", "Oof");
}

function fizz_buzz(counter, result, start, fizz, buzz) {

  let state = {started: false};

  monitor_state(state, start);
  update_display(state, counter, result, fizz, buzz);
}

async function monitor_state(state, start) {
  while (true) {
    await user_click(start);
    if (state.started) {
      state.started = false;
      start.innerText = 'Start';
    } else {
      state.started = true;
      start.innerText = 'Stop';
    }
  }
}

function user_click(start_button) {
  let p = new Promise ( (accept_callback, reject_callback) => {
    start_button.addEventListener('click', accept_callback);
  });
  return p;
}

async function update_display(state, counter, result, fizz, buzz) {

  let value;

  while (true) {
    await one_second();

    if (state.started) {
      value = parseInt(counter.innerText);
      value = value + 1;
      if ((value % 3 === 0) && (value % 5 === 0)) {
        result.innerText = `${fizz}-${buzz}`;
      } else if (value % 3 == 0) {
        result.innerText = `${fizz}`;
      } else if (value % 5 == 0) {
        result.innerText = `${buzz}`;
      } else {
        result.innerText = " ";
      }
      counter.innerText = value;
    }
  }
}


function one_second() {
  let p = new Promise ( (accept_callback, reject_callback) => {
    setTimeout(accept_callback, 1000);
  });
  return p;
}
