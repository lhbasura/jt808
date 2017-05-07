package com.example.lhb.vo.req;

import com.example.lhb.common.MsgHeader;




/**
 * 终端鉴权消息

 *
 */
public class TerminalAuthenticationMsg extends MsgHeader {
	private String authCode;

	public TerminalAuthenticationMsg() {
	}

	public TerminalAuthenticationMsg(MsgHeader packageData) {
		this();



	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getAuthCode() {
		return authCode;
	}


}
