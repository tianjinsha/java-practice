package com.train.hive.servicedynamic;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author tjshan
 * @date 2019/12/27 16:24
 */
public class DistributeServer {
    public DistributeServer() {
    }

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        System.out.println("---start--------"+args.length);
        System.out.println(args[0]);
        DistributeServer server = new DistributeServer();
        //连接
        server.getConnect();
        //注册
        server.getRegister(args[0]);
        //业务逻辑
        server.business();
    }

    private String connectSingleString = "hadoop05:2181";
    private String connectClusterString = "hadoop02:2181,hadoop03:2181,hadoop04:2181";
    private int sessionTimeout = 2000;
    private ZooKeeper zkClient;

    private void business() throws InterruptedException {

        TimeUnit.SECONDS.sleep(300);
    }

    private void getRegister(String hostname) throws KeeperException, InterruptedException {

        zkClient.create("/servers/server", hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(hostname + "is online");
    }

    private void getConnect() throws IOException {
        zkClient = new ZooKeeper(connectSingleString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {

            }
        });
    }
}
