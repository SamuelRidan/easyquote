<%@ include file="/EQbase.jsp" %> 
<%@ taglib uri="/tags/tags" prefix="tags"%>

 <div id="page-wrapper">
   <div class="row">
     <div class="col-lg-12">
       <h1><small><span style="color:#31708F">//</span> Criar/Editar Segmento </small></h1>
     </div>
   </div><!-- /.row -->   
        

<form class="form-horizontal" action="<c:url value="/seguimento/salvarSeguimento"/>" method="post">
  <fieldset>
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
</div>

<%@ include file="/EQbaseFim.jsp" %> 