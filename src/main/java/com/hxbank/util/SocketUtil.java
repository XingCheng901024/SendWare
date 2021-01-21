package com.hxbank.util;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

public class SocketUtil {

    private static final String FILE_START = "file:";
    private static final String MESSAGE_END = "@@@@";
    private static final String FILE_NAME_END = "$_?";


    /**
     * 示例：String str = "Xhj1001#1153285#201310230099112#10250001002171482#1#1#对私#1|6226310510420134|135067|500|J|#@@@@";
     *
     * @param
     * @return
     */
    public static String send() throws IOException {
        Socket socket = new Socket("localhost", 5500);
        OutputStream os = socket.getOutputStream();
        InputStream iss = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(iss, "utf-8"));
        File imageFile = new File("D:\\邮件附件\\CR20418_企业网银系统_应用系统工作量估算表（备份）.xls");
        InputStream is = new FileInputStream(imageFile);
        long fileLength = imageFile.length();
        System.out.println("图片长度：" + fileLength);
        /*发送第一部分的文本信息，对应text1*/
        int a = "xhj3003#10250000001602678#100000#1#".getBytes().length;
        os.write("xhj3003#10250000001602678#100000#1#".getBytes());
        /*发送图片开始的标识，对应image_start*/
        int c = FILE_START.getBytes().length;
        os.write(FILE_START.getBytes());
        // 发送图片文件名称，对应image_file_name
        int d = imageFile.getName().getBytes().length;
        os.write(imageFile.getName().getBytes());
        //  发送图片文件名称结束的标识，对应image_file_name_end
        int f = FILE_NAME_END.getBytes().length;
        os.write(FILE_NAME_END.getBytes());
        //  发送图片文件的长度，对应image_file_length
        byte[] bs = LongToBytes(a + c + d + f);
        os.write(bs);
        //  发送图片文件，对应image
        int length;
        byte[] b = new byte[1024];
        while ((length = is.read(b)) > 0) {
            os.write(b, 0, length);
        }

        /*发送一条完整信息结束的标识，对应message_end*/
        os.write(MESSAGE_END.getBytes());
        os.flush();
        socket.shutdownOutput();
        String info = null;
        while ((info = br.readLine()) != null) {
            System.out.println("receive：" + info);
        }
        socket.close();
        return null;
    }

    /**
     * 将长整型转换为byte数组
     *
     * @param n
     * @return
     */
    public static byte[] longToBytes(long n) {
        byte[] b = new byte[8];
        b[7] = (byte) (n & 0xff);
        b[6] = (byte) (n >> 8 & 0xff);
        b[5] = (byte) (n >> 16 & 0xff);
        b[4] = (byte) (n >> 24 & 0xff);
        b[3] = (byte) (n >> 32 & 0xff);
        b[2] = (byte) (n >> 40 & 0xff);
        b[1] = (byte) (n >> 48 & 0xff);
        b[0] = (byte) (n >> 56 & 0xff);
        return b;
    }

    private static CountDownLatch cld = new CountDownLatch(1);

    public static void main(String[] args) {
        String str = "Xhj1001#1153285#201310230099112#10250001002171482#1#1#对私#1|6226310510420134|135067|500|J|#@@@@";

        for (int i = 0; i < 1; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cld.await();//将线程阻塞在此，等待所有线程都调用完start()方法，一起执行
                        String send = send();
                        System.out.println(Thread.currentThread().getName() + ":" + System.currentTimeMillis());
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }

                }
            });
            t.start();
            cld.countDown();
        }
    }

    public static byte[] LongToBytes(long values) {
        byte[] buffer = new byte[8];
        for (int i = 0; i < 8; i++) {
            int offset = 64 - (i + 1) * 8;
            buffer[i] = (byte) ((values >> offset) & 0xff);
        }
        return buffer;
    }

    public static long BytesToLong(byte[] buffer) {
        long values = 0;
        for (int i = 0; i < 8; i++) {
            values <<= 8;
            values |= (buffer[i] & 0xff);
        }
        return values;
    }
}
