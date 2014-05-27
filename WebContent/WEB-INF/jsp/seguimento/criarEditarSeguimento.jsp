<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>

<form class="form-horizontal" action="<c:url value="/seguimento/salvarSeguimento"/>" method="post">
  <fieldset>
    <legend>Criar/editar seguimento</legend>
    <div class="control-group">
      <label class="control-label">Id</label>
      <div class="controls">
        <input type="text" class="input-xlarge numero-inteiro" name="seguimento.id" value="${seguimento.id}">
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">Descricao</label>
      <div class="controls">
        <input type="text" class="input-xlarge" name="seguimento.descricao" value="${seguimento.descricao}">
      </div>
    </div>

    <button type="submit" class="btn btn-primary">Salvar</button>
    <a class="btn btn-danger" href="<c:url value="/seguimento/listarSeguimentos"/>" > Cancelar </a>
  </fieldset>
</form>

<%@ include file="/EQbaseFim.jsp" %> 