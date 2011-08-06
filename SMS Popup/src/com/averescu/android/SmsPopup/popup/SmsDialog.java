package com.averescu.android.SmsPopup.popup;

import com.averescu.android.SmsPopup.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SmsDialog extends Activity {

	private TextView smsText;
	public static final String SMS_MIME_TYPE = "vnd.android-dir/mms-sms";
	private String phoneNo;
	private String message;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup);

		smsText = (TextView) findViewById(R.id.smsText);

		Bundle bundle = this.getIntent().getExtras();
		message = bundle.getString("receivedMsg");
		phoneNo = bundle.getString("phoneNo");

		smsText.setText("From: " + phoneNo + "\n" + message);

	}

	public void inboxClickHandler(View target) {

		Intent conversations = new Intent(Intent.ACTION_MAIN);
		conversations.setType(SMS_MIME_TYPE);
		int flags = Intent.FLAG_ACTIVITY_NEW_TASK
				| Intent.FLAG_ACTIVITY_SINGLE_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TOP;
		conversations.setFlags(flags);

		startActivity(conversations);

	}

}
