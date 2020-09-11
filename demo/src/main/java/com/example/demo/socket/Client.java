package com.example.demo.socket;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Author: chenweilong
 * @Date: 2020/8/27
 * @Description:
 **/
public class Client {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new ClientHandler());
                    }
                });
        ChannelFuture cf1 = b.connect("127.0.0.1", 8765).sync();
        ChannelFuture cf2 = b.connect("127.0.0.1", 8764).sync();

        // 发送消息
        cf1.channel().writeAndFlush(Unpooled.copiedBuffer("111".getBytes()));
        Thread.sleep(1000);
        cf2.channel().writeAndFlush(Unpooled.copiedBuffer("222".getBytes()));

        Thread.sleep(10000);

        // 关闭
        cf1.channel().closeFuture();
        cf2.channel().closeFuture();
        group.shutdownGracefully();

    }

}
