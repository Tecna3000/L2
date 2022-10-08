var button = document.querySelector('input');
//var txt = document.querySelector('p');
button.addEventListener('click', update);
function update() {
  if (button.value === 'Start') {
    button.value = 'Stop';
    //txt.textContent = 'La machine est démarrée !';
  } else {
  button.value = 'Start';
    //txt.textContent = 'La machine est arrêtée.';
  }
}
