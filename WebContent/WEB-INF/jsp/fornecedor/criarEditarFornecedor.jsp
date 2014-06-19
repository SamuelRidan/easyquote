<%@page import="easyquote.controller.ListaCotacaoController"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/EQbase.jsp" %> 
<%@ page import="java.util.*,easyquote.modelo.*,easyquote.hibernate.*, teste.*" %>

<script type="text/javascript">

function validaCNPJ() {
	CNPJ = document.getElementById("fornecedor.cnpj").value;
	erro = new String;
	if ((CNPJ.length < 18) && (CNPJ.length != 14)) {
		erro += "É necessario preencher corretamente o número do CNPJ! "; 
	}
	if (CNPJ.length == 14){
		CNPJ = CNPJ.substr(0,2)+"."+CNPJ.substr(2,3)+"."+CNPJ.substr(5,3)+"/"+CNPJ.substr(8,4)+"-"+CNPJ.substr(12,2);
	}
	if ((CNPJ.charAt(2) != ".") || (CNPJ.charAt(6) != ".") || (CNPJ.charAt(10) != "/") || (CNPJ.charAt(15) != "-")){
		if (erro.length == 0) {
			erro += "É necessário preencher corretamente o número do CNPJ! ";
		}
    }
	
    //substituir os caracteres que não são números
    if(document.layers && parseInt(navigator.appVersion) == 4){
	    x = CNPJ.substring(0,2);
	    x += CNPJ. substring (3,6);
	    x += CNPJ. substring (7,10);
	    x += CNPJ. substring (11,15);
	    x += CNPJ. substring (16,18);
	    CNPJ = x;
    } else {
	      CNPJ = CNPJ. replace (".","");
	      CNPJ = CNPJ. replace (".","");
	      CNPJ = CNPJ. replace ("-","");
	      CNPJ = CNPJ. replace ("/","");
      	}
    
	var nonNumbers = /\D/;
	if (nonNumbers.test(CNPJ)) erro += "A verificação de CNPJ suporta apenas números! "; 
	var a = [];
	var b = new Number;
	var c = [6,5,4,3,2,9,8,7,6,5,4,3,2];
	
	for (i=0; i<12; i++){
	    a[i] = CNPJ.charAt(i);
	    b += a[i] * c[i+1];
	}
    
	if ((x = b % 11) < 2) { 
    	a[12] = 0; 
    } else { 
    	a[12] = 11-x;
      }
	
    b = 0;
    
    for (y=0; y<13; y++) {
    	b += (a[y] * c[y]); 
    }
    
    if ((x = b % 11) < 2) { 
  		a[13] = 0; 
  	} else { 
  	  	a[13] = 11-x; 
  	  }
    
    if ((CNPJ.charAt(12) != a[12]) || (CNPJ.charAt(13) != a[13])){
       erro +="Dígito verificador com problema!";
    }
    
    if (erro.length > 0){
		alert(erro);
		document.getElementById("fornecedor.cnpj").value = "";
		document.getElementById("fornecedor.cnpj").focus();
	} else {
		CNPJ = CNPJ.substr(0,2)+"."+CNPJ.substr(2,3)+"."+CNPJ.substr(5,3)+"/"+CNPJ.substr(8,4)+"-"+CNPJ.substr(12,2);
		document.getElementById("fornecedor.cnpj").value = CNPJ;
	}
    
}

</script>

<div id="page-wrapper">
  <div class="row">
    <div class="col-lg-12">
      <h1><small><span style="color:#31708F">//</span> Criar fornecedor </small></h1>
    </div>
  </div><!-- /.row -->

<form class="form-horizontal" action="<c:url value="/fornecedor/salvarFornecedor"/>" method="post">
  <fieldset>
    <div class="control-group" style="display: none;">
      <label class="control-label">Operador</label>
      <div class="controls">     	
		  <input type="text" class="input-xlarge numero-decimal" name="fornecedor.operador.id" value="${fornecedor.operador.id}">   	
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">CNPJ</label>
      <div class="controls">
        <input type="text" class="input-xlarge" name="fornecedor.cnpj" id="fornecedor.cnpj" value="${fornecedor.cnpj}" onblur="validaCNPJ()">
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">Razao Social</label>
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
      <label class="control-label">Email</label>
      <div class="controls">
        <input type="email" class="input-xlarge" name="fornecedor.email" value="${fornecedor.email}">
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">Segmento</label>
      <div class="controls">
      		<select name="fornecedor.seguimento.id">
      			<option disabled="disabled" selected="selected">Selecione o segmento</option>
				<c:forEach items="${tipoSeg}" var="itemSeg">
					<option <c:if test="${fornecedor.seguimento.id == itemSeg.id}"> selected="selected" </c:if> value="${itemSeg.id}"> ${itemSeg.id} - ${itemSeg.descricao} </option>
				</c:forEach>  
			</select>    
	  </div>
    </div>
    <div class="control-group">
      <label class="control-label">Reputacao</label>
      <div class="controls">
        <input type="text" class="input-xlarge" name="fornecedor.reputacao" value="${fornecedor.reputacao}">
      </div>
    </div>

    <button type="submit" class="btn btn-primary">Salvar</button>
  </fieldset>
</form>
</div>
<%@ include file="/EQbaseFim.jsp" %> 
