"use_strict"
let search_button;
let input ;
let city_name ;
let heat ;
let wind_speed ;
let weather_icons ;

let url_parser = require('url');
let http = require('http');
let fetch = require("cross-fetch")
let fs = require('fs');
const template = fs.readFileSync('tp4.html').toString();
let incity;
console.log("aa")
let server = http.createServer(callback);
async function onserver(){
  let promise =  await( fetch("http://api.weatherstack.com/current?access_key=cb25d333a98031bccc1d50e93fb278ba&query="+process.argv[2]))
  let res =promise.json();
  console.log(res)
  res.then(response=>{
     // city_name :response.location.name;
     // weather_icons : response.current.weather_icons[0];
     // heat : "Temperature : " + response.current.temperature + " °C";
     // wind_speed : "Wind speed :  " + response.current.wind_speed + " Kph";

  })

}
onserver();

let server = http.createServer((req, res) => {
  let url = url_parser.parse(req.url, true);
  incity = url.query.city;
  console.log(url);
  res.writeHead(200, {'content-type':'text/html'});
  res.end(template)
  let module = require('nom du module');

});
server.listen(3000);
// /Documents/Web_Tp5$ node node.js
// http://127.0.0.1:3000/node.js
