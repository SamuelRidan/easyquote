<%@ include file="/EQbase.jsp" %> 
      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">//</span> Criar/editar aditivo </small></h1>
          </div>
        </div><!-- /.row -->   
              
		<form class="form-horizontal" action="<c:url value="/aditivo/salvarAditivo"/>" method="post">
		  <fieldset>
		    <legend></legend>
		    <div class="control-group">
		      <label class="control-label">PrecoTotal</label>
		      <div class="controls">
		        <input type="text" class="input-xlarge numero-inteiro" name="aditivo.precoTotal" value="${aditivo.precoTotal}">
		      </div>
		    </div>
		
		    <button type="submit" class="btn btn-primary">Salvar</button>
		    <a class="btn btn-danger" href="<c:url value="/aditivo/listarAditivos"/>" > Cancelar </a>
		  </fieldset>
		</form>

</div>

<%@ include file="/EQbaseFim.jsp" %> 