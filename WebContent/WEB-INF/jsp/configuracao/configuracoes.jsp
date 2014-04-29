<%@ include file="/EQbase.jsp" %> 

      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">//</span>Configurações gerais </small></h1>
          </div>
        </div><!-- /.row -->     
         

		<form class="form-horizontal" action="<c:url value="/configuracao/salvarConfiguracoes"/>" method="post">
		  <fieldset>
		    <legend></legend>
		    
		    <div class="control-group warning">
		      <label class="control-label configuracao">Quantidade de registros por página</label>
		      <div class="controls">
		        <input type="text" class="input-medium required numero-inteiro" name="configuracoes(quantidadeRegistrosPorPagina)" value="${configuracoes.get('quantidadeRegistrosPorPagina')}">
		      </div>
		    </div>
		
			<br><br>
		    <button type="submit" class="btn btn-primary">Salvar</button>
		  </fieldset>
		</form>


<%@ include file="/EQbaseFim.jsp" %> 		