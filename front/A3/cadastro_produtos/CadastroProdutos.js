function sendCadastroProdutos(){
    let numeroLote = document.getElementById("numeroLote").value;
    let nome = document.getElementById("nome").value;
    let funcao = document.getElementById("funcao").value;

    let url = "http://localhost:8080/api/produtos/cadastrar";


        let data = {
            nome: nome,
            numeroLote: parseInt(numeroLote),
            alocado: false,
            funcao: funcao
        }
    
        $.ajax({
            type: "POST",
            url: url,
            crossDomain: true,
            headers: { 
                'Content-Type': 'application/json',
                'Accept': '*/*',
            },
            data: JSON.stringify(data),
            dataType: 'json',
            success: function(data) {
              console.log(data);
              document.getElementById("numeroLote").value = "";
              document.getElementById("nome").value = "";
              document.getElementById("funcao").value = "";

              Swal.fire(
                `Sucesso!`,
                `Você criou um produto!`,
                'success'
              )
            },
            error: function () {
                console.log("error");
            }
          });
    
}