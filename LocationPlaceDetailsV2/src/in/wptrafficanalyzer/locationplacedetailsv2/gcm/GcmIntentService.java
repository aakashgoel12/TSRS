package in.wptrafficanalyzer.locationplacedetailsv2.gcm;

import com.sinch.android.rtc.NotificationResult;
import com.sinch.android.rtc.SinchHelpers;
import in.wptrafficanalyzer.locationplacedetailsv2.SinchServiceCall;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

public class GcmIntentService extends IntentService implements ServiceConnection {

    private Intent mIntent;

    public GcmIntentService() {
        super("GcmIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (SinchHelpers.isSinchPushIntent(intent)) {
            mIntent = intent;
            connectToService();
        } else {
            GcmBroadcastReceiver.completeWakefulIntent(intent);
        }
    }

    private void connectToService() {
        getApplicationContext().bindService(new Intent(this, SinchServiceCall.class), this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (mIntent == null) {
            return;
        }

        if (SinchHelpers.isSinchPushIntent(mIntent)) {
            SinchServiceCall.SinchServiceInterface sinchService = (SinchServiceCall.SinchServiceInterface) iBinder;
            if (sinchService != null) {
                NotificationResult result = sinchService.relayRemotePushNotificationPayload(mIntent);
                // handle result, e.g. show a notification or similar
            }
        }

        GcmBroadcastReceiver.completeWakefulIntent(mIntent);
        mIntent = null;
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
    }

}