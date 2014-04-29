<%@ include file="/EQbase.jsp" %> 

      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">// </span> Criar/editar setor </small></h1>
          </div>
        </div><!-- /.row -->     
        
		<form class="form-horizontal" action="<c:url value="/setor/salvarSetor"/>" method="post">
		  <fieldset>
		    <legend></legend>
		    <div class="control-group">
		      <label class="control-label">Descricao</label>
		      <div class="controls">
		        <input type="text" class="input-xlarge" name="setor.descricao" value="${setor.descricao}">
		      </div>
		    </div>
		
		    <button type="submit" class="btn btn-primary">Salvar</button>
		    <a class="btn btn-danger" href="<c:url value="/setor/listarSetors"/>" > Cancelar </a>
		  </fieldset>
		</form>
<%@ include file="/EQbaseFim.jsp" %> 