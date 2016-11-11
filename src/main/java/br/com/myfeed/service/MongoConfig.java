package br.com.myfeed.service;/**
 * Created by laissonsilveira on 11/10/16.
 */

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static java.util.Collections.singletonList;

/**
 * @author Laisson R. Silveira
 *         laisson.r.silveira@gmail.com
 *         11/10/16
 **/
//@Configuration
//@EnableMongoRepositories()
//public class MongoConfig extends AbstractMongoConfiguration {
//
//    @Override
//    protected String getDatabaseName() {
//        return "wifeed";
//    }
//
//    @Override
//    public Mongo mongo() throws Exception {
//        return new MongoClient("localhost", 27017);
//    }
//
//    @Override
//    @Bean
//    public SimpleMongoDbFactory mongoDbFactory() throws Exception {
//        return new SimpleMongoDbFactory(mongo(), getDatabaseName());
//    }
//
//    @Override
//    @Bean
//    public MongoTemplate mongoTemplate() throws Exception {
//        final UserCredentials userCredentials = new UserCredentials("laisson", "admin");
//
//        final MongoTemplate mongoTemplate = new MongoTemplate(mongo(), getDatabaseName(), userCredentials);
//        mongoTemplate.setWriteConcern(WriteConcern.SAFE);
//
//        return mongoTemplate;
//    }
//
//}
@Configuration
@EnableMongoRepositories("br.com.myfeed.service.HomeServiceImpl")
public class MongoConfig extends AbstractMongoConfiguration {

    private final Logger log = LoggerFactory.getLogger(MongoConfig.class);


    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private Integer port;

    @Value("${spring.data.mongodb.username}")
    private String username;

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.password}")
    private String password;

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }


    @Override
    public String getDatabaseName() {
        return database;
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient(singletonList(new ServerAddress(host, port))/*,
                singletonList(MongoCredential.createCredential(username,database, password.toCharArray()))*/);
    }

}
