package com.train.elasticsearch;

import com.train.elasticsearch.spring.Sender;
import com.train.elasticsearch.springboot.RabbitProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrainRabbitmqApplicationTests {

    @Autowired
    private Sender sender;
    @Autowired
    private RabbitProducer producer;


    @Test
    public void hello() throws InterruptedException {
        sender.send();
    }

    @Test
    public void producerSend(){
        producer.fanoutSend();
    }

    @Test
    public void testStringSend() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(1);
            producer.stringSend();
        }
    }

    @Test
    public void testFanoutSend() {
        producer.fanoutSend();
    }

    @Test
    public void testTopic() {
        producer.topicTopic1Send();
        producer.topicTopic2Send();
        producer.topicTopic3Send();
    }

}
