package br.com.myfeed.service;/**
 * Created by laissonsilveira on 11/10/16.
 */

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 * @author Laisson R. Silveira
 *         laisson.r.silveira@gmail.com
 *         11/10/16
 **/
@Configuration
public class MongoConfig {

    ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");
    MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

    @Bean
    public MongoOperations getMongoOperation() {
	return mongoOperation;
    }
}
