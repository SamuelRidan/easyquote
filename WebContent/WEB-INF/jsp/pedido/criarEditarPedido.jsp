<%@ include file="/EQbase.jsp" %> 

<script type="text/javascript">

function gravar(){	
	
	select = document.getElementById("pedido.status.id");
	option = select.options[select.options.selectedIndex];
	valor = option.value;
	
	if (valor == 2){
		
		var idpedido = $("input[name='idped']").val();
		var st = valor;
		var dtEntrega = $("input[name='pedido.dataEntrega']").val();
		var cot = $("input[name='idCotacao']").val();
		var forne = $("input[name='idForncedor']").val(); 
		console.log(idpedido +" | "+dtEntrega+" | "+cot+" | "+forne+" | "+st);
		$.ajax({
	    	  url:"<c:url value='/pedido/salvarPedido1'/>",
	      	  data: {
		      		id: idpedido,
		      		cotacao: cot,
		      		status: st,
		      		dt: dtEntrega,
		      		fornecedor: forne
		      	  },  
	    	  success: function(dt){
		        if(dt =="sucesso"){
		        		
		        	window.location.href = "<c:url value='/pesquisaPedido/Pesquisa?id='/>"+idpedido;
		        }
	    		  
	    	  }
	    	  });	
	} else {
		
	    $("#formPedido").submit();
		
	}	
}

$( document ).ready(function() {
	select = document.getElementById("pedido.status.id");
	option = select.options[select.options.selectedIndex];
	valor = option.value;
	if (valor == 2){
		document.getElementById("pedido.dataEntrega").style.display="block";
	} else {
		document.getElementById("pedido.dataEntrega").style.display="none";
	}
	

});

function setVisible(valor){
	if (valor != 1){
		document.getElementById("pedido.dataEntrega").style.display="block";
	} else {
		document.getElementById("pedido.dataEntrega").style.display="none";
	}
}

</script>

      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">// </span> Status do pedido </small></h1>
          </div>
        </div><!-- /.row -->     
        <input type="hidden" name=""/>
		<form class="form-horizontal" id="formPedido" action="<c:url value="/pedido/salvarPedido"/>" method="post">
		  <fieldset>
		   <input type="hidden" name="idped" value="${pedido.id}">
		   <input type="hidden" name="idCotacao" value="${pedido.cotacao.id}">
		    <input type="hidden" name="idForncedor" value="${pedido.fornecedor.id}">
		   <div class="control-group warning">
		        <label class="control-label">Status</label>
		        <div class="controls">
		          <select name="pedido.status.id" id="pedido.status.id" onchange="setVisible(this.value)">
					<c:forEach items="${status}" var="itemStatus">
						<option <c:if test="${pedido.status.id == itemStatus.id}"> selected="selected" </c:if> value="${itemStatus.id}"> ${itemStatus.id} - ${itemStatus.descricao} </option>
					</c:forEach>
				  </select>
		        </div>
		    </div>
		  
		    <div class="control-group" id="pedido.dataEntrega" style="display: none;">
		      <label class="control-label">Data de entrega</label>
		      <div class="controls">
		        <input type="text" class="input-xlarge data" name="pedido.dataEntrega" value="<fmt:formatDate value="${pedido.dataEntrega.time}"/>">
		      </div>
		    </div>
		    <button type="button" onclick="gravar();" class="btn btn-primary">Salvar</button>
		    <a class="btn btn-danger" href="<c:url value="/pedido/listarPedidos"/>" > Cancelar </a>
		  </fieldset>
		</form>
</div>

<%@ include file="/EQbaseFim.jsp" %> 