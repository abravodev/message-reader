package com.geevisoft.messagereader.infrastructure.providers;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.Telephony;

import com.geevisoft.messagereader.domain.entities.Sms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SmsProviderImpl implements SmsProvider {

	private Context context;

	public SmsProviderImpl(Context context) {
		this.context = context;
	}

	@Override
	public List<Sms> getReceivedSms() {
		List<Sms> smsList = new ArrayList<>();
		ContentResolver contentResolver = context.getContentResolver();
		String[] smsFields = new String[] {
				Telephony.Sms.Inbox.ADDRESS,
				Telephony.Sms.Inbox.BODY,
				Telephony.Sms.Inbox.DATE,
				Telephony.Sms.Inbox.READ
		};
		Cursor cursor = contentResolver.query(Telephony.Sms.Inbox.CONTENT_URI,
				smsFields, null, null, Telephony.Sms.Inbox.DEFAULT_SORT_ORDER);

		while(cursor.moveToNext()){
			String smsSender = cursor.getString(0);
			String smsBody = cursor.getString(1);
			Date smsDate = new Date(cursor.getLong(2));
			boolean smsRead = cursor.getInt(3) == 1;
			Sms sms = new Sms(smsSender, smsBody, smsDate, smsRead);
			smsList.add(sms);
		}

		return smsList;
	}
}
