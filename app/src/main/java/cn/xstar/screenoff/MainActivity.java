package cn.xstar.screenoff;

import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    DevicePolicyManager policyManager;
    ComponentName componentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        policyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        componentName = new ComponentName(this, LockReceiver.class);
        doLockScreen(policyManager, componentName);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (policyManager.isAdminActive(componentName)) {
            policyManager.lockNow();
        }
        finish();
    }

    void doLockScreen(DevicePolicyManager policyManager, ComponentName componentName) {
        if (policyManager.isAdminActive(componentName)) {
            policyManager.lockNow();
            finish();
            //如果设备管理器给app授权了
        } else {
            activeManager(componentName);
        }
    }

    private void activeManager(ComponentName componentName) {
        //使用隐式意图调用系统方法来激活指定的设备管理器
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "锁屏");
        startActivity(intent);
    }
}
