package cn.xstar.screenoff;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author: xstar
 * @since: 2018-02-22.
 */

public class LockReceiver extends DeviceAdminReceiver {
    @Override
    public void onEnabled(Context context, Intent intent) {
        super.onEnabled(context, intent);
        Toast.makeText(context, "已激活，再次点击图标即可锁屏！", Toast.LENGTH_LONG).show();
    }
}
