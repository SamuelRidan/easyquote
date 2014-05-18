<%@ include file="/base.jsp" %> 

<form class="form-horizontal" action="<c:url value="/listaCotacaoFornecedor/salvarListaCotacaoFornecedor"/>" method="post">
  <fieldset>
    <legend>Criar/editar listaCotacaoFornecedor</legend>
    <div class="control-group">
      <label class="control-label">Preco</label>
      <div class="controls">
        <input type="text" class="input-xlarge numero-decimal" name="listaCotacaoFornecedor.preco" value="<fmt:formatNumber value="${listaCotacaoFornecedor.preco}"/>">
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">PrecoTotal</label>
      <div class="controls">
        <input type="text" class="input-xlarge numero-decimal" name="listaCotacaoFornecedor.precoTotal" value="<fmt:formatNumber value="${listaCotacaoFornecedor.precoTotal}"/>">
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">IdListaCotacao</label>
      <div class="controls">
        <input type="text" class="input-xlarge numero-inteiro" name="listaCotacaoFornecedor.idListaCotacao" value="${listaCotacaoFornecedor.idListaCotacao}">
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">IdFornecedor</label>
      <div class="controls">
        <input type="text" class="input-xlarge numero-inteiro" name="listaCotacaoFornecedor.idFornecedor" value="${listaCotacaoFornecedor.idFornecedor}">
      </div>
    </div>

    <button type="submit" class="btn btn-primary">Salvar</button>
    <a class="btn btn-danger" href="<c:url value="/listaCotacaoFornecedor/listarListaCotacaoFornecedors"/>" > Cancelar </a>
  </fieldset>
</form>
