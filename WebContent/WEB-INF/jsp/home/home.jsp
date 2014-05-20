<%@ include file="/EQbase.jsp" %> 
<%@ page import="java.util.*, scada.modelo.*, scada.hibernate.*, teste.*" %>
      <div id="page-wrapper">
        <div class="row">
          <div class="col-lg-12">
            <h1>Bem-Vindo <small>Sistema de Cotações de Contratos</small></h1>
            <ol class="breadcrumb">
                          <li class="active"><i class="fa fa-dashboard"></i>  Seguro  // Simples // Rápido</li>
            </ol>
            <div class="alert alert-success alert-dismissable">
              <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
               Com <b>Easy Quote</b> você conseguirá obter lucro no final de todas as suas cotações, além de trazer mais praticidade, rapidez e segurança nas negociações burocráticas de contratação.
            </div>
          </div>
        </div><!-- /.row -->       
 
        <div class="row">
          <div class="col-lg-3">
            <div class="panel panel-info">
              <div class="panel-heading">
                <div class="row">
                  <div class="col-xs-6">
                    <i class="fa fa-comments fa-5x"></i>
                  </div>
                  <div class="col-xs-6 text-right">
                    <% List contratoAditivo = HibernateUtilTest.executarConsultaHQL("from Aditivo");%>
                    <p class="announcement-heading"><% out.println(contratoAditivo.size());%></p>
                    <p class="announcement-text">Contratos Ativos</p>
                  </div>
                </div>
              </div>
              <a href="<c:url value="/aditivo/listarAditivos"/>">
                <div class="panel-footer announcement-bottom">
                  <div class="row">
                    <div class="col-xs-6">
                      Ver Contratos</div>
                    <div class="col-xs-6 text-right">
                      <i class="fa fa-arrow-circle-right"></i>
                    </div>
                  </div>
                </div>
              </a>
            </div>
          </div>
          <div class="col-lg-3">
            <div class="panel panel-warning">
              <div class="panel-heading">
                <div class="row">
                  <div class="col-xs-6">
                    <i class="fa fa-check fa-5x"></i>
                  </div>
                  <div class="col-xs-6 text-right">
                   <% List cotacaoAndamento = HibernateUtilTest.executarConsultaHQL("from Cotacao where status=4");%>
                    <p class="announcement-heading"><% out.println(cotacaoAndamento.size());%></p>
                    <p class="announcement-text">Cotações Andamento</p>
                  </div>
                </div>
              </div>
              <a href="<c:url value="/cotacao/listarCotacaos"/>">
                <div class="panel-footer announcement-bottom">
                  <div class="row">
                    <div class="col-xs-6">
                      Visualizar Cotações
                    </div>
                    <div class="col-xs-6 text-right">
                      <i class="fa fa-arrow-circle-right"></i>
                    </div>
                  </div>
                </div>
              </a>
            </div>
          </div>
          <div class="col-lg-3">
            <div class="panel panel-danger">
              <div class="panel-heading">
                <div class="row">
                  <div class="col-xs-6">
                    <i class="fa fa-tasks fa-5x"></i>
                  </div>
                  <div class="col-xs-6 text-right">
                    <% List cotacaoPendente = HibernateUtilTest.executarConsultaHQL("from Cotacao where status=5");%>
                    <p class="announcement-heading"><% out.println(cotacaoPendente.size());%></p>
                    <p class="announcement-text">Cotações Pendentes</p>
                  </div>
                </div>
              </div>
              <a href="<c:url value="/cotacao/listarCotacaos"/>">
                <div class="panel-footer announcement-bottom">
                  <div class="row">
                    <div class="col-xs-6">
                      Visualizar Cotações
                    </div>
                    <div class="col-xs-6 text-right">
                      <i class="fa fa-arrow-circle-right"></i>
                    </div>
                  </div>
                </div>
              </a>
            </div>
          </div>
          <div class="col-lg-3">
            <div class="panel panel-success">
              <div class="panel-heading">
                <div class="row">
                  <div class="col-xs-6">
                    <i class="fa fa-comments fa-5x"></i>
                  </div>
                  <div class="col-xs-6 text-right">
                   <% List pedidos = HibernateUtilTest.executarConsultaHQL("from Pedido");%>
                    <p class="announcement-heading"><% out.println(pedidos.size());%></p>
                    <p class="announcement-text">Pedidos</p>
                  </div>
                </div>
              </div>
              <a href="<c:url value="/pedido/listarPedidos"/>">
                <div class="panel-footer announcement-bottom">
                  <div class="row">
                    <div class="col-xs-6">
                      Visualizar Pedidos
                    </div>
                    <div class="col-xs-6 text-right">
                      <i class="fa fa-arrow-circle-right"></i>
                    </div>
                  </div>
                </div>
              </a>
            </div>
          </div>
        </div><!-- /.row -->    
       
        <h1><small><span style="color:#31708F">//</span> Como funciona o Processo ? </small></h1>

              <img src="<c:url value="/css/images/steps.png"/>" />         
        </div>

<%@ include file="/EQbaseFim.jsp" %> 