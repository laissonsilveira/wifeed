package br.com.myfeed.service;

import br.com.myfeed.model.Respostas;
import com.mongodb.BasicDBObject;
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

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(HomeServiceImpl.class);

    @Autowired
    public MongoConfig mongo;

    @Override
    public Respostas save(Respostas respostas) {
	mongo.getMongoOperation().save(respostas);
	return respostas;
    }

    //Convert the aggregation result into a List
    //AggregationResults<HostingCount> groupResults = mongoTemplate.aggregate(agg, Domain.class, HostingCount.class);
    //List<HostingCount> result = groupResults.getMappedResults();
    //return result;

    @Override
    public List<Respostas> findAll() {
	return mongo.getMongoOperation().findAll(Respostas.class);
    }

    @Override
    public List<BasicDBObject> countResponses() {

	Aggregation agg = newAggregation(
			group(fields().and("per", "$pergunta").and("res", "$resposta")).count().as("respostaCount"),
			group(fields("$_id.per")).push(new BasicDBObject("resposta", "$_id.res").append("count", "$respostaCount")).as("respostas")
					.count().as("count"),
			sort(Sort.Direction.DESC, "count"));

	LOGGER.info(agg.toString());

	AggregationResults<BasicDBObject> basicDBObject = mongo.getMongoOperation().aggregate(agg, "respostas", BasicDBObject.class);
	return basicDBObject.getMappedResults();
    }
}
