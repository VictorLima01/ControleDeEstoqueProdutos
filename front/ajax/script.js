$(document).ready(function(){
    $("button").click(function(){
        $.ajax({
            type: "GET",
            url: "https://swapi.dev/api/people/1",
            dataType:"json",
            success: function(result){
        
                $("#div1").html(result.birth_year);
            }
          });
    });
  });

  $.ajax({
    type: "POST",
    url: url,
    data: data,
    dataType: "json",
    success: function(data) {
      alert('Load was performed.');
    }
  });

  $.ajax({
    url: '/echo/html/',
    type: 'PUT',
    data: "name=John&location=Boston",
    dataType: "json",
    success: function(data) {
      alert('Load was performed.');
    }
  });

  $.ajax({
    url: '/script.cgi',
    type: 'DELETE',
    success: function(result) {
        // Do something with the result
    }
});
  