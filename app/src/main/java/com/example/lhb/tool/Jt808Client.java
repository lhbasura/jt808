package com.example.lhb.tool;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.lhb.util.BitOperator;
import com.example.lhb.util.JT808ProtocolUtils;
import com.example.lhb.vo.req.TerminalRegisterMsg;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * Created by lhb on 2017/5/5.
 */

public class Jt808Client {
    private String ipaddr;
    private int port;
    ByteBuffer writebuffer;
    ByteBuffer readbuffer;
    SocketChannel channel;
    Handler handler=null;
    public Jt808Client(String ip, int port) {
        this.ipaddr = ip;
        this.port = port;
    }

    public  void setHandler(Handler handler)
    {
        this.handler=handler;
    }
    public void start() {
        try {
            channel = SocketChannel.open();
            channel.configureBlocking(false);

            SocketAddress socketAddress = new InetSocketAddress(ipaddr, port);

            if (!channel.connect(socketAddress)){
                //不断地轮询连接状态，直到完成连接
                while (!channel.finishConnect()){
                    //System.out.print(".");
                }
            }

            new Thread()
            {
                public void run()
                {
                    readbuffer = ByteBuffer.allocate(2048);
                    Log.i("read","go" );
                    while (true)
                    {

                        try {
                            int len=0;
                            if((len=channel.read(readbuffer))>0&&handler!=null)
                            {
                                readbuffer.flip();
                                byte[]rb=readbuffer.array();


                                BitOperator bitOperator=new BitOperator();
                                byte []bytes= bitOperator.splitBytes(rb,0,len-1);
                                Message message=new Message();
                                Bundle bundle=new Bundle();
                                bundle.putByteArray("data",bytes);
                                message.setData(bundle);
                                handler.sendMessage(message);
                            };

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendBytes(byte[] bytes) throws IOException {
        if(bytes==null)
            return;
         writebuffer = ByteBuffer.wrap(bytes);
        // 如果正在连接，则完成连接
        if (channel.isConnectionPending()) {
            channel.finishConnect();
        }
        channel.write(writebuffer);
    }



    public void registerTerminal(TerminalRegisterMsg terminalAuthenticationMsg)throws IOException
    {
        // 如果正在连接，则完成连接
        if (channel.isConnectionPending()) {
            channel.finishConnect();
        }
        byte[]sendbytes=terminalAuthenticationMsg.getAllBytes();
        for(int i=0;i<sendbytes.length;i++)
        Log.i("sendbytes",sendbytes[i]+"");
        sendBytes(sendbytes);
    }








    public void logoff() {

    }

    public void checkLocation() {

    }

    public void checkTerminalparams() {

    }
}
