package br.com.myfeed.service;

import br.com.myfeed.model.Respostas;
import com.mongodb.BasicDBObject;

import java.util.List;

public interface HomeService {

    Respostas save(Respostas respostas);

    List<Respostas> findAll();

    List<BasicDBObject> countResponses();

}
