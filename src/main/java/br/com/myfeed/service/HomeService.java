package br.com.myfeed.service;

import br.com.myfeed.model.Resposta;

import java.util.List;

public interface HomeService {

    Resposta save(Resposta resposta);

    List<Resposta> findAll();

}
