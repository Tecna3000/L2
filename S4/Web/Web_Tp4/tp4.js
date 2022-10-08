"use strict";
document.addEventListener('DOMContentLoaded',Main);

function Main(event) {
  const search_button=document.querySelector('#search_button');
  const input = document.querySelector('#input');
  const city_name = document.querySelector('#city_name');
  const heat = document.querySelector('#heat');
  const wind_speed = document.querySelector('#wind_speed');
  const weather_icons = document.querySelector('#weather_icons');

  get_weather_data();

  async function get_weather_data (){
    while(true){
      let city = await user_input();
       await fetch("http://api.weatherstack.com/current?access_key=cb25d333a98031bccc1d50e93fb278ba&query=" + city)
      .then(function (response){
        return response.json();
      })
      .then(function(data){
         city_name.innerHTML = city;
         weather_icons.src = data.current.weather_icons[0];
         weather_icons.alt= data.current.weather_descriptions[0];
         heat.innerHTML = "Temperature : " + data.current.temperature + " °C";
         wind_speed.innerHTML = "Wind speed :  " + data.current.wind_speed + " Kph";
         input.value="";

      })
   }

  }

  function user_input() {
    let prom = new Promise ( (accept_callback, reject_callback) => {
      search_button.addEventListener('click',(event) => {
                  event.preventDefault()
                  accept_callback(input.value);

              });

    });
    return prom;
 }
}
