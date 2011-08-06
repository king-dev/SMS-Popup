package com.averescu.android.SmsPopup.popup;

import com.averescu.android.SmsPopup.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SmsReply extends Activity {
	
	private TextView quickReplyTextView;
	private EditText quickReplyEditText;
	private String phoneNo;
	private SmsAction sms;
	private String replyMessage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup_reply);
		
		quickReplyTextView = (TextView) findViewById(R.id.QuickReplyTextView);
		quickReplyEditText = (EditText) findViewById(R.id.QuickReplyEditText);
		sms = new SmsAction(this);
		
		Bundle bundle = this.getIntent().getExtras();
		phoneNo = bundle.getString("phoneNo");
		
		quickReplyTextView.setText("To: " + phoneNo);

	}
	
	public void sendReplyClickHandler(View target) {

		replyMessage = quickReplyEditText.getText().toString();
		sms.sendSMS(phoneNo, replyMessage);
		finish();
		
	}

}