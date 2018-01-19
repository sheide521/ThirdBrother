package com.zl.third.brother.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zl.third.brother.R;
import com.zl.third.brother.adapter.base.RcyCommonAdapter;
import com.zl.third.brother.adapter.base.RcyViewHolder;
import com.zl.third.brother.utils.Permisson.PermissionHelper;
import com.zl.third.brother.utils.bluetooth.DeviceControlActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.bluetooth.BluetoothAdapter.STATE_CONNECTED;
import static android.telecom.Call.STATE_DISCONNECTED;

/**
 * Created by zhaolong on
 * 2017/12/20
 * 蓝牙
 */
public class BleActivity extends BaseActivity {
    @Bind(R.id.tv_open_ble)
    TextView tvOpenBle;
    @Bind(R.id.tv_scan_ble)
    TextView tvScanBle;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.tv_stop_ble)
    TextView tvStopBle;
    private BluetoothManager bluetoothManager;
    private BluetoothAdapter bluetoothAdapter;
    private Handler mHandler;
    private RcyCommonAdapter adapter;

    private List<BluetoothDevice> mLeDevices = new ArrayList<BluetoothDevice>();

    private static final int REQUEST_ENABLE_BT = 1;
    // 10秒后停止查找搜索.
    private static final long SCAN_PERIOD = 10000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ble);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mHandler = new Handler();
/**
 * 设备是否支持蓝牙
 */
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "不支持蓝牙", Toast.LENGTH_SHORT).show();
            finish();
        }

        /**
         * 开启蓝牙
         */
//        adapter = BluetoothAdapter.getDefaultAdapter();//此方法也可以开启
        bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();

        // 检查设备上是否支持蓝牙
        if (bluetoothAdapter == null) {
            showToast("没有发现蓝牙模块");
            return;
        }

        scanLeDevice(true);
    }

    /**
     * 扫描设备
     */
    private void scanLeDevice(final boolean enable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            if (enable) {
                mLeDevices.clear();//清空集合
                // Stops scanning after a pre-defined scan period.
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                            bluetoothAdapter.stopLeScan(mLeScanCallback);
                        }
                    }
                }, SCAN_PERIOD);
                bluetoothAdapter.startLeScan(mLeScanCallback);
            } else {

                try {
                    bluetoothAdapter.stopLeScan(mLeScanCallback);
                } catch (Exception e) {
                }
            }
        }
    }

    // Device scan callback.
    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {

        @Override
        public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (!mLeDevices.contains(device)) {
                        mLeDevices.add(device);
                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(bActivity));
                    adapter = getAdapter();
                    recyclerView.setAdapter(adapter);
                }
            });
        }
    };

    private RcyCommonAdapter getAdapter() {
        return new RcyCommonAdapter<BluetoothDevice>(this, mLeDevices, true, recyclerView) {

            @Override
            public void convert(RcyViewHolder holder, BluetoothDevice bluetoothDevice) {
                if (bluetoothDevice == null) {
                    return;
                }

                TextView tv = holder.getView(R.id.id_num);
                tv.setText(bluetoothDevice.getAddress() + "------" + bluetoothDevice.getName());
            }

            @Override
            public int getLayoutId(int position) {
                return R.layout.item_home;
            }

            @Override
            public void onItemClickListener(int position) {
                BluetoothDevice device = mDatas.get(position);

                if (device == null) return;
                final Intent intent = new Intent(bActivity, DeviceControlActivity.class);
//                intent.putExtra(DeviceControlActivity.EXTRAS_DEVICE_NAME, device.getName());
                intent.putExtra(DeviceControlActivity.EXTRAS_DEVICE_ADDRESS, device.getAddress());
                startActivity(intent);
            }
        };
    }

    @Override
    protected void onPause() {
        super.onPause();
        scanLeDevice(false);
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.tv_open_ble, R.id.tv_scan_ble, R.id.tv_stop_ble})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_open_ble://打开蓝牙
                if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                }
                break;
            case R.id.tv_scan_ble://扫描蓝牙
                PermissionHelper.requestLocation(new PermissionHelper.OnPermissionGrantedListener() {
                    @Override
                    public void onPermissionGranted() {
                        scanLeDevice(true);
                    }
                });
                break;
            case R.id.tv_stop_ble://停止扫描
                scanLeDevice(false);
                break;
        }
    }
}
