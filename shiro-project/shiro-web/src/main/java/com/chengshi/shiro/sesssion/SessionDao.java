package com.chengshi.shiro.sesssion;

import com.chengshi.shiro.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SerializationUtils;

import java.io.*;

@Slf4j
public class SessionDao extends EnterpriseCacheSessionDAO {

    @Autowired
    private RedisUtil redisUtil;

    // 创建session，保存到数据库
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        redisUtil.set(sessionId.toString(), sessionToByte(session), 1 * 60L);

        return sessionId;
    }

    // 获取session
    @Override
    protected Session doReadSession(Serializable sessionId) {
        // 先从缓存中获取session，如果没有再去数据库中获取
        log.info("read session");
        Session session = super.doReadSession(sessionId);
        if (session == null) {
            byte[] bytes = (byte[]) redisUtil.get(sessionId.toString());
            if (bytes != null && bytes.length > 0) {
//                session = byteToSession(bytes);
                session= (Session) SerializationUtils.deserialize(bytes);
            }

        }
        return session;
    }

    // 更新session的最后一次访问时间
    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
        redisUtil.set(session.getId().toString(), sessionToByte(session), 1 * 60L);
    }

    // 删除session
    @Override
    protected void doDelete(Session session) {
        super.doDelete(session);
        redisUtil.remove(session.getId().toString());
    }

    // 把session对象转化为byte保存到redis中
    public byte[] sessionToByte(Session session) {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        byte[] bytes = null;
        try {
            ObjectOutput oo = new ObjectOutputStream(bo);
            oo.writeObject(session);
            bytes = bo.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    // 把byte还原为session
    public Session byteToSession(byte[] bytes) {
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
        ObjectInputStream in;
        SimpleSession session = null;
        try {
            in = new ObjectInputStream(bi);
            session = (SimpleSession) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return session;
    }
}
