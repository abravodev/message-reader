package com.geevisoft.messagereader.presentation.views.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
	private static final int REQUEST_PERMISSION_CODE = 23;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		presenter = new MainPresenterImpl(new SmsProviderImpl(this	));

		if(PermissionHelper.readSmsPermissionIsGranted(this)){
			setupSmsList();
		} else {
			boolean requested = PermissionHelper.showReadSmsPermissionRequest(this, REQUEST_PERMISSION_CODE);
			if(!requested){
				showRequestPermissionAbout();
			}
		}
	}

	private void setupSmsList(){
		List<Sms> receivedSms = presenter.getReceivedSms();
		ListView lv_smsList = findViewById(R.id.lv_sms_list);
		ListAdapter smsListAdapter = new SmsListItemAdapter(this, R.layout.sms_list_adapter, receivedSms);
		lv_smsList.setAdapter(smsListAdapter);
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if(requestCode != REQUEST_PERMISSION_CODE){
			return;
		}
		if(PermissionHelper.readSmsPermissionIsGranted(this)){
			setupSmsList();
		} else {
			showRequestPermissionAbout();
		}
	}

	private void showRequestPermissionAbout(){
		Toast.makeText(this, "Permission is necessary to read sms. Please, grant permission through mobile settings.", Toast.LENGTH_LONG).show();
	}
}
