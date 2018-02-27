package br.com.caelum.estoque.modelo.usuario;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class InfoFault {

	@XmlElement(name="dateOfFault")
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date dataErro;
	
	@XmlElement(name="detailMessage")
	private String mensagem;

	public InfoFault() {

	}

	public InfoFault(Date dataErro, String mensagem) {
		this.dataErro = dataErro;
		this.mensagem = mensagem;
	}
}
