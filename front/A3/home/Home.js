function buildPacientes(){
  /*
   Carrosel
  */
   let slideIndex = 1;
   showSlides(slideIndex);
   
   function plusSlides(n) {
     showSlides(slideIndex += n);
   }
   
   function currentSlide(n) {
     showSlides(slideIndex = n);
   }
   
   function showSlides(n) {
     let i;
     let slides = document.getElementsByClassName("mySlides");
     let dots = document.getElementsByClassName("dot");
     if (n > slides.length) {slideIndex = 1}    
     if (n < 1) {slideIndex = slides.length}
     for (i = 0; i < slides.length; i++) {
       slides[i].style.display = "none";  
     }
     for (i = 0; i < dots.length; i++) {
       dots[i].className = dots[i].className.replace(" active", "");
     }
     slides[slideIndex-1].style.display = "block";  
     dots[slideIndex-1].className += " active";
   }
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

              meusPacientes += `
              <div class="gallery">
              <div class="content">
                  <img src="assets/paciente.png">
                  <h3>${obj.nome}</h3>
                  <p>${obj.email}</p>
                  <h6>${obj.listasProdutos}</h6>
                  <ul>
                      <li><i class=""></i></li>
                      <li><i class=""></i></li>
                      <li><i class=""></i></li>
                      <li><i class=""></i></li>
                      <li><i class=""></i></li>
                  </ul>
                  <button class="buy-1">Alocar Produto</button>
              </div>
          </div>
              `
              
              console.log(obj);
              
          }
          document.getElementById("pacientes").innerHTML = meusPacientes
      }
  });
}