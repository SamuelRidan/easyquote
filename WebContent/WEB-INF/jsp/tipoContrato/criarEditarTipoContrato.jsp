<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/EQbase.jsp" %> 

<div id="page-wrapper">
  <div class="row">
    <div class="col-lg-12">
      <h1><small><span style="color:#31708F">//</span> Criar tipo de Contrato </small></h1>
    </div>
  </div><!-- /.row -->

<form class="form-horizontal" action="<c:url value="/tipoContrato/salvarTipoContrato"/>" method="post">
  <fieldset>
    <div class="control-group">
      <label class="control-label">Descricao</label>
      <div class="controls">
        <input type="text" class="input-xlarge" name="tipoContrato.descricao" value="${tipoContrato.descricao}">
      </div>
    </div>

    <button type="submit" class="btn btn-primary">Salvar</button>
    <a class="btn btn-danger" href="<c:url value="/tipoContrato/listarTipoContratos"/>" > Cancelar </a>
  </fieldset>
</form>

</div>
<%@ include file="/EQbaseFim.jsp" %> 

