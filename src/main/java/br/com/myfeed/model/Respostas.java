package br.com.myfeed.model;

import org.springframework.data.annotation.Id;

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

    public Respostas(){}

    public Respostas(String pergunta, String resposta) {
	this.pergunta = pergunta;
	this.resposta = resposta;
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
}
