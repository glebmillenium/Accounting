package com.example.glebmillenium.mobile_client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
/**
* Created by glebmillenium on 30.11.17.
*/

public class ConnectWithRemoteServer extends Thread {

    /**
     * run Метод запускающий реализацию сокет соединения по протоколу TCP в
     * параллельном треде
     *
     * @author glebmillenium
     */

    private int serverPort;
    private String address;
    private Socket socket;
    /**
     * 0 - getGoods
     * 1 - addGood
     * 2 - getStorage
     * 2 - addStorage
     */
    private DataInputStream in;
    private DataOutputStream out;

    public ConnectWithRemoteServer() throws IOException
    {
        this.serverPort = 7000;
        this.address = "127.0.0.1";
        socket = new Socket(InetAddress.getByName(address), serverPort);
    }

    public ConnectWithRemoteServer(String ip, int port) throws IOException
    {
        this.serverPort = port;
        this.address = ip;
        socket = new Socket(InetAddress.getByName(address), serverPort);
        System.out.println("Создал сокет");
    }

    public String run(int typeMessage)
    {
        String result = "";
        try
        {
            /**
             * Берем входной и выходной потоки сокета, теперь можем получать и
             * отсылать данные клиенту.
             *
             * @param InputStream - входной поток сокета.
             * @param OutputStream - выходной поток сокета.
             */
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            /**
             * Конвертация потоков в другой тип, чтоб легче обрабатывать
             * текстовые сообщения.
             *
             * @param DataInputStream входной поток данных
             * @param DataOutputStream выходной поток данных
             */
            in = new DataInputStream(sin);
            out = new DataOutputStream(sout);

                /* 0 - getGoods
                * 1 - addGood
                * 2 - getStorage
                * 2 - addStorage*/
            switch (typeMessage)
            {
                case 0:
                    result = sendMessage("--getGoods", in, out);
                    break;
                case 1:
                    sendMessage("--addGood", in, out);
                    break;
            }

            sendMessage("--", in, out);
        } catch (Exception x)
        {
            System.out.println("Не получилось присоединиться. " + x.getMessage());
        }
        return result;
    }



    public static String sendMessage(String text, DataInputStream in,
                                     DataOutputStream out) throws IOException, InterruptedException
    {

        out.write((text + '\0').getBytes("UTF-8"));
        out.flush();
        byte[] b = new byte[1024];
        in.read(b);
        String answer = new String(b, "UTF-8");
        return CppToJavaString(answer);
    }

    /**
     * sendMessage - отправка сообщения серверу
     *
     * @param text
     * @param in
     * @param out
     * @param sizeByte
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public static String sendMessage(String text, DataInputStream in,
                                     DataOutputStream out, int sizeByte) throws IOException, InterruptedException
    {

        out.write((text + '\0').getBytes("UTF-8"));
        out.flush();
        byte[] b = new byte[sizeByte];
        in.read(b);
        String answer = new String(b, "UTF-8");
        return answer;
    }

    private static String CppToJavaString(String strcpp)
    {
        String result = "";
        for (int i = 0; i < strcpp.length(); i++)
        {
            if (strcpp.charAt(i) == '\0')
            {
                break;
            }
            result += strcpp.charAt(i);
        }
        return result;
    }

}
