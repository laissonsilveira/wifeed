package br.com.myfeed.service;

import br.com.myfeed.model.Respostas;
import com.mongodb.BasicDBObject;

import java.util.List;

public interface HomeService {

    Respostas save(Respostas respostas) throws Exception;

    List<Respostas> findAll() throws Exception;

    List<BasicDBObject> countResponses() throws Exception;

}
