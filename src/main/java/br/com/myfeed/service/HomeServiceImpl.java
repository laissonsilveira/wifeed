package br.com.myfeed.service;

import br.com.myfeed.model.Respostas;
import com.mongodb.BasicDBObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class HomeServiceImpl implements HomeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeServiceImpl.class);

    @Autowired
    public MongoConfig mongo;

    @Override
    public Respostas save(Respostas respostas) throws Exception {
        mongo.mongoTemplate().save(respostas);
        return respostas;
    }

    @Override
    public List<Respostas> findAll() throws Exception {
        return mongo.mongoTemplate().findAll(Respostas.class);
    }

    @Override
    public List<BasicDBObject> countResponses() throws Exception {

        Aggregation agg = newAggregation(
                group(fields().and("per", "$pergunta").and("res", "$resposta")).count().as("respostaCount"),
                group(fields("$_id.per")).push(new BasicDBObject("resposta", "$_id.res").append("count", "$respostaCount"))
                        .as("respostas").sum("respostaCount").as("count"),
                sort(Sort.Direction.DESC, "count"));

        LOGGER.info(agg.toString());

        AggregationResults<BasicDBObject> basicDBObject = mongo.mongoTemplate().aggregate(agg, "respostas", BasicDBObject.class);
        return basicDBObject.getMappedResults();
    }
}
