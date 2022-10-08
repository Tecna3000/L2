
document.addEventListener('DOMContentLoaded',Main);

function Main(event) {

let game1=document.querySelector('#game1');
let game2=document.querySelector('#game2');
let player_input=document.querySelector('#player_input');
let message1=document.querySelector('#message1');
let message2=document.querySelector('#message2');
let try_button=document.querySelector('#try_button');
let restart_button1=document.querySelector('#restart_button1');
let restart_button2=document.querySelector('#restart_button2');
let smaller_button =document.querySelector('#smaller_button');
let bigger_button =document.querySelector('#bigger_button');
let found_button =document.querySelector('#found_button');
let start_game1 =document.querySelector('#start_game1');
let start_game2 =document.querySelector('#start_game2');
let min = 0;
let max = 100;

new_game();

function show(element) {
  element.style.display = "";
}

function hide(element) {
  element.style.display = "none";
}


function new_game() {

  hide(game1);
  hide(game2);
  show(game_selection);
}

start_game1.addEventListener('click',(event)=> {start_player_guess_game()})

let computer_number

   function start_player_guess_game(){
     message1.innerText = "Guess the number between 0 and 100";
     computer_number = Math.floor(Math.random() * (99))+1;
     show(game1);
     hide(game_selection);
     hide(restart_button1);
     player_input.value = "";
   }

restart_button1.addEventListener('click',(event)=> {new_game()})

try_button.addEventListener('click',(event)=> {
  let val=parseInt(player_input.value)
 //console.log(player_input.value)
  if(val>computer_number){
    message1.innerText = "No it's smaller";
  }
  else if(val<computer_number){
    message1.innerText = "No it's bigger";
  }
  else if(val ==computer_number){
    message1.innerText = "Yay you found it!";
    show(restart_button1);}
}
)


start_game2.addEventListener('click',(event)=> {start_computer_guess_game()})

function start_computer_guess_game(){
 number = Math.round ((min+max)/2);
 show(game2);
 hide(game_selection);
 hide(restart_button2);
 message2.innerText = "Think of a number between 1 and 100.\rIs it : " + number + " ?";
 begin();
}
function begin(){
 show(found_button);
 show(smaller_button);
 show(bigger_button);
 show(message2);
}

smaller_button.addEventListener('click',(event)=> {smaller()})
function smaller(){
  start_computer_guess_game()
  if (min!=max){
  max = number;
  number = Math.round ((min+max)/2);
  message2.innerText = "Is it : " + number + " ?";
 }
}

bigger_button.addEventListener('click',(event)=> {bigger()})
function bigger(){
  start_computer_guess_game();
  if (min!=max){
  min = number;
  number = Math.round ((min+max)/2);
  message2.innerText = "Is it : " + number + " ?";
 }
}
found_button.addEventListener('click',(event)=> {found()})
function found(){
  show(restart_button2);
  hide(message2);
  hide(bigger_button);
  hide(smaller_button);
  hide(found_button);
  min = 0;
  max = 100;
}
restart_button2.addEventListener('click',(event)=> {new_game()})
}
