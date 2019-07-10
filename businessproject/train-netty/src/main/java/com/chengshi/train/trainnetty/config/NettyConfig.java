package com.chengshi.train.trainnetty.config;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author tjshan
 * @date 2019/5/4 21:00
 */
public class NettyConfig {

    /**
     * 存储每一个客户端接进来的channel配置对象
     */
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

}
