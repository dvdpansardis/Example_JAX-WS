package br.com.caelum.estoque.ws;

import javax.xml.ws.Endpoint;

public class PublicaWebService {

	public static void main(String[] args) {
		String uri1 = "http://localhost:8080/estoque";
		String uri2 = "http://localhost:8080/relatorio";

		EstoqueWS servico = new EstoqueWS();
		RelatorioService relatorio = new RelatorioService();
		
		Endpoint.publish(uri1, servico);
		Endpoint.publish(uri2, relatorio);
		
		System.out.println("Servidor rodando, url " + uri1);
		System.out.println("Servidor rodando, url " + uri2);
		
	}

}
