package com.example.demo.socket.heartBeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @Author: chenweilong
 * @Date: 2020/8/28
 * @Description:    心跳触发器
 **/
public class ClientIdleStateTrigger extends ChannelInboundHandlerAdapter {

    public static final String HEART_BEAT = "heart beat!";

    @Override
    public void userEventTriggered(ChannelHandlerContext context,Object evt)throws Exception{
        if(evt instanceof IdleStateEvent){
            IdleState state = ((IdleStateEvent) evt).state();
            if(state == IdleState.WRITER_IDLE){
                // 发送心跳给服务端
                context.writeAndFlush(HEART_BEAT);
            }
        }else {
            super.userEventTriggered(context,evt);
        }

    }

}
