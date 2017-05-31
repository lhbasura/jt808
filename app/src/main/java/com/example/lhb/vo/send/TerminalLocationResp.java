package com.example.lhb.vo.send;

import com.example.lhb.common.MsgHeader;
import com.example.lhb.util.BitOperator;

/**
 * Created by lhb on 2017/5/31.
 */

public class TerminalLocationResp extends TerminalLocationReport{
    public int getRespflowId() {
        return respflowId;
    }

    public void setRespflowId(int respflowId) {
        this.respflowId = respflowId;
    }

    int respflowId;
    public TerminalLocationResp(MsgHeader header,int respflowId, long sign, long state,
                                long lat, long lon, int height,
                                int speed, int direction, String time,
                                int attachId, int attachLength,
                                long attachMsg)
    {
        super(header,sign,state,lat,lon,height,speed,direction,time,attachId,attachLength,attachMsg);
        setRespflowId(respflowId);

    }
    public byte[]getAllBytes()
    {
        BitOperator bitOperator=new BitOperator();
        byte []respflow_bytes=bitOperator.numToByteArray(respflowId,2);
        byte []header_bytes=getHearder().getHeaderbytes();
        byte[]body_bytes=bitOperator.concatAll(respflow_bytes,super.getMessageBodybytes());
        byte[]all_bytes=bitOperator.concatAll(header_bytes,body_bytes);
        return all_bytes;
    }
}
