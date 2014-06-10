<%@ include file="/EQbase.jsp" %> 

<div id="page-wrapper">
  <div class="row">
    <div class="col-lg-12">
      <h1><small><span style="color:#31708F">//</span> Status de pedidos </small></h1>
    </div>
  </div><!-- /.row -->

<form class="form-horizontal" action="<c:url value="/statusPedido/salvarStatusPedido"/>" method="post">
  <fieldset>
    <div class="control-group">
      <label class="control-label">Descrição</label>
      <div class="controls">
        <input type="text" class="input-xlarge" name="statusPedido.descricao" value="${statusPedido.descricao}">
      </div>
    </div>

    <button type="submit" class="btn btn-primary">Salvar</button>
    <a class="btn btn-danger" href="<c:url value="/statusPedido/listarStatusPedidos"/>" > Cancelar </a>
  </fieldset>
</form>

</div>

<%@ include file="/EQbaseFim.jsp" %> 