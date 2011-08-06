package com.averescu.android.SmsPopup.popup;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.widget.Toast;

public class SmsAction {

	private Context context;
	private BroadcastReceiver br;

	public SmsAction(Context context) {
		this.context = context;
	}

	public void sendSMS(String phoneNumber, String message) {
		String SENT = "SMS_SENT";
		String DELIVERED = "SMS_DELIVERED";

		PendingIntent sentPI = PendingIntent.getBroadcast(context, 0, new Intent(
				SENT), 0);

		PendingIntent deliveredPI = PendingIntent.getBroadcast(context, 0,
				new Intent(DELIVERED), 0);

		/* When the SMS has been sent */
		br = new BroadcastReceiver() {
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(context, "SMS sent",
							Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					Toast.makeText(context, "Generic failure",
							Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					Toast.makeText(context, "No service",
							Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					Toast.makeText(context, "Null PDU",
							Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					Toast.makeText(context, "Radio off",
							Toast.LENGTH_SHORT).show();
					break;
				}
				context.unregisterReceiver(br);

			}
		};
		context.registerReceiver(br, new IntentFilter(SENT));
		/*
		// ---when the SMS has been delivered---
		registerReceiver(new BroadcastReceiver() {
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(getBaseContext(), "SMS delivered",
							Toast.LENGTH_SHORT).show();
					break;
				case Activity.RESULT_CANCELED:
					Toast.makeText(getBaseContext(), "SMS not delivered",
							Toast.LENGTH_SHORT).show();
					break;
				}
			}
		}, new IntentFilter(DELIVERED));
		*/
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
	}

}
