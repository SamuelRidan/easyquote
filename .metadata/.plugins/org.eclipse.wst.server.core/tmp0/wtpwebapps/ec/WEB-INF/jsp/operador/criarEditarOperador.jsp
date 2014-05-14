<%@ include file="/EQbase.jsp" %> 

      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">// </span> Criar/editar operador</small></h1>
          </div>
        </div><!-- /.row --> 
		
		<form class="form-horizontal" action="<c:url value="/operador/salvarOperador"/>" method="post">
		  <fieldset>
		    <legend></legend>
		    <div class="control-group">
		      <label class="control-label">Login</label>
		      <div class="controls">
		        <input type="text" class="input-xlarge" name="operador.login" value="${operador.login}">
		      </div>
		    </div>
		    <div class="control-group">
		      <label class="control-label">Senha</label>
		      <div class="controls">
		        <input type="text" class="input-xlarge" name="operador.senha" value="${operador.senha}">
		      </div>
		    </div>
		    <div class="control-group">
		      <label class="control-label">Ativo</label>
		      <div class="controls">
		        <input type="checkbox" <c:if test="${operador.ativo}"> checked="checked" </c:if> class="input-xlarge" name="operador.ativo">
		      </div>
		    </div>
		
		    <button type="submit" class="btn btn-primary">Salvar</button>
		    <a class="btn btn-danger" href="<c:url value="/operador/listarOperadors"/>" > Cancelar </a>
		  </fieldset>
		</form>


<%@ include file="/EQbaseFim.jsp" %> 