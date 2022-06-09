function sendLogin() {
    let email = document.getElementById("username").value
    let senha = document.getElementById("password").value

    let url = `http://localhost:8080/api/pessoas/login/email=` + email + `&password=` + senha;

    if(email == null || email == ""){
        document.getElementById("username").style.borderColor = "red";
    }else if(senha == null || senha == ""){
        document.getElementById("password").style.borderColor = "red";
    }else{
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
                let id = result[0].id
                let nome = result[0].nome
                location.href = '../home/index.html?id=' + id + '&nome=' + nome;
            }
          });
    }
}