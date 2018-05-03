package com.geevisoft.messagereader.presentation.presenters;

import com.geevisoft.messagereader.domain.entities.Sms;

import java.util.List;

public interface MainPresenter {

	List<Sms> getReceivedSms();
}
