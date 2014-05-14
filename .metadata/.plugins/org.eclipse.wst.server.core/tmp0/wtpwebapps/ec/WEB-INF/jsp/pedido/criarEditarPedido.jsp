<%@ include file="/EQbase.jsp" %> 

      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">// </span> Criar/editar pedido </small></h1>
          </div>
        </div><!-- /.row -->     

		<form class="form-horizontal" action="<c:url value="/pedido/salvarPedido"/>" method="post">
		  <fieldset>
		    <legend></legend>
		    <div class="control-group">
		      <label class="control-label">DataEntrega</label>
		      <div class="controls">
		        <input type="text" class="input-xlarge data" name="pedido.dataEntrega" value="<fmt:formatDate value="${pedido.dataEntrega.time}"/>">
		      </div>
		    </div>
		
		    <button type="submit" class="btn btn-primary">Salvar</button>
		    <a class="btn btn-danger" href="<c:url value="/pedido/listarPedidos"/>" > Cancelar </a>
		  </fieldset>
		</form>


<%@ include file="/EQbaseFim.jsp" %> 