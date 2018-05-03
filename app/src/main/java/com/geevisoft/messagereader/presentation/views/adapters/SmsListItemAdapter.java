package com.geevisoft.messagereader.presentation.views.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.geevisoft.messagereader.R;
import com.geevisoft.messagereader.domain.entities.Sms;

import java.util.List;

public class SmsListItemAdapter extends ArrayAdapter<Sms> {

	private final LayoutInflater inflater;
	private final int layoutId;
	private final List<Sms> smsList;
	private SmsInfoHolder holder;

	public SmsListItemAdapter(@NonNull Context context, int resource, @NonNull List<Sms> smsList) {
		super(context, resource);
		this.layoutId = resource;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.smsList = smsList;
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View row, @NonNull ViewGroup parent) {
		row = initView(row, parent);

		Sms sms = smsList.get(position);
		holder.updateElements(sms);

		return row;
	}

	@Override
	public int getCount() {
		return smsList.size();
	}

	private View initView(View row, ViewGroup parent){
		if (row == null) {
			row = inflater.inflate(layoutId, parent, false);
			holder = new SmsInfoHolder(row);
			row.setTag(holder);
		} else {
			holder = (SmsInfoHolder) row.getTag();
		}

		return row;
	}

	private class SmsInfoHolder {
		private final TextView smsSender;
		private final TextView smsDate;
		private final TextView smsBody;

		private SmsInfoHolder(View row) {
			this.smsSender = row.findViewById(R.id.tv_sms_sender);
			this.smsDate = row.findViewById(R.id.tv_sms_date);
			this.smsBody = row.findViewById(R.id.tv_sms_body);
		}

		private void updateElements(Sms sms){
			smsSender.setText(sms.getSender());
			smsDate.setText(sms.getSendDateFormatted());
			smsBody.setText(sms.getText());
		}
	}
}
