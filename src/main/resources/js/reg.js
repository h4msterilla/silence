console.log("loading reg.js");

async function regist(){
  var login_form = document.getElementById('username');
  var pass_form = document.getElementById('password');
  var pass_apply_form = document.getElementById('pass_apply');
  var mess = document.getElementById('mess');

  mess.innerHTML = "registration";

  if(!login_form.value){
    mess.innerHTML += " - plz set username!";
    return;
  }
  if(!pass_form.value){
    mess.innerHTML += " - plz set password!";
    return;
  }
  if(pass_form.value != pass_apply_form.value){
    mess.innerHTML += " - passwords are not same";
    return;
  }

  var userData = {
    username: login_form.value,
    password: pass_form.value
  }

  var respond = await fetch("/reg",{
    method: "post",
    body: JSON.stringify(userData)
  });
  var respondText = await respond.text();
  //console.log(respondText);

  mess.innerHTML += " - " + respondText;
}
