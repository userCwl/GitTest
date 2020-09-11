package com.example.demo.socket.heartBeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @Author: chenweilong
 * @Date: 2020/8/28
 * @Description:
 **/
public class HeartBeatHandler extends SimpleChannelInboundHandler<String> {

    int readIdleTimes = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        System.out.println(" ====== > [server] message received : " + s);
        if("I am alive".equals(s)){
            ctx.channel().writeAndFlush("copy that");
        }else {
            System.out.println(" 其他信息处理 ... ");
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext context,Object evt)throws Exception{
        IdleStateEvent event = (IdleStateEvent)evt;

        String eventType = null;
        switch (event.state()){
            case READER_IDLE:
                eventType = "读空闲";
                readIdleTimes++;    // 读空闲计数加一
                break;
            case WRITER_IDLE:
                eventType = "写空闲";
                // 不处理
                break;
            case ALL_IDLE:
                eventType = "读写空闲";
                // 不处理
                break;
        }
        System.out.println(context.channel().remoteAddress() + "超时事件：" + eventType);
        if(readIdleTimes > 3){
            System.out.println(" [server]读空闲超过3次，关闭连接");
            context.channel().writeAndFlush("you are out");
            context.channel().close();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.err.println("=== " + ctx.channel().remoteAddress() + " is active ===");
    }

}
