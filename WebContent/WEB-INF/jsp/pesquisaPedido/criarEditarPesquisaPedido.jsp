<%@ include file="/EQbase.jsp" %> 

 <script type="text/javascript">
 
function per1(sel) {
    a = sel.value.split("|");
     $("input[name='pesquisaPedido.pontuacao1']").val(a[0]); 
     $("input[name='pesquisaPedido.pergunta1']").val(a[1]); 
     
  }

function per2(sel) {
	    a = sel.value.split("|");
	     $("input[name='pesquisaPedido.pontuacao2']").val(a[0]); 
	     $("input[name='pesquisaPedido.pergunta2']").val(a[1]); 
	     
	  }	
 
function per3(sel) {
	    a = sel.value.split("|");
	     $("input[name='pesquisaPedido.pontuacao3']").val(a[0]); 
	     $("input[name='pesquisaPedido.pergunta3']").val(a[1]); 
	     
	  }	
 
$(document).ready(function() {

	
});




</script>
      <div id="page-wrapper">
	        <div class="row">
          <div class="col-lg-12">
            <h1><small><span style="color:#31708F">//</span> Pesquisa de Satisfação </small></h1>
          </div>
        </div><!-- /.row -->   

              <div class="panel-body">
<div class="row">
          <div class="col-lg-5">
 
      
<form class="form-horizontal" action="<c:url value="/pesquisaPedido/salvarPesquisaPedido"/>" method="post">
  <fieldset>
 
    <div class="control-group">
              <div class="form-group">
              <label>Entregou no Prazo ? </label>
              <b>Resposta:</b> ${pesquisaPedido.pergunta1} <br><br>
                <select class="form-control" onchange="per1(this);"  value="${pesquisaPedido.pergunta1}">
                   <option></option>
                  <option value="10|Entregou no Prazo">Entregou no Prazo</option>
                  <option value="7|Atrasado, com justificativa." >Atrasado, com justificativa.</option>
                  <option value="5|Atrasado, sem justificativa.">Atrasado, sem justificativa.</option>
                </select>
              </div>        
    </div>
    <div class="control-group">
              <div class="form-group">
               <label>Qualidade dos Produtos/Serviços no Setor</label>
               <b>Resposta:</b> ${pesquisaPedido.pergunta2} <br><br>
                <select class="form-control" value="pesquisaPedido.pergunta2" value="${pesquisaPedido.pergunta2}" onchange="per2(this);" >
                   <option></option>
                  <option value="10|Excelente">Excelente</option>
                  <option value="8|Satisfatorio">Satisfatorio</option>
                  <option value="6|Regular">Regular</option>
                  <option value="4|Insatisfatorio">Insatisfatorio</option>
                </select>
              </div>            
 
    </div>
    <div class="control-group">
              <div class="form-group">
                <label>Comunicação do Fornecedor </label>
               <b>Resposta:</b> ${pesquisaPedido.pergunta3} <br><br>
                <select class="form-control"  name="pesquisaPedido.pergunta3"  onchange="per3(this);"  >
                   <option></option>
                  <option value="10|Excelente">Excelente</option>
                  <option value="8|Satisfatorio">Satisfatorio</option>
                  <option value="6|Regular">Regular</option>
                  <option value="4|Insatisfatorio">Insatisfatorio</option>
                </select>
              </div>              
    </div>
    
    </div>
    </div><!-- row-->
        
        <input type="hidden" name="pesquisaPedido.pergunta1" >
        <input type="hidden" name="pesquisaPedido.pergunta2" >
        <input type="hidden" name="pesquisaPedido.pergunta3" >
        <input type="hidden" name="pesquisaPedido.pontuacao1" value="${pesquisaPedido.pontuacao1}">
        <input type="hidden" name="pesquisaPedido.pontuacao2" value="${pesquisaPedido.pontuacao2}">
        <input type="hidden" name="pesquisaPedido.pontuacao3" value="${pesquisaPedido.pontuacao3}">
        <input type="hidden" name="pesquisaPedido.pontuacao4" value="${pesquisaPedido.pontuacao4}">
        <input type="hidden" name="pesquisaPedido.pedido.id" value="${pesquisaPedido.pedido.id}">


    <button type="submit" class="btn btn-primary">Salvar</button>
    <a  class="btn btn-default" href="<c:url value="/pesquisaPedido/listarPesquisaPedidos"/>" > Cancelar </a>
  </fieldset>
</form>
       </div>
     </div>

<%@ include file="/EQbaseFim.jsp" %> 		

