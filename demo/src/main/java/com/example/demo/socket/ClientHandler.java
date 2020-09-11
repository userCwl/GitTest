package com.example.demo.socket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @Author: chenweilong
 * @Date: 2020/8/28
 * @Description:
 **/
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext context)throws Exception{

    }

    @Override
    public void channelRead(ChannelHandlerContext context,Object message)throws Exception{
        try {
            ByteBuf buf = (ByteBuf) message;
            byte[] req = new byte[buf.readableBytes()]; // 设置可读大小
            buf.readBytes(req); // 读进req
            String body = new String(req, "utf-8");
            System.out.println("收到消息：" + body);
        }finally {
            // write数据，message引用将被自动释放不用手动处理；但只读数据，必须手动释放引用
            ReferenceCountUtil.release(message);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext context)throws Exception{

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context,Throwable cause)throws Exception{
        context.close();
    }

}
