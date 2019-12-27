package com.train.hive.servicedynamic;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author tjshan
 * @date 2019/12/27 16:38
 */
public class DistributeClient {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        DistributeClient client=new DistributeClient();
        client.getConnect();
        client.getWatch();
        client.bussiness();
    }

    private String connectSingleString = "hadoop05:2181";
    private String connectClusterString = "hadoop02:2181,hadoop03:2181,hadoop04:2181";
    private int sessionTimeout = 2000;
    private String parentNode="/servers";
    private ZooKeeper zkClient;

    private void bussiness() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3000);
    }

    private void getWatch() throws KeeperException, InterruptedException {
        List<String> hosts=new ArrayList<>();
        List<String> children = zkClient.getChildren(parentNode, true);
        for (String child :children){
            byte[] data = zkClient.getData(parentNode+"/" + child, false, null);
            hosts.add(new String(data));
        }
        System.out.println(hosts);
    }

    private void getConnect() throws IOException {
        zkClient=new ZooKeeper(connectSingleString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                try {
                    getWatch();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
