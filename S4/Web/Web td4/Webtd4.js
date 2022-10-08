document.addEventListener('DOMContentLoaded',sum_5_numbers);

function Main(event) {
  let text=document.querySelector('#text');
  let next=document.querySelector('#next');
  next.addEventListener('click',(event)=> { sum_5_numbers()})
}

  //next.addEventListener('click',(event)=> {update_sum()})


/*function update_sum(){

}*/


function sum_5_numbers() {
  let j;
  let i=0;
  let sum = 0;
  let sum_display = "";
  const div = document.querySelector("#calcul");
  let text=document.querySelector('#text');
  let next=document.querySelector('#next');
  next.addEventListener('click',(event)=>  {
    // j = parseInt(prompt("Entrez un nombre: "));
    j = parseInt(text.value);
    i++;
    if (sum_display != "" && i<=5) {
      sum_display += "+";
    }
    if (i<=5) {
        sum_display = sum_display + j;
    }

    div.innerText = sum_display;
    sum += j;
    if (i==5){
    sum_display += "=" + sum;
    div.innerText = sum_display;
    }
  });
}

/*function upgrade(next){
  let p = new Promise ( (accept_callback, reject_callback) => {
    next.addEventListener('click', accept_callback);
  });
  return p;
}*/
