package mntm.top.offer.nio;

/**
 * @program: perf
 * @description: NIO Buffer
 * @author: yingjun
 * @create: 2020-05-20 18:41
 **/

import java.nio.ByteBuffer;

/**
 * buffer 基本用法
 *      1、写入数据到 buffer  ： a、从 channel 写到 buffer b、通过 buffer 的 put()方法写
 *      2、调用 flip()方法将 buffer 从写模式切换到读模式
 *      3、从 buffer 中读取数据
 *      4、读取玩数据需要清空缓存，调用 clear()方法清空整个缓冲区或者 compact()方法清除已经读过的数据，未读数据都被移到缓冲区的起始处
 * @author yingjun
 *
 */
public class BufferTest {

    public static void main(String[] args) {
        int j = 0;
        for (int i = 0; i < 10; i++) {
            j = (j++);
        }
        System.out.println(j);
    }

}
