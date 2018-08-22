package com.zl.third.brother.utils.netty;

import android.util.Log;

import java.nio.ByteOrder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * Created by Administrator on 2018/3/6.
 */

public class NettyTCPClient {
    public static void goNet() {
        //创建一个NIO线程组
        EventLoopGroup loopGroup = new NioEventLoopGroup();
        try {
            //辅助工具类，用于服务器通道的一系列配置
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(loopGroup);//绑定线程组
            bootstrap.channel(NioSocketChannel.class);//指定NIO的模式
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);//保持连接
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
//                    ch.pipeline().addLast(new LengthFieldBasedFrameDecoder());
//                    ch.pipeline().addLast(new StringDecoder());
                    ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(ByteOrder.LITTLE_ENDIAN,Integer.MAX_VALUE,0,4,2,4,true));
                    ch.pipeline().addLast(new NettyTCPClientHandler());

                }
            });
            // 发起异步连接操作
            ChannelFuture future = bootstrap.connect("192.168.2.30", 11111).sync();

            if(future.isSuccess())
            {
                Log.d("123","TCP Client：Start Success------Port---" );
            }
            else
            {
                Log.d("123","TCP Client：Start Failed------Port---" );
            }

//            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
//            loopGroup.shutdownGracefully();
        }
    }
}
