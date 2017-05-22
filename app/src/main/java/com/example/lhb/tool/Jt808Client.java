package com.example.lhb.tool;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.lhb.util.BitOperator;
import com.example.lhb.util.JT808ProtocolUtils;
import com.example.lhb.vo.req.TerminalAuthentication;
import com.example.lhb.vo.req.TerminalCommonResp;
import com.example.lhb.vo.req.TerminalHeartBeat;
import com.example.lhb.vo.req.TerminalLogOut;
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
    public void start() {//连接server
        try {
            channel = SocketChannel.open();//连接到TCP网络套接字的通道
            channel.configureBlocking(false);//非阻塞IO

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
                    readbuffer = ByteBuffer.allocate(2048);//JVM级的内存分配
                    Log.i("read","go" );
                    while (true)
                    {

                        try {
                            int len=0;
                           // Log.e("tag","dbbbbbbbbbbbb");
                            if((len=channel.read(readbuffer))>0&&handler!=null)
                            {
                                readbuffer.flip();
                                byte[]rb=readbuffer.array();
                          //      Log.e("tagc","dccccccccccccc");

                                BitOperator bitOperator=new BitOperator();
                                byte []bytes= bitOperator.splitBytes(rb,0,len-1);
                                Message message=new Message();
                                Bundle bundle=new Bundle();
                                bundle.putByteArray("data",bytes);
                                message.setData(bundle);
                                handler.sendMessage(message);
                                Log.e("reading",message+"");
                            }


                        } catch (IOException e) {
                            Log.e("tag","dgggggggggggggggg");
                            e.printStackTrace();
                        }
                    }

                }
            }.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendBytes(byte[] bytes) throws IOException {//发送数据
        if(bytes==null)
            return;
         writebuffer = ByteBuffer.wrap(bytes);
        // 如果正在连接，则完成连接
        if (channel.isConnectionPending()) {
            channel.finishConnect();
        }
        try{
            channel.write(writebuffer);
        }catch (IOException e){
            Log.e("cc","write error");
            e.printStackTrace();
        }
    }



    public void registerTerminal(TerminalRegisterMsg terminalAuthenticationMsg)throws IOException//注册
    {
        // 如果正在连接，则完成连接
        if (channel.isConnectionPending()) {
            channel.finishConnect();
        }
        byte[]sendbytes=terminalAuthenticationMsg.getAllBytes();
        sendBytes(sendbytes);//发送消息
    }
    public void heartbeatTerminal(TerminalHeartBeat terminalHeartBeat)throws IOException
    {
        if(channel.isConnectionPending()){
            channel.finishConnect();
        }
        Log.e("tag","dddddddddds");
        byte[]sendbyte=terminalHeartBeat.getAllBytes();

        sendBytes(sendbyte);
    }
    public void authenticationTerminal(TerminalAuthentication terminalAuthentication)throws IOException
    {
        if(channel.isConnectionPending()){channel.finishConnect();}
        byte[] sendbyte=terminalAuthentication.getAllBytes();
        sendBytes(sendbyte);
    }
    public void commonRespTerminal(TerminalCommonResp terminalCommonResp)throws IOException
    {
        if(channel.isConnectionPending()){channel.finishConnect();}
        byte[] sendbyte=terminalCommonResp.getAllBytes();
        sendBytes(sendbyte);
    }
    public void logOutTerminal(TerminalLogOut terminalLogOut) throws IOException {
        if(channel.isConnectionPending()){channel.finishConnect();}
        byte[] sendbyte=terminalLogOut.getAllBytes();
        sendBytes(sendbyte);
    }








    public void logoff() {

    }

    public void checkLocation() {

    }

    public void checkTerminalparams() {

    }
}
