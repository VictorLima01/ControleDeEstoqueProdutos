function buildPacientes(){

  /*
   Carrosel
  */
  
   /** */
  let url = `http://localhost:8080/api/pessoas`;
    let meusPacientes = "";


    $.ajax({
      type: "GET",
      url: url,
      crossDomain: true,
      headers: { 
          'Content-Type': 'application/json',
          'Accept': '*/*',
      },
      dataType:"json",
      success: function(result){
          console.log(result);
          for (var key in result) {
              var obj = result[key];
              if(obj.listasProdutos.length > 0){
                for(var keyProduto in obj.listasProdutos){
                  var objProduto = obj.listasProdutos[keyProduto];
                  let arr = []
                  arr.push(objProduto.nome)
    
                  meusPacientes += `
                  <div class="gallery">
                  <div class="content">
                  <i style="width: 100%;
                  padding: 5px;
                  color: red;" class="fa-solid fa-rectangle-xmark" onclick="deletarPaciente(${obj.id})"></i>
                      <img src="assets/paciente.png">
                      <h3>${obj.nome}</h3>
                      <p>Email: ${obj.email}</p>
                      <p>Id: ${obj.id}</p>
                      <h6>Produtos alocados: ${arr}</h6>
                      <ul>
                          <li><i class=""></i></li>
                          <li><i class=""></i></li>
                          <li><i class=""></i></li>
                          <li><i class=""></i></li>
                          <li><i class=""></i></li>
                      </ul>
                      <button class="buy-1">Paciente está usando um produto</button>
                  </div>
              </div>
                  `
                  
              }
              }else{
              
                  meusPacientes += `
                  <div class="gallery">
                  <div class="content">
                  <i style="width: 100%;
                  padding: 5px;
                  color: red;" class="fa-solid fa-rectangle-xmark" onclick="deletarPaciente(${obj.id})"></i>
                      <img src="assets/paciente.png">
                      <h3>${obj.nome}</h3>
                      <p>Email: ${obj.email}</p>
                      <p>Id: ${obj.id}</p>
                    
                      <ul>
                          <li><i class=""></i></li>
                          <li><i class=""></i></li>
                          <li><i class=""></i></li>
                          <li><i class=""></i></li>
                          <li><i class=""></i></li>
                      </ul>
                      <button class="buy-1">Paciente não utiliza nenhum produto</button>
                  </div>
              </div>
                  `
                  
              
              }
              
          document.getElementById("pacientes").innerHTML = meusPacientes
        }
      }
  });
}

function deletarPaciente(idPaciente){
  console.log("Chaamou")
  $.ajax({
    type: "DELETE",
    url: `http://localhost:8080/api/pessoas/${idPaciente}`,
    crossDomain: true,
    headers: { 
        'Content-Type': 'application/json',
        'Accept': '*/*',
    },
    dataType:"json",
    success: function(result){
        console.log("Usuário: " + idPaciente + " deletado");
        document.location.reload(true);
    }
});
}