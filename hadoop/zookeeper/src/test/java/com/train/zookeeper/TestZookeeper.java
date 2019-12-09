package com.train.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author tjshan
 * @date 2019/12/9 11:27
 */
public class TestZookeeper {

    private final static String CONNECT_ADDRESS="192.168.233.135:2181";
    private final static int sessionTimeout=2000;
    private ZooKeeper zkClient;

    @Before
    public void init() throws IOException {

        zkClient = new ZooKeeper(CONNECT_ADDRESS, sessionTimeout, watchedEvent -> {
            System.out.println("path::"+ watchedEvent.getPath());
            System.out.println("type::"+ watchedEvent.getType());
            System.out.println("state::"+ watchedEvent.getState());
            try {
                List<String> children = zkClient.getChildren("/company", true);
                System.out.println("====================");
                children.forEach(System.out::println);
                System.out.println("====================");
            } catch (KeeperException e) {
            } catch (InterruptedException e) {
            }
        });

    }

    //创建节点
    @Test
    public void create() throws KeeperException, InterruptedException {
//        String  path= zkClient.create("/company", "company".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        String  path= zkClient.create("/company/jinrong", "jinrong".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("====================");
        System.out.println(path);
    }

    @Test
    public void getChildren() throws KeeperException, InterruptedException {
        List<String> children = zkClient.getChildren("/company", true);
        System.out.println("-------------------");
        children.forEach(System.out::println);
        System.out.println("-------------------");

        TimeUnit.SECONDS.sleep(1200);
    }

    @Test
    public void exists() throws KeeperException, InterruptedException {
        Stat exists = zkClient.exists("/company/temp", true);
        System.out.println("-------------------");
        System.out.println(exists);
        System.out.println(exists!=null?"exist":"not exist");
        System.out.println("-------------------");

        TimeUnit.SECONDS.sleep(1200);
    }

    @Test
    public void set() throws KeeperException, InterruptedException {
        System.out.println("-------------------");
        Stat stat = zkClient.setData("/company", "guangdian".getBytes(), -1);
        System.out.println(stat);
    }

    @Test
    public void get() throws KeeperException, InterruptedException {
        byte[] data = zkClient.getData("/company", null,null);

        System.out.println("-------------------");
        System.out.println(new String(data));

    }
}
