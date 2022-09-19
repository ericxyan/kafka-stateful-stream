package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static final Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        
        try(InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties props = new Properties();
            
            props.load(input);
            
            log.info("props {}", props);

            KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

            ProducerRecord<String, String> record = new ProducerRecord<>("test", "test", "value");

            producer.send(record);

            producer.close();
        } catch(IOException e) {
            System.out.println(e);
        }
    }
}