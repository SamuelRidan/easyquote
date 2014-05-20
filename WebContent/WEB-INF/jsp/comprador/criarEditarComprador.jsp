<%@ include file="/base.jsp" %> 

<form class="form-horizontal" action="<c:url value="/comprador/salvarComprador"/>" method="post">
  <fieldset>
    <legend>Criar/editar comprador</legend>
    <div class="control-group">
      <label class="control-label">Cpf</label>
      <div class="controls">
        <input type="text" class="input-xlarge numero-decimal" name="comprador.cpf" value="<fmt:formatNumber value="${comprador.cpf}"/>">
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">Telefone</label>
      <div class="controls">
        <input type="text" class="input-xlarge" name="comprador.telefone" value="${comprador.telefone}">
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">Email</label>
      <div class="controls">
        <input type="text" class="input-xlarge" name="comprador.email" value="${comprador.email}">
      </div>
    </div>

    <button type="submit" class="btn btn-primary">Salvar</button>
    <a class="btn btn-danger" href="<c:url value="/comprador/listarCompradors"/>" > Cancelar </a>
  </fieldset>
</form>
