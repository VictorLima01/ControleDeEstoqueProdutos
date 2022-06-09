/*
Tela de Home
*/
let url = window.location.href;
let id = url.split("?")[1].split("&")[0].slice(3)
function startHome(){
    document.getElementById("homeLocation").innerHTML = `<a href="index.html?${id}"><i class="fa-solid fa-house"></i> Home</a>`;
    document.getElementById("produtosLocation").innerHTML = `<a href="../produtos_disponiveis/index.html?${id}"><i class="fa-solid fa-house-medical-circle-check"></i> Produtos disponíveis</a>`
  }