package com.zl.third.brother.utils.netty;

import android.util.Log;

import com.zl.third.brother.utils.BufferUtils;
import com.zl.third.brother.utils.netty.protobuf.ZLBaseTest;
import com.zl.third.brother.utils.netty.protobuf.ZLTest;

import java.nio.ByteOrder;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;


/**
 * Created by Administrator on 2018/3/6.
 */

public class NettyTCPClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Log.d("123", "TCP Client：NettyTCPClientHandler --channelRead");
        try {
//            ByteBuf buf = (ByteBuf) msg;
//            byte[] data = new byte[buf.readableBytes()];
//            buf.readBytes(data);
//            System.out.println("Client：" + new String(data).trim());


//            String buf = (String) msg;
//            System.out.println("Client：" + buf);
            ByteBuf buf = (ByteBuf) msg;
            int code = BufferUtils.readShort(buf);
            byte[] data = BufferUtils.byteBufToBytes(buf);
            switch (code) {
                case ZLTest.E_ALL_TEST.ALL_TEST_THREE_VALUE:
                    ZLTest.S2C_GetUserInfo respone = ZLTest.S2C_GetUserInfo.parseFrom(data);

                    ZLBaseTest.TsetInfo str = respone.getInfo();
                    List<ZLTest.AllInfo> allInfo = respone.getAllInfoList();
                    StringBuilder sb = new StringBuilder();
                    for (ZLTest.AllInfo list : allInfo) {
                        sb.append(list.getBother());
                        sb.append(list.getMother());
                        sb.append(list.getFather());
                    }

                    int gradan = respone.getInfo().getGradan();
                    int sex = respone.getInfo().getSex();
//                    int sex = baseRespoe.getSex();
                    int age = respone.getAge();
                    String name = respone.getName();
                    System.out.println("服务端返回: " + "年龄：" + age + "     姓名：" + name + "     性别：" + sex + "     gradan:" + gradan + "     " + sb.toString());
                    break;
                default:
                    break;
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        Log.d("123", "TCP Client：NettyTCPClientHandler --channelReadComplete");
        ctx.flush();// 将消息发送队列中的消息写入到SocketChannel中发送给对方。
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ZLTest.C2S_GetUserInfo.Builder request = ZLTest.C2S_GetUserInfo.newBuilder();
        ZLBaseTest.TsetInfo.Builder baseRequest = ZLBaseTest.TsetInfo.newBuilder();

        ZLTest.AllInfo.Builder allInfo = ZLTest.AllInfo.newBuilder();

        for (int i = 0; i < 2; i++) {
            allInfo.setBother("哥哥" + i);
            allInfo.setFather("爸爸" + i);
            allInfo.setMother("妈妈" + i);
            request.addAllInfo(allInfo);
        }

        baseRequest.setGradan(10);
        baseRequest.setSex(10);
        request.setUserId(1);
        request.setContent("你好");
        request.setInfo(baseRequest);
//        ctx.channel().writeAndFlush(Unpooled.copiedBuffer("777".getBytes()));

        byte[] data = request.build().toByteArray();

        final ByteBuf byteBuf = Unpooled.buffer();
        int length = data == null ? 0 : data.length;
        if (ByteOrder.nativeOrder().toString().equals(ByteOrder.LITTLE_ENDIAN.toString())) {
            byteBuf.writeIntLE(length);//总包长
            byteBuf.writeShortLE(ZLTest.E_ALL_TEST.ALL_TEST_THREE_VALUE);//长度为2
        } else {
            byteBuf.writeInt(length);//总包长
            byteBuf.writeShort(ZLTest.E_ALL_TEST.ALL_TEST_THREE_VALUE);//长度为2
        }
        if (data != null) {
            byteBuf.writeBytes(data);
        }
        ctx.channel().writeAndFlush(byteBuf);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Log.d("123", "TCP Client：NettyTCPClientHandler --exceptionCaught");
        cause.printStackTrace();
        ctx.close();
    }
}
