package com.example.demo.socket.heartBeat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;

/**
 * @Author: chenweilong
 * @Date: 2020/8/28
 * @Description:    TCP连接的客户端
 **/
public class TcpClient {

    private String host;
    private int port;
    private Bootstrap bootstrap;
    private Channel channel;

    public TcpClient(String host,int port){

    }

}
