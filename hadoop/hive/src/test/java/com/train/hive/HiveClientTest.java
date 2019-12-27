package com.train.hive;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author tjshan
 * @date 2019/12/27 10:33
 */
@SpringBootTest
public class HiveClientTest {
    private String connectSingleString="hadoop05:2181";
    private String connectClusterString="hadoop02:2181,hadoop03:2181,hadoop04:2181";
    private int sessionTimeout=2000;
    private ZooKeeper zooKeeper;
    private boolean init=true;

    @Before
    public void init() throws IOException {

       zooKeeper = new ZooKeeper(connectSingleString, sessionTimeout, new Watcher() {
           @Override
           public void process(WatchedEvent event) {
               if (!init){
                   System.out.println("=========start=============");
                   String path = event.getPath();
                   System.out.println("watch:"+event.getPath());
                   System.out.println("eventName:"+event.getType().name());


                   if (event.getType()==Event.EventType.NodeChildrenChanged){
                   List<String> children;
                   try {
                       children = zooKeeper.getChildren(path, true);
                       children.forEach(System.out::println);
                   } catch (KeeperException e) {
                       e.printStackTrace();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   }else if(event.getType()== Event.EventType.NodeDataChanged){
                       try {
                           byte[] data = zooKeeper.getData(path, true, new Stat());
                           System.out.println("data data change::"+data.toString());
                       } catch (KeeperException | InterruptedException e) {
                           e.printStackTrace();
                       }
                   }else if (event.getType()==Event.EventType.NodeCreated || event.getType()==Event.EventType.NodeDeleted){
                       try {
                           byte[] data = zooKeeper.getData(path, true, new Stat());
                           System.out.println("data create or delete::"+data.toString());
                       } catch (KeeperException | InterruptedException e) {
                           e.printStackTrace();
                       }
                   }

                   System.out.println("========end==============");
               }

           }
       });
    }

    //创建节点
    @Test
    public void createNode() throws KeeperException, InterruptedException {
        String data="hiveData";
        String path = zooKeeper.create("/hivePath", data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        System.out.println("create node :"+path);
    }

    @Test
    public void getChildren() throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren("/", true);
        children.forEach(System.out::println);
        init=false;
        TimeUnit.SECONDS.sleep(300);
    }


    @Test
    public void getExist() throws KeeperException, InterruptedException {
        Stat exists = zooKeeper.exists("/hivePath", true);
        System.out.println(exists);
        System.out.println(exists!=null?"exist":"not exist");
        init=false;
        TimeUnit.SECONDS.sleep(300);
    }


    @Test
    public  void getData() throws KeeperException, InterruptedException {
        byte[] data = zooKeeper.getData("/hivePath", true, new Stat());
        System.out.println("data::"+new String(data));
        init=false;
        TimeUnit.SECONDS.sleep(300);
    }
}
