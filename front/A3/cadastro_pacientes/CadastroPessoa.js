function sendCadastro() {
    let name = document.getElementById("name").value;
    let Email = document.getElementById("email").value;
    let phone = document.getElementById("telefone").value;
    let Senha = document.getElementById("senha").value;
    let confirmSenha = document.getElementById("senhaConfirm").value;

    let url = "http://localhost:8080/api/pessoas/cadastrar";

    if(Senha != confirmSenha){
        document.getElementById("senhaConfirm").style.borderColor = "red";
    }else{
        let data = {
            nome: name,
            email: Email,
            telefone: phone,
            password: Senha
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
              document.getElementById("name").value = "";
              document.getElementById("email").value = "";
              document.getElementById("telefone").value = "";
              document.getElementById("senha").value = "";
              document.getElementById("senhaConfirm").value = "";

              document.location.reload(true);
            },
            error: function () {
                console.log("error");
            }
          });
    }

    
  }