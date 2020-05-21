package mntm.top.offer;

import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.ecs.model.v20140526.*;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: perf
 * @description:
 * @author: yingjun
 * @create: 2020-05-08 10:32
 **/
@Slf4j
public class Demo {

    public static void main(String[] args) throws IOException {

    }


    private void testNio() throws IOException {


        RandomAccessFile accessFile = new RandomAccessFile("/data/nio.txt", "rw");
        FileChannel channel = accessFile.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        int byteRead = channel.read(byteBuffer);

        while(byteRead != -1){
            System.out.println("Read " + byteRead);
            byteBuffer.flip();

            while (byteBuffer.hasRemaining()){
                System.out.print((char) byteBuffer.get());
            }

            byteBuffer.clear();
            byteRead = channel.read(byteBuffer);
        }

        accessFile.close();
    }

}
