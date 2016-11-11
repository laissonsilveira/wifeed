package br.com.myfeed.controller;

import br.com.myfeed.model.Respostas;
import br.com.myfeed.service.HomeServiceImpl;
import com.mongodb.BasicDBObject;
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


    @RequestMapping(value = "/salvarresposta", method = RequestMethod.POST)
    public ResponseEntity<Object> createResposta(@RequestBody Respostas respostas, UriComponentsBuilder ucBuilder) {

        System.out.println("Salvando respostas... " + respostas);

        try {
            homeService.save(respostas);
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }

        return new ResponseEntity<Object>(respostas, HttpStatus.OK);
    }

    @RequestMapping(value = "/listarespostas", method = RequestMethod.GET)
    public ResponseEntity<Object> listaRespostas() {

        List<Respostas> result = null;
        try {
            result = homeService.findAll();
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }

        return new ResponseEntity<Object>(result == null ? "Nenhuma resposta encontrada." : result, HttpStatus.OK);

    }

    @RequestMapping(value = "/countrespostas", method = RequestMethod.GET)
    public ResponseEntity<Object> countRespostas() {

        List<BasicDBObject> result = null;
        try {
            result = homeService.countResponses();
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }

        return new ResponseEntity<Object>(result == null ? "Nenhuma resposta encontrada." : result, HttpStatus.OK);

    }
}
