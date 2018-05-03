package com.geevisoft.messagereader.infrastructure.providers;

import com.geevisoft.messagereader.domain.entities.Sms;

import java.util.List;

public interface SmsProvider {

	List<Sms> getReceivedSms();

}
