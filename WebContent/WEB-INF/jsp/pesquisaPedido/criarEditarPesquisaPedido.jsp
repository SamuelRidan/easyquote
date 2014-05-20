<%@ include file="/base.jsp" %> 

<form class="form-horizontal" action="<c:url value="/pesquisaPedido/salvarPesquisaPedido"/>" method="post">
  <fieldset>
    <legend>Criar/editar pesquisaPedido</legend>
    <div class="control-group">
      <label class="control-label">Pergunta1</label>
      <div class="controls">
        <input type="text" class="input-xlarge" name="pesquisaPedido.pergunta1" value="${pesquisaPedido.pergunta1}">
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">Pergunta2</label>
      <div class="controls">
        <input type="text" class="input-xlarge" name="pesquisaPedido.pergunta2" value="${pesquisaPedido.pergunta2}">
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">Pergunta3</label>
      <div class="controls">
        <input type="text" class="input-xlarge" name="pesquisaPedido.pergunta3" value="${pesquisaPedido.pergunta3}">
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">Pergunta4</label>
      <div class="controls">
        <input type="text" class="input-xlarge" name="pesquisaPedido.pergunta4" value="${pesquisaPedido.pergunta4}">
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">Pontuacao1</label>
      <div class="controls">
        <input type="text" class="input-xlarge numero-inteiro" name="pesquisaPedido.pontuacao1" value="${pesquisaPedido.pontuacao1}">
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">Pontuacao2</label>
      <div class="controls">
        <input type="text" class="input-xlarge numero-inteiro" name="pesquisaPedido.pontuacao2" value="${pesquisaPedido.pontuacao2}">
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">Pontuacao3</label>
      <div class="controls">
        <input type="text" class="input-xlarge numero-inteiro" name="pesquisaPedido.pontuacao3" value="${pesquisaPedido.pontuacao3}">
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">Pontuacao4</label>
      <div class="controls">
        <input type="text" class="input-xlarge numero-inteiro" name="pesquisaPedido.pontuacao4" value="${pesquisaPedido.pontuacao4}">
      </div>
    </div>

    <button type="submit" class="btn btn-primary">Salvar</button>
    <a class="btn btn-danger" href="<c:url value="/pesquisaPedido/listarPesquisaPedidos"/>" > Cancelar </a>
  </fieldset>
</form>
