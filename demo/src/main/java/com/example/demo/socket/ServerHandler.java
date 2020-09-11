package com.example.demo.socket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author: chenweilong
 * @Date: 2020/8/27
 * @Description:
 **/
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext context) throws Exception{
        System.out.println("Server channel active....");
    }

    /**
     * 读取收到的消息，并可返回消息
     * @param context
     * @param message   消息内容
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext context,Object message) throws Exception{
        ByteBuf buf = (ByteBuf) message;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req); // 读取收到的消息进req
        String body = new String(req, "utf-8");
        System.out.println("收到的消息:"+body);
        String response = "返回给客户端的响应："+body;
        context.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
        // future完成后触发监听器, 此处是写完即关闭(短连接). 因此需要关闭连接时, 要通过server端关闭. 直接关闭用方法ctx[.channel()].close()
        //.addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 读取完数据之后进行的处理
     * @param context
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext context) throws Exception{
        ChannelFuture channelFuture = context.writeAndFlush(Unpooled.copiedBuffer("服务器读完后返回的消息".getBytes()));
        System.out.println("读完了");
        context.flush();
        channelFuture.channel().closeFuture();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context,Throwable throwable) throws Exception{
//        System.out.println("exceptionCaught run");
        context.close();
    }

}
