package com.chengshi.shiro.session;

import com.chengshi.shiro.util.RedisUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SerializationUtils;

import java.io.*;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-06 21:31
 */
public class SessionDao1 extends EnterpriseCacheSessionDAO {
    @Autowired
    RedisUtil redisUtil;

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        redisUtil.set(sessionId.toString(), sessionToByte(session), 1 * 60L);

        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = super.doReadSession(sessionId);
        if(session == null){
            byte[] bytes = (byte[]) redisUtil.get(sessionId.toString());
            if(bytes != null && bytes.length > 0){
                session = byteToSession(bytes);
            }
        }
        return session;

    }

    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
        redisUtil.set(session.getId().toString(), sessionToByte(session),1*60L);
    }

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

//        return (Session) SerializationUtils.deserialize(bytes);

        return session;
    }

}
