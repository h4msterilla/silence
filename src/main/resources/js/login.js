console.log("loading login.js");

async function login(){
  var login_form = document.getElementById('username');
  var pass_form = document.getElementById('password');
  var mess = document.getElementById('mess');

  if(!login_form.value){
    mess.innerHTML += " - plz set username!";
    return;
  }
  if(!pass_form.value){
    mess.innerHTML += " - plz set password!";
    return;
  }

  mess.innerHTML = "login page";

  var userData = {
    username: login_form.value,
    password: pass_form.value
  }

  var respond = await fetch("/login",{
    method: "post",
    body: JSON.stringify(userData)
  });
  var respondText = await respond.text();
  console.log(respondText);

  mess.innerHTML += " - " + respondText;
}
