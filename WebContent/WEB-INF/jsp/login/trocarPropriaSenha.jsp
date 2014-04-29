<%@ include file="/EQbase.jsp" %> 

      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">// </span> Troca Senha </small></h1>
          </div>
        </div><!-- /.row -->     
        
		<form class="form-horizontal" action="<c:url value="/login/salvarTrocarPropriaSenha"/>" method="post">
		  <fieldset>
		    <legend></legend>
		    
		    <div class="control-group warning">
		    	<label class="control-label">Senha antiga</label>
		    	<div class="controls">
		    		<input type="password" class="input-xlarge required" name="senhaAntiga"  >
				</div>
		    </div>
		    
		    <div class="control-group warning">
		    	<label class="control-label">Nova senha</label>
		    	<div class="controls">
		    		<input type="password" class="input-xlarge required" name="senhaNova"  >
				</div>
		    </div>
		            
            <button type="submit" class="btn btn-default">Salvar</button>
		    <a class="btn btn-default" href="<c:url value="/home/home"/>" > Cancelar </a>
		  </fieldset>
		</form>


<%@ include file="/EQbaseFim.jsp" %> 