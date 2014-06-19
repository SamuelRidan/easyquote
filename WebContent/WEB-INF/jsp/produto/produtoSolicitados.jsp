<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>

<!-- <script src="http://code.jquery.com/jquery-1.10.2.js"></script>  -->


   	   <div class="col-lg-12">
            <h1>Produtos <small>Solicitados</small></h1>
            <ol class="breadcrumb">
              <li><a href="<c:url value="/produto/listarProdutos"/>" ><i class="icon-dashboard"></i> Lista de Produtos</a></li>
              <li class="active"><i class="icon-file-alt"></i> Produtos Solicitados</li>
            </ol>
          
        </div><!-- /.row -->  

<div class="col-lg-12">
           
           <div class="col-lg-5">
            <div class="panel panel-primary">
              <div class="panel-heading">
                <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i>Produtos Solicitados por  Setor</h3>
              </div>
              <div class="panel-body">
                <div id="morris-chart-donut"></div>
                <div class="text-right">
                </div>
              </div>
            </div>
          </div>
          
 
           <div class="col-lg-7">          
            <div class="panel panel-primary">
              <div class="panel-heading">
                <h3 class="panel-title"><i class="fa fa-bar-chart-o"></i> Produtos Solicitados</h3>
              </div>
              <div class="panel-body">
                <div id="morris-chart-area"></div>
              </div>
            </div>
            </div>
                 
                 
          <div class="well form-inline">
            <table  id="prodR" class="table table-striped table-bordered tablesorter">
	      		<tr>
	      		<th>Cod.</th>
	      		<th>Produto</th>
	      		<th>Quantidade</th>
	      	    <th>Setor</th>
	      	    <th>Data Solicitada</th>
	      		</tr>  
        
            </table>
                    
         </div><!-- /.row --> 
          </div>
 

    <!-- Page Specific Plugins -->
    <script src="<c:url value="/EQ-js/raphael-min.js"/>"></script>  
    <script src="<c:url value="/EQ-js/morris-0.4.3.min.js"/>"></script>

 <script type="text/javascript">

 function charArea(c){
	   console.log(c);

}

function cicle(d){
 
 Morris.Donut({
	  element: 'morris-chart-donut',
	  data: d,
	  formatter: function (y) { return y + "%" ;}
	});	   
}


$(document).ready(function() {
	
    $("#morris-chart-donut").click(function(){
    	setor = $("div#morris-chart-donut > svg > text > tspan:first-child").html();
    	setor= setor.replace(" ","");
    	
    	$(".setor").hide();
    	$("."+setor).toggle();
    	
    });
	
    $.ajax({
    	  url:"<c:url value='/produto/produtosSegmento'/>",
    	  dataType: "text",
        beforeSend: function(jqXHR) {
            jqXHR.overrideMimeType("text/html;charset=iso-8859-1");
          },
    	  success: function(dt){
				console.log(dt);
				var obj = jQuery.parseJSON(dt);
			    cicle(obj);
    	  }
	
	    });	
    

   
    $.ajax({
    	  url:"<c:url value='/produto/produtosRealizados'/>",
    	  dataType: "json",
          beforeSend: function(jqXHR) {
              jqXHR.overrideMimeType("text/html;charset=iso-8859-1");
            },   	  
    	  success: function(dt){ 
	      	$.each(dt, function(i, item) {
	      		setor  = dt[i].Setor;
	      		setor = setor.replace(" ","");
	      		 
	      		$("#prodR").append(
	      		"<tr class='"+setor+" setor' >"+
	      		"<td>" + dt[i].cod +"</td>"+
	      		"<td>" + dt[i].produto+"</td>"+
	      		"<td>" +dt[i].Quantidade+"</td>"+
	      		"<td>" +dt[i].Setor+"</td>"+
	      		"<td>" +dt[i].Data+"</td>"+
	      		"</tr>"
	      		);
 
	      	}); 
	  }	  
            
    }); 

	        $.ajax({
	        	  url:"<c:url value='/produto/produtosSolicitadosData'/>",
	        	  dataType: "json",
	        	  success: function(dt){
	        		  
	      		    Morris.Area({
	  			  	  // ID of the element in which to draw the chart.
	  			  	  element: 'morris-chart-area',
	  			  	  // Chart data records -- each entry in this array corresponds to a point on
	  			  	  // the chart.
	  			  	  data: dt,
	  			  	  // The name of the data record attribute that contains x-visitss.
	  			  	  xkey: 'd',
	  			  	  // A list of names of data record attributes that contain y-visitss.
	  			  	  ykeys: ['cont'],
	  			  	  // Labels for the ykeys -- will be displayed when you hover over the
	  			  	  // chart.
	  			  	  labels: ['Quantidade'],
	  			  	  // Disables line smoothing
	  			  	  smooth: false,
	  			  	});		        		     		  

	      				
	        	  }
	      	 });	             
	       



 });	

// 	function atualiza(){
// 		var de = document.getElementById("de").value;
// 		var ate = document.getElementById("ate").value;
		
// 		console.log(de);
// 		console.log(ate);
		
// 		 $.ajax({
// 	      	  url: "<c:url value='/produto/produtosRealizados1'/>",
// 	      	  data: {
// 	      		  dataInicio: de,
// 	      		  dataFim: ate
// 	      	  }, success: function(dt){
// 	     	}
// 		 });
// 	}
</script>

 
  		
  </body>
</html>
