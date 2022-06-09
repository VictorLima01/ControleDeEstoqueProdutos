function buildProducts(){
    /*
        bUILD SELECT
    */
        let meusPacientes = `<option value="" selected>Selecione um paciente para poder alocar um produto</option>`;

        $.ajax({
            type: "GET",
            url: `http://localhost:8080/api/pessoas`,
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
      
                    meusPacientes += `
                    <option value="${obj.id}">${obj.nome}</option>
                    `
                    
                }
                document.getElementById("idPaciente").innerHTML = meusPacientes
            }
        });
      
   /*
   
   */
    let url = `http://localhost:8080/api/produtos`;
    let meusProdutos = "";

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
                let alocado = obj.alocado;
                let textAlocado = "";

                if(alocado){
                    textAlocado = "Produto não disponível"
                    meusProdutos += `
                <div class="gallery">
                <div class="content">
                <i style="width: 100%;
                  padding: 5px;
                  color: red;" class="fa-solid fa-rectangle-xmark" onclick="deletarProduto(${obj.id})"></i>
                    <img src="assets/produto-sem-imagem.png">
                    <h3>${obj.nome}</h3>
                    <p>Número Lote: ${obj.numeroLote}</p>
                    <h6>${textAlocado}</h6>
                    <ul>
                        <li><i class=""></i></li>
                        <li><i class=""></i></li>
                        <li><i class=""></i></li>
                        <li><i class=""></i></li>
                        <li><i class=""></i></li>
                    </ul>
                    <button style="background-color: grey;" class="buy-1" disabled>Alocar Produto</button>
                </div>
            </div>
                `
                }else{
                    textAlocado = "Produto disponível"
                    meusProdutos += `
                <div class="gallery">
                <div class="content">
                <i style="width: 100%;
                  padding: 5px;
                  color: red;" class="fa-solid fa-rectangle-xmark" onclick="deletarProduto(${obj.id})"></i>
                    <img src="assets/produto-sem-imagem.png">
                    <h3>${obj.nome}</h3>
                    <p>Número Lote: ${obj.numeroLote}</p>
                    <h6>${textAlocado}</h6>
                    <button class="buy-1" onclick="alocarProduto(${obj.id})">Alocar Produto</button>
                </div>
            </div>
                `
                }
                console.log(obj);
                
            }
            document.getElementById("produtos").innerHTML = meusProdutos
        }
    });
}

function alocarProduto(idProduto){
    var e = document.getElementById("idPaciente");
    let idPaciente = e.value;
    let url = `http://localhost:8080/api/pessoas/alocar-produtos/idProduto=${idProduto}&idPessoa=${parseInt(idPaciente)}`
    console.log("Id Paciente: ", idPaciente)

    if(idPaciente != ""){
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
            }, error: function(){
                console.log("erro")
            }
        });
    }
}

function deletarProduto(idProduto){
    console.log("Chaamou")
    $.ajax({
      type: "DELETE",
      url: `http://localhost:8080/api/produtos/${idProduto}`,
      crossDomain: true,
      headers: { 
          'Content-Type': 'application/json',
          'Accept': '*/*',
      },
      dataType:"json",
      success: function(result){
          console.log("Usuário: " + idProduto + " deletado");
          document.location.reload(true);
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Something went wrong!',
            footer: '<a href="">Why do I have this issue?</a>'
          })
      }
  });
  }