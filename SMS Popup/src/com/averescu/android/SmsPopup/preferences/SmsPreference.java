package com.averescu.android.SmsPopup.preferences;

import com.averescu.android.SmsPopup.R;
import com.averescu.android.SmsPopup.receiver.SmsReceiver;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.preference.CheckBoxPreference;
import android.util.AttributeSet;
import android.widget.Toast;

public class SmsPreference extends CheckBoxPreference {
	private Context context;

	public SmsPreference(Context context) {
		super(context);
		this.context = context;
	}

	public SmsPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	public SmsPreference(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
	}

	@Override
	protected void onClick() {
		super.onClick();
		changeReceiverState(context, isChecked());
	}

	public void changeReceiverState(Context context, boolean enable) {
		PackageManager pm = context.getPackageManager();
		ComponentName cn = new ComponentName(context, SmsReceiver.class);

		if (enable) {
			pm.setComponentEnabledSetting(cn,
					PackageManager.COMPONENT_ENABLED_STATE_DEFAULT,
					PackageManager.DONT_KILL_APP);

			Toast.makeText(context, R.string.pref_notification_enabled,
					Toast.LENGTH_SHORT).show();
		} else {
			pm.setComponentEnabledSetting(cn,
					PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
					PackageManager.DONT_KILL_APP);

			Toast.makeText(context, R.string.pref_notification_disabled,
					Toast.LENGTH_SHORT).show();
		}
	}

}
