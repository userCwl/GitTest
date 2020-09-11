package com.example.demo.socket.heartBeat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.ScheduledFuture;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author: chenweilong
 * @Date: 2020/8/28
 * @Description:    心跳发射器
 **/
public class Pinger extends ChannelInboundHandlerAdapter {

    private Random random = new Random();
    private int baseRandom = 8;

    private Channel channel;

    @Override
    public void channelActive(ChannelHandlerContext context)throws Exception{
        super.channelActive(context);
        this.channel = context.channel();

        ping(context.channel());
    }

    private void ping(Channel channel){
        int second = Math.max(1,random.nextInt(baseRandom));
        System.out.println("next heartbeat will send after " + second +" s.");
        ScheduledFuture<?> future = channel.eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                if(channel.isActive()){
                    System.out.println("sending heartbeat to the server...");
                    channel.writeAndFlush(ClientIdleStateTrigger.HEART_BEAT);
                }else {
                    System.out.println("the connection had broken,cancel the task that will send a heartbeat.");
                    channel.closeFuture();
                    throw new RuntimeException();
                }
            }
        }, second, TimeUnit.SECONDS);

        future.addListener(new GenericFutureListener() {
            @Override
            public void operationComplete(Future future) throws Exception {
                if(future.isSuccess()){
                    ping(channel);
                }
            }
        });

    }


    public void exceptionCaught(ChannelHandlerContext context,Throwable cause)throws Exception{
        // 当Channel已经断开的情况下，仍然会发送数据，会抛出异常，该方法会被调用
        cause.printStackTrace();
        context.close();
    }


}
