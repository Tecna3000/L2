
var field;
var paddle1;
var paddle2;
function buttons(){
  p1_up = false;
  p1_down = false;
  p2_up= false;
  p2_down = false;
}


function Ball() {
  this.id = "ball";
  this.x = 0;
  this.y = 0;
  this.vx = 23;
  this.vy = 23;
}

function Paddle1(){
  var image = document.querySelector("#paddle1");
  this.id = "paddle1";
  this.x = 0;
  this.y = 100;
  this.width = image.width;
  this.height = image.height;

}

function Paddle2(){
  var image = document.querySelector("#paddle2");
  this.id = "paddle2";
  this.x = 1350;
  this.y = 100;
  this.width = image.width;
  this.height = image.height;

}
function place_objects(objects) {
  for (let object of objects) {
    let element = document.getElementById(object.id);
    element.style.left = object.x + "px";
    element.style.top = object.y + "px";
  }
}

function update() {
  ball.x += ball.vx;
  ball.y += ball.vy;
  body = document.body.getBoundingClientRect();
  // if(ball.x < 0 || ball.x > body.width -64){
  //   ball.vx=- ball.vx;
  // }
  //tache6
  if(ball.y < 0 || ball.y >body.height -64){
    ball.vy =- ball.vy;
  }
  if(ball.x < paddle1.width && (ball.y > paddle1.y  && ball.y < paddle1.y + paddle1.height+(ball.height/2))){
    ball.vx=- ball.vx;

  }
  if(ball.x < paddle2.width && ( ball.y >paddle2.y && ball.y < paddle2.y + paddle2.height+(ball.height/2))){
    ball.vx =- ball.vx;
  }

  if (buttons.p1_up == true && paddle1.y>0 ){
    paddle1.y -= 10;
  }
  if (buttons.p1_down == true && paddle1.y < body.height - paddle1.height){
    paddle1.y += 10;
  }

  if (buttons.p2_up == true && paddle2.y>0 ){
    paddle2.y -= 10;
  }
  if (buttons.p2_down == true && paddle2.y < body.height-paddle2.height){
    paddle2.y += 10;
  }

  place_objects([ball]);
  place_objects([paddle1]);
  place_objects([paddle2]);
}

let ball;

function init() {
  ball = new Ball();
  paddle1 = new Paddle1();
  paddle2 = new Paddle2();
  setInterval(update, 100);
}
function track_player_input(event) {
  if(event.type == "keydown") {
    switch(event.key) {
      case "a": buttons.p1_up = true; break;
      case "q": buttons.p1_down = true; break;
      case "p": buttons.p2_up = true; break;
      case "m": buttons.p2_down = true; break;
  }
 } else if(event.type == "keyup") {
   switch(event.key) {
      case "a": buttons.p1_up = false; break;
      case "q": buttons.p1_down = false; break;
      case "p": buttons.p2_up = false; break;
      case "m": buttons.p2_down = false; break;
  }
 }
}
document.addEventListener("keydown", track_player_input);
document.addEventListener("keyup", track_player_input);
