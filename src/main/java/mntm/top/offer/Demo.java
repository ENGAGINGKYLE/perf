package mntm.top.offer;

import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.ecs.model.v20140526.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

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

    @Test
    public void t() throws IOException {

        String url = "http://www.trxs.cc/d/file/tongren/20200211/48ed22e953bd522a9b6df662c1101707.txt?nsukey=opVe0DJ7W%2F9cMZ0LKskiL%2F2drh1SRq8T4iOQE5JTjacCqgR%2BtrqTGYdYSePPAPRwFikX4DvYY7cVfGSzdbYTzBnVJHs04T7Up97KDoIO%2B9%2BXyMlZZlIkd8FInnQZAdYOpuebZ20zBtupo3xTaRf8VNMWZ6uuRhRC751Ug3wCRLLcjSEUXLJXnCWSXT3xX2YBc9PCLFTgcLJu4IeexVGmag%3D%3D";

        Document doc = Jsoup.connect(url).get();

        String content = doc.body().text();

        File file = new File("/data/46.txt");
        PrintWriter printWriter = new PrintWriter(file,"UTF8");
        printWriter.println(content);
        printWriter.close();
    }

//
//    private String connUrl(String url) {
//        log.info("execute , url  : {}", url);
//        if (StringUtils.isEmpty(url)) {
//            return null;
//        }
//        String contentS = null;
//
//        try {
//            //设置随机代理
//            InputStream inputStream = httpClientUtil.httpGet(url);
//            contentS = IOUtils.toString(inputStream);
//        } catch (Exception e) {
//            log.error("execute converter fail url : {} ", url, e);
//            return null;
//        }
//
//        return contentS;
//
//    }

}
