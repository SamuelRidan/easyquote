<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%> 
 <script type="text/javascript">
	
$(document).ready(function() {
	$("#buscarProd").click(function(){
		de = $("#de").val();
		para = $("#ate").val();
	 }); 
	
    $.ajax({
    	  url:"<c:url value='/produto/produtosRealizados'/>",
    	  dataType: "json",
    	  success: function(dt){

	      	$.each(dt, function(i, item) {
	      		$("#prodR").append(
	      		"<tr>"+
	      		"<td>" + dt[i].cod +"</td>"+
	      		"<td>" + dt[i].produto+"</td>"+
	      		"<td>" +dt[i].Quantidade+"</td>"+
	      		"</tr>"
	      		);
	      		//alert(dt[i].Quantidade);		
	      	});
 
    	  }	  
    });
});	
</script>


   	   <div class="col-lg-12">
            <h1>Produtos <small>Solicitados</small></h1>
            <ol class="breadcrumb">
              <li><a href="<c:url value="/produto/listarProdutos"/>" ><i class="icon-dashboard"></i> Lista de Produtos</a></li>
              <li class="active"><i class="icon-file-alt"></i> Produtos Solicitados</li>
            </ol>
          
        </div><!-- /.row -->  

<div class="col-lg-12">
       <div class="well form-inline">
		                 <span>De:</span> <input id="de" class="input-small data"  type="text">
		                 <span>Até:</span> <input id="ate" class="input-small data"  type="text">  <br/> <br/>          
				     <div class="control-group">
				      <label class="control-label">Setor Requerente</label>
				      <div class="controls">
				        <select id="setor" name="setor">
				        	<option selected="selected" >Todos </option>
							<c:forEach items="${tipoSetor}" var="itemSetor">
								<option value="${itemSetor.id}">${itemSetor.id} - ${itemSetor.descricao} </option>
							</c:forEach>
						</select>
				    </div>
              </div> <br/> 
			   <button type="submit" id="buscarProd" class="btn btn-default">Buscar</button>               		            
          </div>
          
          <div class="well form-inline">
            <table  id="prodR" class="table table-striped table-bordered tablesorter">
	      		<tr>
	      		<th>Cod.</th>
	      		<th>Produto</th>
	      		<th>Quantidade</th>
	      		</tr>  
        
            </table>
          
          </div>
<c:choose>
	<c:when test="${!empty produtos}">
		
		<c:set var="link" value="produto/listarProdutos" scope="request" />
		<%@ include file="/paginacao.jsp" %> 
		
		<table class="table table-striped table-bordered tablesorter">
			<thead>
		    	<tr>
                    <th> Descricao </th>
                    <th> Preco </th>
                    <th> CodSAP </th>
		            <th> Editar </th>
		            <th> Remover </th>	                    
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${produtos}" var="item">
					<tr id="produto_${item.id}">
                        <td> ${item.descricao} </td>
                        <td> ${item.preco} </td>
                        <td> ${item.codSAP} </td>
			            <td> <a href="<c:url value="/produto/editarProduto/${item.id}"/>" ><i class="fa fa-edit"></i> Editar </a> </td>
			            <td> <a href="<c:url value="/produto/excluirProduto/${item.id}"/>" ><i class="fa fa-trash-o"></i> Remover</a></td>		                                                
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<br>  <br>  <h4> Nenhum registro foi encontrado </h4>
	</c:otherwise>
</c:choose>
   </div>
   

<%@ include file="/EQbaseFim.jsp" %> 