package br.com.myfeed.controller;

import br.com.myfeed.model.Resposta;
import br.com.myfeed.service.HomeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private HomeServiceImpl homeService;


    @RequestMapping(value="/salvarresposta", method= RequestMethod.POST)
    public ResponseEntity<Object> createResposta(@RequestBody Resposta resposta, UriComponentsBuilder ucBuilder) {

	System.out.println("Salvando resposta... " + resposta);

	try {
	    homeService.save(resposta);
	} catch (Exception e) {
	    System.out.println("Error: " + e.toString());
	}

	return new ResponseEntity<Object>(resposta, HttpStatus.OK);


    }

    @RequestMapping(value="/listarespostas", method= RequestMethod.GET)
    public ResponseEntity<Object> listaRespostas() {

	List<Resposta> respostas = null;
	try {
	    respostas = homeService.findAll();
	} catch (Exception e) {
	    System.out.println("Error: " + e.toString());
	}

	return new ResponseEntity<Object>(respostas == null ? "Nenhuma resposta encontrada." : respostas, HttpStatus.OK);

    }
}
