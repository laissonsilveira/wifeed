package br.com.myfeed;

import br.com.myfeed.config.WebAppInitializer;
import br.com.myfeed.model.Respostas;
import br.com.myfeed.service.HomeService;
import br.com.myfeed.service.MongoConfig;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author Laisson R. Silveira
 *         laisson.r.silveira@gmail.com
 *         08/11/16
 **/
@ContextConfiguration(classes = {WebAppInitializer.class})
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations="classpath:application.properties")
public class RespostasRepositoryTest {

    private static final String PERGUNTA = "Teste funcionou?";
    private static final String RESPOSTA = "SIM";

    @Autowired
    private HomeService homeService;

    @Autowired
    private MongoConfig mongo;

    private Respostas resInserted;

    @Before
    public void init() throws Exception {
        Respostas resInsert = new Respostas(PERGUNTA, RESPOSTA, new Date());
        resInserted = homeService.save(resInsert);
    }

    @After
    public void reset() throws Exception {
        if (resInserted != null) {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(resInserted.getId()));
            mongo.mongoTemplate().findAllAndRemove(query, Respostas.class);
        }
    }

    @Test
    public void testSave() throws Exception {
        Respostas resByID = mongo.mongoTemplate().findById(resInserted.getId(), Respostas.class);
        assertThat(resByID.getPergunta(), (is(equalTo(PERGUNTA))));
        assertThat(resByID.getResposta(), (is(equalTo(RESPOSTA))));
    }

    @Test
    public void testFindAll() throws Exception {
        List<Respostas> responses = homeService.findAll();
        assertThat(responses.size(), is(greaterThan(0)));
    }

    @Test
    public void testCountResponses() throws Exception {
        List<BasicDBObject> responses = homeService.countResponses();
        BasicDBObject o = null;

        for (BasicDBObject res : responses) {
            if (res.get("_id").equals(PERGUNTA)) {
                BasicDBList listRes = (BasicDBList) res.get("respostas");
                o = (BasicDBObject) listRes.get(0);
                break;
            }
        }

        assertThat(o.get("count"), is(equalTo(1)));
    }

}
