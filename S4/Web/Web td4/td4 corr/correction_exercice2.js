"use strict";

document.addEventListener('DOMContentLoaded', bet_for_toffees);


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
