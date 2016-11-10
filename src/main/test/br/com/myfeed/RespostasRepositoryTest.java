package br.com.myfeed;

import br.com.myfeed.config.WebAppInitializer;
import br.com.myfeed.model.Respostas;
import br.com.myfeed.service.HomeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@ContextConfiguration(classes = {WebAppInitializer.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class RespostasRepositoryTest {

    @Autowired
    private HomeService homeService;

    @Autowired
    private MongoOperations mongoOps;

//    @Before
//    public void reset() {
//
//    }

//    @Test
//    public void testFindOne() {
//        Continent asia = homeService.findOne(2L);
//        assertThat(asia.getName(), is(equalTo("Asia")));
//    }

    @Test
    public void testSave() {
        String id = "-111";
        String pergunta = "Teste unitário funcionou?";
        String resposta = "SIM";

        Respostas res = new Respostas(id, pergunta, resposta);

        Respostas resInserted = homeService.save(res);

        assertThat(resInserted.getPergunta(), is(equalTo(pergunta)));
        assertThat(resInserted.getResposta(), is(equalTo(resposta)));

//        assertThat(mongoOps.find(new Query(Criteria.where("_id").is(id)), Respostas.class).get(0).getPergunta(), is(equalTo(pergunta)));

//        Query query = new Query();
//        query.addCriteria(Criteria.where("_id").is("frog"));
//        mongoOps.remove(query, Respostas.class);

//        assertThat(mongoOps.findById(id, Respostas.class), is(equalTo(null)));
    }

}
