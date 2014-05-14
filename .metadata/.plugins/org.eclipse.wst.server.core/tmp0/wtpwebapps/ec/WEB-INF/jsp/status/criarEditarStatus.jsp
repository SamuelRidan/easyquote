<%@ include file="/EQbase.jsp" %> 

      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">// </span> Criar/editar status</small></h1>
          </div>
        </div><!-- /.row -->    

		<form class="form-horizontal" action="<c:url value="/status/salvarStatus"/>" method="post">
		  <fieldset>
		    <legend></legend>
		    <div class="control-group">
		      <label class="control-label">Descricao</label>
		      <div class="controls">
		        <input type="text" class="input-xlarge" name="status.descricao" value="${status.descricao}">
		      </div>
		    </div>
		
		    <button type="submit" class="btn btn-primary">Salvar</button>
		    <a class="btn btn-danger" href="<c:url value="/status/listarStatuss"/>" > Cancelar </a>
		  </fieldset>
		</form>

<%@ include file="/EQbaseFim.jsp" %> 