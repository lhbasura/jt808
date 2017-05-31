package com.example.lhb.vo.receive;


import com.example.lhb.common.MsgFrame;
import com.example.lhb.common.MsgHeader;
import com.example.lhb.common.TPMSConsts;
import com.example.lhb.util.BitOperator;

/**
 * Created by Long on 2017/5/23.
 */

public class CmdTerminalParamMsg extends MsgFrame {
    //终端心跳发送间隔，单位为秒（s）
    public static final int heart_interval=0x0001;
    //TCP消息应答超时时间，单位为秒（s）
    public static final int tcpmessage_resp_overtime=0x0002;
    //TCP消息重传次数
    public static final int tcpmessage_retranst_time=0x0003;
    //UDP消息应答超时时间，单位为秒（s）
    public static final int udpmessage_resp_overtime=0x0004;
    //UDP消息重传次数
    public static final int udpmessage_retranst_time=0x0005;
    //SMS消息应答超时时间，单位为秒（s）
    public static final int smsmessage_resp_overtime=0x0006;
    //SMS消息重传次数
    public static final int smsmessage_retranst_time=0x0007;

    /*0x0008-0x000f 保留*/

    /*主服务器 APN，无线通信拨号访问点。若网络制式为 CDMA，则该处为
     *PPP 拨号号码
     */
    public static final int main_server_apn=0x0010;
    //主服务器无线通信拨号用户名
    public static final int main_server_dial_name=0x0011;
    //主服务器无线通信拨号密码
    public static final int main_server_dial_pwd=0x0012;
    //主服务器地址,IP 或域名
    public static final int main_server_address=0x0013;
    //备份服务器 APN，无线通信拨号访问点
    public static final int backup_server_apn=0x0014;
    //备份服务器无线通信拨号用户名
    public static final int backup_server_dial_name=0x0015;
    //备份服务器无线通信拨号密码
    public static final int backup_server_dial_pwd=0x0016;
    //备份服务器地址,IP 或域名
    public static final int backup_server_address=0x0017;
    //服务器 TCP 端口
    public static final int server_tcp_port=0x0018;
    //服务器 UDP 端口
    public static final int server_udp_port=0x0019;
    //道路运输证 IC 卡认证主服务器 IP 地址或域名
    public static final int roadtranstlicense_ic_main_server_address=0x001A;
    //道路运输证 IC 卡认证主服务器 TCP 端口
    public static final int roadtranstlicense_ic_main_server_tcp_port=0x001B;
    //道路运输证 IC 卡认证主服务器 UDP 端口
    public static final int roadtranstlicense_ic_main_server_udp_port=0x001C;
    //道路运输证 IC 卡认证备份服务器 IP 地址或域名，端口同主服务器
    public static final int roadtranstlicense_ic_backup_server_address=0x001D;
    /* 0x001E-0x001F  保留*/

    /*
     * 位置汇报策略，
     * 0：定时汇报；
     * 1：定距汇报；
     * 2：定时和定距汇报
     */
    public static final int location_report_strategy=0x0020;
    /*
     *位置汇报方案，
     * 0：根据 ACC 状态；
     * 1：根据登录状态和 ACC 状态，先判断登录状态，若登录再根据 ACC 状态
     */
    public static final int location_report_plan=0x0021;
    //驾驶员未登录汇报时间间隔，单位为秒（s），>0
    public static final int driver_unlog_report_interval=0x0022;
    /* 0x0023-0x0026  保留*/
    //休眠时汇报时间间隔，单位为秒（s），>0
    public static final int dormancy_report_time_interval=0x0027;
    //紧急报警时汇报时间间隔，单位为秒（s），>0
    public static final int emergency_alarm_report_time_interval=0x0028;
    //缺省时间汇报间隔，单位为秒（s），>0
    public static final int default_time_report_interval=0x0029;

    /* 0x002A-0x002B  保留*/

    //缺省距离汇报间隔，单位为米（m），>0
    public static final int default_distance_report_interval=0x002C;
    //驾驶员未登录汇报距离间隔，单位为米（m），>0
    public static final int driver_unlog_report_distance_interval=0x002D;
    //休眠时汇报距离间隔，单位为米（m），>0
    public static final int dormancy_report_distance_interval=0x002E;
    //紧急报警时汇报距离间隔，单位为米（m），>0
    public static final int emergency_alarm_report_distance_interval=0x002F;
    //拐点补传角度，<180
    public static final int inflexion_replenish_angle=0x0030;
    //电子围栏半径（非法位移阈值），单位为米
    public static final int electronic_fence_radiu=0x0031;

    /* 0x0032-0x003F  保留*/

    //监控平台电话号码
    public static final int monitor_platform_tel=0x0040;
    //复位电话号码，可采用此电话号码拨打终端电话让终端复位
    public static final int reset_tel=0x0041;
    //恢复出厂设置电话号码，可采用此电话号码拨打终端电话让终端恢复出厂设置
    public static final int recovery_factory_setting_tel=0x0042;
    //监控平台 SMS 电话号码
    public static final int monitor_platform_sms_tel=0x0043;
    //接收终端 SMS 文本报警号码
    public static final int receving_terminal_sms_alarm_tel=0x0044;
    /*
     * 终端电话接听策略，
     * 0：自动接听；
     * 1：ACC ON 时自动接听，OFF 时手动接听
     */
    public static final int terminal_answer_strategy=0x0045;
    //每次最长通话时间，单位为秒（s），0 为不允许通话，0xFFFFFFFF 为不限制
    public static final int evey_longest_answer_time=0x0046;
    //当月最长通话时间，单位为秒（s），0 为不允许通话，0xFFFFFFFF 为不限制
    public static final int this_month_longest_answer_time=0x0047;
    //监听电话号码
    public static final int monitor_tel=0x0048;
    //监管平台特权短信号码
    public static final int supervise_platform_prerogative_message_number=0x0049;

    /* 0x004A-0x004F  保留*/

    //报警屏蔽字，与位置信息汇报消息中的报警标志相对应，相应位为1则相应报警被屏蔽
    public static final int alarm_mask_word=0x0050;
    //报警发送文本 SMS 开关，与位置信息汇报消息中的报警标志相对应，相应位为 1 则相应报警时发送文本SMS
    public static final int alarm_send_text_sms_switch=0x0051;
    //报警拍摄开关，与位置信息汇报消息中的报警标志相对应，相应位为1则相应报警时摄像头拍摄
    public static final int alarm_video_switch=0x0052;
    //报警拍摄存储标志，与位置信息汇报消息中的报警标志相对应，相应位为 1 则对相应报警时拍的照片进行存储，否则实时上
    public static final int alarm_video_storage=0x0053;
    //关键标志，与位置信息汇报消息中的报警标志相对应，相应位为 1 则对相应报警为关键报警
    public static final int key_sign=0x0054;
    //最高速度，单位为公里每小时（km/h）
    public static final int top_speed = 0x0055;

    //超速持续时间，单位为秒（s）
    public static final int time_overspeed_last = 0x0056;

    //连续驾驶时间门限，单位为秒（s）
    public static final int drive_time_threshold_last = 0x0057;

    //当天累计驾驶时间门限，单位为秒（s）
    public static final int drive_time_threshold_day = 0x0058;

    //最小休息时间，单位为秒（s）
    public static final int relax_time_min = 0x0059;

    //最长停车时间，单位为秒（s）
    public static final int car_stop_time_max = 0x005A;

    //超速报警预警差值，单位为 1/10Km/h
    public static final int overspeed_warning = 0x005B;

    //疲劳驾驶预警差值，单位为秒（s），>0
    public static final int overtired_warning = 0x005C;

    /***
     *碰撞报警参数设置：<br>
     *b7-b0： 碰撞时间，单位 4ms；<br>
     *b15-b8：碰撞加速度，单位 0.1g，设置范围在：0-79 之间，默认为10。<br>
     */
    public static final int collision_warning_param = 0x005D;

    /*侧翻报警参数设置：
     侧翻角度，单位 1 度，默认为 30 度。*/
    public static final int rollover_param = 0x005E;

    /*0x005F-0x0063保留*/

    //定时拍照控制，见 表 13
    public static final int time__photo = 0x0064;

    //定距拍照控制，见 表 14
    public static final int distance_photo = 0x0065;

    /*0x0066-0x006F保留*/

    //图像/视频质量，1-10，1 最好
    public static final int image_or_video_quality = 0x0070;

    //亮度，0-255
    public static final int brigtness = 0x0071;

    //对比度，0-127
    public static final int contrat_ratio = 0x0072;

    //饱和度，0-12
    public static final int saturability = 0x0073;

    //色度，0-255
    public static final int chroma = 0x0074;

     /*0x0075-0x007F*/

    //车辆里程表读数，1/10km
    public static final int car_odometer = 0x0080;

    //车辆所在的省域 ID
    public static final int car_province_id = 0x0081;

    //车辆所在的市域 ID
    public static final int car_city_id = 0x0082;

    //公安交通管理部门颁发的机动车号牌
    public static final int car_number = 0x0083;

    //车牌颜色，按照 JT/T415-2006 的 5.4.12
    public static final int car_plate_color = 0x0084;

    /***
     * GNSS 定位模式，定义如下：
     * bit0，0：禁用 GPS 定位， 1：启用 GPS 定位；<br>
     * bit1，0：禁用北斗定位， 1：启用北斗定位；<br>
     * bit2，0：禁用 GLONASS 定位， 1：启用 GLONASS 定位；<br>
     * bit3，0：禁用 Galileo 定位， 1：启用 Galileo 定位。<br>
     */
    public static final int gnns_location = 0x0090;

    /***
     *GNSS 波特率，定义如下： <br>
     *0x00：4800；0x01：9600； <br>
     *0x02：19200；0x03：38400； <br>
     *0x04：57600；0x05：115200。 <br>
     */
    public static final int gnns_baud_rate = 0x0091;

    /***GNSS 模块详细定位数据输出频率，定义如下：<br>
     *0x00：500ms；0x01：1000ms（默认值）；<br>
     *0x02：2000ms；0x03：3000ms；<br>
     *0x04：4000ms。<br>
     */
    public static final int gnns_module_location_output_rate = 0x0092;

    //GNSS 模块详细定位数据采集频率，单位为秒，默认为 1。
    public static final int gnns_module_location_gather_rate = 0x0093;

    /***GNSS 模块详细定位数据上传方式：<br>
     *0x00，本地存储，不上传（默认值）；<br>
     *0x01，按时间间隔上传；<br>
     *0x02，按距离间隔上传；<br>
     *0x0B，按累计时间上传，达到传输时间后自动停止上传；<br>
     *0x0C，按累计距离上传，达到距离后自动停止上传；<br>
     *0x0D，按累计条数上传，达到上传条数后自动停止上传。<br>
     */
    public static final int gnns_module_location_push_way = 0x0094;

    /***GNSS 模块详细定位数据上传设置：<br>
     *上传方式为 0x01 时，单位为秒；<br>
     *上传方式为 0x02 时，单位为米；<br>
     *上传方式为 0x0B 时，单位为秒；<br>
     *上传方式为 0x0C 时，单位为米；<br>
     *上传方式为 0x0D 时，单位为条。<br>
     */
    public static final int gnns_module_location_push_setting = 0x0095;

    // CAN 总线通道 1 采集时间间隔(ms)，0 表示不采集
    public static final int can_buschanel1_gather_interval =0x0100;

    //CAN 总线通道 1 上传时间间隔(s)，0 表示不上传
    public static final int can_buschanel1_push_interval = 0x0101;

    //CAN 总线通道 2 采集时间间隔(ms)，0 表示不采
    public static final int can_buschanel2_gather_interval =0x0102;

    //CAN 总线通道 2 上传时间间隔(s)，0 表示不上传
    public static final int can_buschanel2_push_interval = 0x0103;

    /***CAN 总线 ID 单独采集设置：<br>
     *bit63-bit32 表示此 ID 采集时间间隔(ms)，0 表示不采集；<br>
     *bit31 表示 CAN 通道号，0：CAN1，1：CAN2；<br>
     *bit30 表示帧类型，0：标准帧，1：扩展帧；<br>
     *bit29 表示数据采集方式，0：原始数据，1：采集区间的计算值；<br>
     *bit28-bit0 表示 CAN 总线 ID<br>
     */
    public static final int can_id_gather_alone_setting = 0x0110;

    // 0x0111-0x01FF 用于其他CAN总线ID单独采集设置,0x0111为第一个*/
    public static final int othercan_id_gather_alone_setting = 0x0111;

    /* 0xF000-0xFFFF 用户自定义*/



    public CmdTerminalParamMsg(MsgHeader header){this.msgHeader=header;}
    /*
    // 消息ID
    protected int msgId;

    /////// ========消息体属性
    // byte[2-3]
    protected byte msgBodyPropsField;
    // 消息体长度
    protected int msgBodyLength;
    // 数据加密方式
    protected int encryptionType;
    // 是否分包,true==>有消息包封装项
    protected boolean hasSubPackage;
    // 保留位[14-15]
    protected int reservedBit;
    /////// ========消息体属性

    // 终端手机号
    protected String terminalPhone;
    // 流水号
    protected int flowId;

    //////// =====消息包封装项
    // byte[12-15]
    protected int packageInfoField;
    // 消息包总数(word(16))
    protected long totalSubPackage;
    // 包序号(word(16))这次发送的这个消息包是分包中的第几个消息包, 从 1 开始
    protected long subPackageSeq;
    //////// =====消息包封装项
    */

    //参数总数(BYTE)
    private int paramCount;
    //参数ID(DWORD)
    private long[] paramId;
    //参数长度(BYTE)
    private int[] paramLength;
    //若为多值参数，则消息中使用多个相同ID的参数项，如调度中心电话号码
    //DWORD类型参数值
    private long[] longParamValue;
    //STRING类型参数值
    private String[] stringParamValue;

    public CmdTerminalParamMsg(byte[] bytes){
        super(bytes);
        //Log.i("msgheader",this.msgHeader.toString());
        BitOperator bitOperator=new BitOperator();
        //消息体
        byte[]body;
        //参数总数
        byte[] byteParamCount;
        //当前分割位置
        int tag;
        if(this.msgHeader.isHasSubPackage()) //获得消息体
        {
            body=bitOperator.splitBytes(bytes,17,bytes.length-3);
            byteParamCount=bitOperator.splitBytes(bytes,17,17);
            this.setParamCount(bitOperator.byteToInteger(byteParamCount));
            tag=18;
        }
        else
        {
            body=bitOperator.splitBytes(bytes,13,bytes.length-3);
            byteParamCount=bitOperator.splitBytes(bytes,13,13);
            this.setParamCount(bitOperator.byteToInteger(byteParamCount));
            tag=14;
        }
        //如果是设置终端参数，则进行以下解析
        if(this.msgHeader.getMsgId()== TPMSConsts.cmd_terminal_param_settings){
            //解析参数总数
            this.setParamCount(bitOperator.byteToInteger(byteParamCount));

            //解析参数项列表
            for(int i=0;i<this.paramCount;i++){
                this.setParamId(bitOperator.bytes2Long(bitOperator.splitBytes(bytes,tag,tag+1)),i);
                this.setParamLength(bitOperator.byteToInteger(bitOperator.splitBytes(bytes,tag+1,tag+2)),i);
                tag+=3;
                if(this.paramLength[i]==4)
                {
                    this.setLongParamValue(bitOperator.bytes2Long(bitOperator.splitBytes(bytes,tag,tag+4)),i);
                    tag+=5;
                }else
                {
                    byte[] strBytes=bitOperator.splitBytes(bytes,tag,tag+this.paramLength[i]);
                    String str=new String(strBytes,TPMSConsts.string_charset);
                    this.setStringParamValue(str,i);
                    tag+=(this.paramLength[i]+1);
                }
            }
        }
        //如果是查询终端参数，则进行以下解析
        else if(this.msgHeader.getMsgId() == TPMSConsts.cmd_terminal_param_query){
            //消息体为空
        }
        //如果是查询指定终端参数，则进行以下解析
        else if(this.msgHeader.getMsgId() == TPMSConsts.cmd_terminal_param_settings){
            //解析参数总数
            setParamCount(bitOperator.byteToInteger(byteParamCount));

            //解析参数项ID列表
            for(int i=0;i<this.paramCount;i++){
                this.setParamId(bitOperator.bytes2Long(bitOperator.splitBytes(bytes, tag, tag + 1)), i);
                tag++;

            }
        }





    }

    //参数总数的getter、setter
    public int getParamCount() {
        return this.paramCount;
    }

    public void setParamCount(int paramCount) {
        this.paramCount=paramCount;
    }

    //参数ID的getter、setter
    public long[] getParamId() {
        return paramId;
    }

    public void setParamId(long paramId, int i) {
        this.paramId[i]=paramId;
    }

    public void setParamId(long[] paramId) {
        this.paramId = paramId;
    }

    //参数长度的getter、setter
    public int[] getParamLength() {
        return paramLength;
    }

    public void setParamLength(int paramLength,int i) {
        this.paramLength[i]=paramLength;
    }

    public void setParamLength(int[] paramLength) {
        this.paramLength = paramLength;
    }

    //long类型的参数值的getter、setter
    public long[] getLongParamValue(){
        return longParamValue;
    }

    public void setLongParamValue(long longParamValue,int i){
        this.longParamValue[i]=longParamValue;
    }

    public void setLongParamValue(long[] longParamValue) {
        this.longParamValue = longParamValue;
    }

    //string类型的getter、setter
    public String[] getStringParamValue() {
        return stringParamValue;
    }

    public void setStringParamValue(String[] stringParamValue) {
        this.stringParamValue = stringParamValue;
    }

    public void setStringParamValue(String stringParamValue,int i) {
        this.stringParamValue[i] = stringParamValue;
    }
}
