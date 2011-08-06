package com.averescu.android.SmsPopup.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {
	private SmsMessage[] msgs = null;
	private String receivedMsg;
	private String phoneNo;

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();

		if (bundle != null) {
			/* retrieve the SMS message received */
			Object[] pdus = (Object[]) bundle.get("pdus");
			msgs = new SmsMessage[pdus.length];
			/* Check each message for predefined pattern */
			for (int i = 0; i < msgs.length; i++) {
				msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
				receivedMsg = msgs[i].getMessageBody().toString();
				phoneNo = msgs[i].getOriginatingAddress();
			}

			startPopup(context);

		}

	}

	private void startPopup(Context context) {
		Intent popup = new Intent(context,
				com.averescu.android.SmsPopup.popup.SmsDialog.class);
		popup.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		popup.putExtra("phoneNo", phoneNo);
		popup.putExtra("receivedMsg", receivedMsg);
		context.startActivity(popup);
	}
}
