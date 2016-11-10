package br.com.myfeed.service;

import br.com.myfeed.model.Respostas;

import java.util.List;

public interface HomeService {

    Respostas save(Respostas respostas);

    List<Respostas> findAll();

}
