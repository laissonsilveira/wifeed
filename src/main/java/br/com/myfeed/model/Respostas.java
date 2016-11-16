package br.com.myfeed.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * @author Laisson R. Silveira
 *         laisson.r.silveira@gmail.com
 *         08/11/16
 **/
public class Respostas {

    @Id
    private String id;

    private String pergunta;
    private String resposta;
    private Date data = new Date();

    public Respostas() {
    }

    public Respostas(String pergunta, String resposta, Date data) {
	this.pergunta = pergunta;
	this.resposta = resposta;
	this.data = data;
    }

    public String getId() {
	return id;
    }

    public String getPergunta() {
	return pergunta;
    }

    public String getResposta() {
	return resposta;
    }

    public Date getData() {
	return data;
    }

    @Override public String toString() {
	return "Resposta: id=[" + getId() + "]"
			+ ", pergunta=[" + getPergunta() + "]"
			+ ", resposta=[" + getResposta() + "]"
			+ ", data=[" + getData() + "]";
    }
}
