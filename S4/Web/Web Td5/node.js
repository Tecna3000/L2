"use strict";
document.addEventListener('DOMContentLoaded',Main);
function Main(){
  let messages = ["Meriem", "Lina", "Clément", "Sofiane"];
  let text ="Malek";
  gen_html(messages);
  add_message(messages, text);
  gen_html(messages);
  delete_message(messages,2);
  gen_html(messages);

}

function gen_html(messages){

  let div = document.createElement("div");
  let ul = document.createElement("ul");
  for(let i=0; i< messages.length; i++){
    let li = document.createElement("li");
     li.innerText = messages[i];
     ul.appendChild(li);

  }
  document.body.appendChild(div);
  div.appendChild(ul);
}

function add_message(messages, text) {
  // messages(string.substring(debut,fin);
  //decodeURI(url)
  messages.push(text)
  //messages[messages.length] = text; //celle là aussi marche
}

function delete_message(messages, index){
  messages.splice(index, 1);
}

let http = require('http');
http.createServer(function (req, res) {
  if (req.method == 'GET' && req.url == '/bonjour') {
    res.writeHead(200, {'Content-Type': 'text/html'});
    res.end('<h1>Bonjour</h1>')
  }
  res.end();
}).listen(3000);
