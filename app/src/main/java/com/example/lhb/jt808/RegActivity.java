package com.example.lhb.jt808;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lhb.common.MsgHeader;
import com.example.lhb.common.TPMSConsts;
import com.example.lhb.tool.Jt808Client;
import com.example.lhb.util.BitOperator;
import com.example.lhb.util.HexStringUtils;
import com.example.lhb.vo.req.TerminalAuthentication;
import com.example.lhb.vo.req.TerminalCommonResp;
import com.example.lhb.vo.req.TerminalHeartBeat;
import com.example.lhb.vo.req.TerminalLogOut;
import com.example.lhb.vo.req.TerminalRegisterMsg;

import java.io.IOException;

public class RegActivity extends AppCompatActivity {


    Button btn_reg;
    Button btn_heart;
    Button btn_auth;
    Button btn_resp;
    Button btn_logout;
    Button btn_paramresp;
    EditText edit_phone;
    EditText edit_flow;
    EditText edit_captal_id;
    EditText edit_city_id;
    EditText edit_fc_id;
    EditText edit_ter_model;
    EditText edit_ter_id;
    EditText edit_color_id;
    EditText edit_car_num;


    private String rebackAuthMsg="313131313131";//可能会出项空指针异常

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        edit_phone = (EditText) findViewById(R.id.edit_phone_id);
        edit_flow = (EditText) findViewById(R.id.edit_flow_id);
        edit_captal_id = (EditText) findViewById(R.id.edit_cp_id);
        edit_city_id = (EditText) findViewById(R.id.edit_city_id);
        edit_fc_id = (EditText) findViewById(R.id.edit_fc_id);
        edit_ter_model = (EditText) findViewById(R.id.edit_ter_model);
        edit_ter_id = (EditText) findViewById(R.id.edit_ter_id);
        edit_color_id = (EditText) findViewById(R.id.edit_color_id);
        edit_car_num = (EditText) findViewById(R.id.edit_car_num);
        btn_reg = (Button) findViewById(R.id.btn_reg);
        btn_heart=(Button)findViewById(R.id.btn_heart);
        btn_auth=(Button)findViewById(R.id.btn_auth);
        btn_resp=(Button)findViewById(R.id.btn_resp);
        btn_logout=(Button)findViewById(R.id.btn_logout);
        btn_paramresp=(Button)findViewById(R.id.btn_paramresp);

        TelephonyManager tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        Intent intent = new Intent(RegActivity.this, Jt808Service.class);
        byte[]b=null;
        intent.putExtra("data",b);
        startService(intent);


        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(RegActivity.this,"dddd",Toast.LENGTH_SHORT).show();
                Jt808Service.client.setHandler(handler);
                int provinceid = Integer.parseInt(edit_captal_id.getText().toString());
                int cityid = Integer.parseInt(edit_city_id.getText().toString());

                String phone = edit_phone.getText().toString();
                int flow = Integer.parseInt(edit_flow.getText().toString());
                String fcid = edit_fc_id.getText().toString();
                String tertype = edit_ter_model.getText().toString();
                String terid = edit_ter_id.getText().toString();
                int color = Integer.parseInt(edit_color_id.getText().toString());
                String carnum = edit_car_num.getText().toString();
                final TerminalRegisterMsg terminalRegisterMsg = new TerminalRegisterMsg(0, false, phone, flow, 0, 0,
                        provinceid, cityid, fcid, tertype, terid, color, carnum);
               new Thread()
               {
                   public void run()
                   {
                       try {
                           Jt808Service.client.registerTerminal(terminalRegisterMsg);
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   }
               }.start();
            }
        });

        btn_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Jt808Service.client.setHandler(handler);
                String phone = edit_phone.getText().toString();
                int flow = Integer.parseInt(edit_flow.getText().toString());

                final TerminalHeartBeat terminalHeartBeat=new TerminalHeartBeat(0,0,false,phone,flow,0,0);
                new Thread()
                {
                    public void run()
                    {
                        try{
                            Jt808Service.client.heartbeatTerminal(terminalHeartBeat);
                        }catch (IOException e){

                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        btn_auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Jt808Service.client.setHandler(handler);
                String phone = edit_phone.getText().toString();
                int flow = Integer.parseInt(edit_flow.getText().toString());

                final TerminalAuthentication terminalAuthentication=new TerminalAuthentication(0,false,phone,flow,0,0,rebackAuthMsg);
                new Thread()
                {
                    public void run()
                    {
                        try
                        {
                            Jt808Service.client.authenticationTerminal(terminalAuthentication);
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        btn_resp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Jt808Service.client.setHandler(handler);
                String phone = edit_phone.getText().toString();
                int flow = Integer.parseInt(edit_flow.getText().toString());
                final TerminalCommonResp terminalCommonResp=new TerminalCommonResp(0,false,phone,flow,0,0,258,0);
                new Thread()
                {
                    public void run()
                    {
                        try{
                            Jt808Service.client.commonRespTerminal(terminalCommonResp);
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Jt808Service.client.setHandler(handler);
                String phone = edit_phone.getText().toString();
                int flow = Integer.parseInt(edit_flow.getText().toString());
                final TerminalLogOut terminalLogOut=new TerminalLogOut(0,0,false,phone,flow,0,0);

                new Thread()
                {
                    public void run()
                    {
                        try
                        {
                            Jt808Service.client.logOutTerminal(terminalLogOut);
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }

    Handler handler=new Handler()//处理返回消息
    {
        @Override
        public void handleMessage(Message msg) {

            Bundle data=msg.getData();
            byte[]b=data.getByteArray("data");
            String hexString="";
            String hex2Byte="";
            switch (msg.what)
            {

                case TPMSConsts.cmd_terminal_register_resp:// 终端注册应答
                     hexString= HexStringUtils.toHexString(b);//转十六进制字符串
                     hex2Byte=HexStringUtils.hexString2Bytes(hexString);
                    Log.e("tag",hexString+"");
                    Toast.makeText(RegActivity.this,"终端注册回应："+getAuthentication(hexString)+"",Toast.LENGTH_LONG).show();
                    rebackAuthMsg=getAuthentication(hexString);

                    break;
                default:// 终端通用应答

                     hexString= HexStringUtils.toHexString(b);
                    Toast.makeText(RegActivity.this,"终端回应："+hexString+"",Toast.LENGTH_LONG).show();
                    break;
            }

        }
    };


    public String getAuthentication(String str)
    {
        String hasStr=str;
        hasStr=hasStr.substring(0,hasStr.length()-4);//去掉后四位
        hasStr=hasStr.substring(32,hasStr.length());
        return(hasStr);
    }
}
