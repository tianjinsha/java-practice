package com.train.elasticsearch.config;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author tjshan
 * @date 2019/8/6 14:20
 */
@Slf4j
@Configuration
public class ESConfig {

    @Value("${elasticsearch.ip}")
    private String hostname;

    @Value("${elasticsearch.port}")
    private String port;

    @Value("${elasticsearch.cluster.name}")
    private String clusterName;

    @Value("${elasticsearch.pool}")
    private String poolSize;


    @Bean
    public TransportClient transportClient(){
        log.info("-----ElasticSearch初始化开始-----");
        TransportClient client;
        Settings settings= Settings.builder()
                //集群名称
                .put("cluster.name",clusterName)
                //正佳嗅探机制，找打es集群
                .put("client.transport.sniff",true)
                //正佳线程池个数
                .put("thread_pool.search.size",Integer.parseInt(poolSize))
                .build();
        client=new PreBuiltTransportClient(settings);
        try {
            TransportAddress transportAddress=new TransportAddress(InetAddress.getByName(hostname),Integer.parseInt(port));
            client.addTransportAddresses(transportAddress);
        } catch (UnknownHostException e) {
           log.error("ElasticSearch TransportClient create error !");
        }
        return client;
    }
}
