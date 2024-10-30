package com.vokrob.bluetooth_monitor.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.SharedPreferences;

import com.vokrob.bluetooth_monitor.adapter.BtConsts;

public class BtConnection {
    private Context context;
    private SharedPreferences pref;
    private BluetoothAdapter btAdapter;
    private BluetoothDevice device;
    private ConnectThread connectThread;

    public BtConnection(Context context) {
        this.context = context;

        pref = context.getSharedPreferences(BtConsts.MY_PREF, Context.MODE_PRIVATE);
        btAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public void connect() {
        String mac = pref.getString(BtConsts.MAC_KEY, "");
        if (!btAdapter.isEnabled() || mac.isEmpty()) return;

        device = btAdapter.getRemoteDevice(mac);
        if (device == null) return;

        connectThread = new ConnectThread(btAdapter, device);
        connectThread.start();
    }
}































