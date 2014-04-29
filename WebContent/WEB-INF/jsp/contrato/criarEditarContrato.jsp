<%@ include file="/EQbase.jsp" %> 

      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">//</span> Troca Senha </small></h1>
          </div>
        </div><!-- /.row -->   

		<form class="form-horizontal" action="<c:url value="/contrato/salvarContrato"/>" method="post">
		  <fieldset>
		    <legend>Criar/editar contrato</legend>
		    <div class="control-group">
		      <label class="control-label">InicioVigencia</label>
		      <div class="controls">
		        <input type="text" class="input-xlarge data" name="contrato.inicioVigencia" value="<fmt:formatDate value="${contrato.inicioVigencia.time}"/>">
		      </div>
		    </div>
		    <div class="control-group">
		      <label class="control-label">FimVigencia</label>
		      <div class="controls">
		        <input type="text" class="input-xlarge data" name="contrato.fimVigencia" value="<fmt:formatDate value="${contrato.fimVigencia.time}"/>">
		      </div>
		    </div>
		    <div class="control-group">
		      <label class="control-label">PeriodicidadeReajuste</label>
		      <div class="controls">
		        <input type="text" class="input-xlarge numero-inteiro" name="contrato.periodicidadeReajuste" value="${contrato.periodicidadeReajuste}">
		      </div>
		    </div>
		
            <button type="submit" class="btn btn-default">Salvar</button>
		    <a class="btn btn-default"  href="<c:url value="/contrato/listarContratos"/>" > Cancelar </a>
		  </fieldset>
		</form>

<%@ include file="/EQbaseFim.jsp" %> 		
