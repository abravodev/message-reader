package com.geevisoft.messagereader.domain.entities;

import com.geevisoft.messagereader.domain.utils.FormatHelper;

import java.util.Date;

public class Sms {

	private final String sender;
	private final String text;
	private final Date sendDate;
	private final boolean read;

	public Sms(String sender, String text, Date sendDate, boolean read) {
		this.sender = sender;
		this.text = text;
		this.sendDate = sendDate;
		this.read = read;
	}

	public String getSender() {
		return sender;
	}

	public String getText() {
		return text;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public String getSendDateFormatted(){
		return FormatHelper.datetimeFormatted(sendDate);
	}

	public boolean isRead() {
		return read;
	}
}
