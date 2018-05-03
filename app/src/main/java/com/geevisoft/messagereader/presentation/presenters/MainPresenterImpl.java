package com.geevisoft.messagereader.presentation.presenters;

import com.geevisoft.messagereader.domain.entities.Sms;
import com.geevisoft.messagereader.infrastructure.providers.SmsProvider;

import java.util.List;

public class MainPresenterImpl implements MainPresenter {

	private final SmsProvider smsProvider;

	public MainPresenterImpl(SmsProvider smsProvider) {
		this.smsProvider = smsProvider;
	}

	public List<Sms> getReceivedSms(){
		return smsProvider.getReceivedSms();
	}
}
