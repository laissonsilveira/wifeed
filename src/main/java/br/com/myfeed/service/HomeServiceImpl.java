package br.com.myfeed.service;

import br.com.myfeed.model.Respostas;
import br.com.myfeed.model.ResultadoRespostas;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class HomeServiceImpl implements HomeService {

    ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");

    MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

    @Override
    public Respostas save(Respostas respostas) {
        mongoOperation.save(respostas);
        return respostas;
    }

    //Convert the aggregation result into a List
    //AggregationResults<HostingCount> groupResults = mongoTemplate.aggregate(agg, Domain.class, HostingCount.class);
    //List<HostingCount> result = groupResults.getMappedResults();
    //return result;

    @Override
    public List<Respostas> findAll() {
        //db.respostas.aggregate([{"$group":{"_id":{"per":"$pergunta","res":"$resposta"},"respostaCount":{"$sum":1}}},{"$group":{"_id":"$_id.per","respostas":{"$push":{"resposta":"$_id.res","count":"$respostaCount"}},"count":{"$sum":"$respostaCount"}}},{"$sort":{"count":-1}}]).pretty()

        Aggregation agg = newAggregation(
                group(
                        fields("per", "$pergunta").and("res", "$resposta")
                ).count().as("respostaCount"),

                group(
                        fields("_id", "$_id.per")
                ).push(
                        fields("resposta", "$_id.res").and("count", "$respostaCount")
                ).as("respostas").sum("$respostaCount").as("count"),

                sort(Sort.Direction.DESC, "count"));

        AggregationResults<ResultadoRespostas> groupResults = mongoOperation.aggregate(agg, Respostas.class, ResultadoRespostas.class);
        List<ResultadoRespostas> results = groupResults.getMappedResults();
        return mongoOperation.findAll(Respostas.class);
    }
}
