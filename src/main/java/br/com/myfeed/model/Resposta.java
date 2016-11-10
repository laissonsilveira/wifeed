package br.com.myfeed.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Laisson R. Silveira
 *         laisson.r.silveira@gmail.com
 *         11/08/16
 **/
@Document(collection = "respostas")
public class Resposta {

    @Id
    private String id;

    private String pergunta;
    private String resposta;

    public Resposta() {

    }

    public Resposta(String pergunta, String resposta) {
	this.setPergunta(pergunta);
	this.setResposta(resposta);
    }

    public String getPergunta() {
	return pergunta;
    }

    public void setPergunta(String pergunta) {
	this.pergunta = pergunta;
    }

    public String getResposta() {
	return resposta;
    }

    public void setResposta(String resposta) {
	this.resposta = resposta;
    }

    @Override
    public String toString() {
	return String.format(
			"Respostas[id=%s, pergunta='%s', resposta='%s']",
			id, getPergunta(), getResposta());
    }
}
