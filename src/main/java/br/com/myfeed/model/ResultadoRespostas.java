package br.com.myfeed.model;/**
 * Created by laissonsilveira on 11/10/16.
 */

import java.util.List;

/**
 * @author Laisson R. Silveira
 *         laisson.r.silveira@gmail.com
 *         11/10/16
 **/
public class ResultadoRespostas {

    private String _id;
    private List<RespostaCount> respostas;

    public ResultadoRespostas() {}

    public String get_id() {
        return _id;
    }

    public List<RespostaCount> getRespostas() {
        return respostas;
    }
}
