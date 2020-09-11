package com.example.demo.socket.heartBeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @Author: chenweilong
 * @Date: 2020/8/28
 * @Description:
 **/
public class HeartBeatServer {

    int port;
    public HeartBeatServer(int port){
        this.port = port;
    }

    public void start(){
        EventLoopGroup pGroup = new NioEventLoopGroup();
        EventLoopGroup wGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        try{
            bootstrap.group(pGroup,wGroup)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .channel(NioServerSocketChannel.class)  // 设定为TCP连接
                    .childHandler(new HeartBeatInitializer());
            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            pGroup.shutdownGracefully();
            wGroup.shutdownGracefully();
        }
    }



}
