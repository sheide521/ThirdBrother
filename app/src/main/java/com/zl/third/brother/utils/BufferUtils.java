package com.zl.third.brother.utils;

import java.nio.ByteOrder;

import io.netty.buffer.ByteBuf;

public class BufferUtils {
    public static byte[] byteBufToBytes(ByteBuf byteBuf)
    {
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);

        return bytes;
    }
    public static short readShort(ByteBuf buffer)
    {
        if(ByteOrder.nativeOrder().toString().equals(ByteOrder.BIG_ENDIAN.toString())){
            return buffer.readShort();
        }else{
            return buffer.readShortLE();
        }
    }

}
