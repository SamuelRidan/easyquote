<%@ include file="/base.jsp" %> 

<form class="form-horizontal" action="<c:url value="/fornecedor/salvarFornecedor"/>" method="post">
  <fieldset>
    <legend>Criar/editar fornecedor</legend>
    <div class="control-group">
      <label class="control-label">Id</label>
      <div class="controls">
        <input type="text" class="input-xlarge numero-inteiro" name="fornecedor.id" value="${fornecedor.id}">
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">Cnpj</label>
      <div class="controls">
        <input type="text" class="input-xlarge" name="fornecedor.cnpj" value="${fornecedor.cnpj}">
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">Razao_social</label>
      <div class="controls">
        <input type="text" class="input-xlarge" name="fornecedor.razao_social" value="${fornecedor.razao_social}">
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">Telefone</label>
      <div class="controls">
        <input type="text" class="input-xlarge" name="fornecedor.telefone" value="${fornecedor.telefone}">
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">Segmento</label>
      <div class="controls">
        <input type="text" class="input-xlarge" name="fornecedor.segmento" value="${fornecedor.segmento}">
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">Reputacao</label>
      <div class="controls">
        <input type="text" class="input-xlarge" name="fornecedor.reputacao" value="${fornecedor.reputacao}">
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">Pessoa</label>
      <div class="controls">
        <input type="text" class="input-xlarge numero-inteiro" name="fornecedor.pessoa" value="${fornecedor.pessoa}">
      </div>
    </div>

    <button type="submit" class="btn btn-primary">Salvar</button>
    <a class="btn btn-danger" href="<c:url value="/fornecedor/listarFornecedors"/>" > Cancelar </a>
  </fieldset>
</form>
