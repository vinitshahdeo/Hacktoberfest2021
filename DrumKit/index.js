var lengthOfSet = document.firstElementChild.lastElementChild.children[1].children.length;


document.addEventListener("keydown",function(event){

  playDrum(event.key);
    buttonAnimation(event.key);
});

while (lengthOfSet >= 0) {

document.querySelectorAll(".drum")[lengthOfSet - 1].addEventListener("click", function() {

var buttonKey = this.innerHTML;

playDrum(buttonKey);

buttonAnimation(buttonKey);
     });

lengthOfSet--;
}



function playDrum(KeyboardOrButtonKey){


  switch (KeyboardOrButtonKey) {
    case "w":
      var crash = new Audio('sounds/crash.mp3');
      crash.play();

      break;

    case "a":
      var kick = new Audio('sounds/kick-bass.mp3');
      kick.play();
      break;


    case "s":
      var snare = new Audio('sounds/snare.mp3');
    snare.play();
      break;

    case "d":
      var tom1 = new Audio('sounds/tom-1.mp3');
      tom1.play();
      break;

    case "j":
      var tom2 = new Audio('sounds/tom-2.mp3');
      tom2.play();
      break;

    case "k":
      var tom3 = new Audio('sounds/tom-3.mp3');
      tom3.play();
      break;

    case "l":
      var tom4 = new Audio('sounds/tom-4.mp3');
      tom4.play();
      break;

    default:
      alert("Invalid Key")


  }
}

function buttonAnimation(pressed){
  var activeButton = document.querySelector("."+pressed);
  activeButton.classList.add("pressed");
  setTimeout(function(){ activeButton.classList.remove("pressed");},100);
}