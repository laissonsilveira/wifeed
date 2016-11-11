package br.com.myfeed.service;/**
 * Created by laissonsilveira on 11/10/16.
 */

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author Laisson R. Silveira
 *         laisson.r.silveira@gmail.com
 *         11/10/16
 **/
@Configuration
@EnableMongoRepositories()
public class MongoConfig extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "heroku_n4tzt19h";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient("ds151137.mlab.com", 51137);
    }

    @Override
    @Bean
    public SimpleMongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(mongo(), getDatabaseName());
    }

    @Override
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        final UserCredentials userCredentials = new UserCredentials("laisson", "admin");

        final MongoTemplate mongoTemplate = new MongoTemplate(mongo(), getDatabaseName(), userCredentials);
        mongoTemplate.setWriteConcern(WriteConcern.SAFE);

        return mongoTemplate;
    }

}
