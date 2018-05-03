package com.geevisoft.messagereader.presentation.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.geevisoft.messagereader.R;
import com.geevisoft.messagereader.domain.entities.Sms;
import com.geevisoft.messagereader.infrastructure.providers.SmsProviderImpl;
import com.geevisoft.messagereader.presentation.presenters.MainPresenter;
import com.geevisoft.messagereader.presentation.presenters.MainPresenterImpl;
import com.geevisoft.messagereader.presentation.utils.PermissionHelper;
import com.geevisoft.messagereader.presentation.views.adapters.SmsListItemAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

	private MainPresenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		presenter = new MainPresenterImpl(new SmsProviderImpl(this	));


		if(PermissionHelper.readSmsPermissionIsGranted(this)){
			setupSmsList();
		} else {
			PermissionHelper.checkReadSmsPermission(this);
		}

	}

	private void setupSmsList(){
		List<Sms> receivedSms = presenter.getReceivedSms();
		ListView lv_smsList = findViewById(R.id.lv_sms_list);
		ListAdapter smsListAdapter = new SmsListItemAdapter(this, R.layout.sms_list_adapter, receivedSms);
		lv_smsList.setAdapter(smsListAdapter);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 23){
			setupSmsList();
		}
	}
}
