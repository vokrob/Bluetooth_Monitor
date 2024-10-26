package com.vokrob.bluetooth_monitor;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.vokrob.bluetooth_monitor.adapter.BtAdapter;
import com.vokrob.bluetooth_monitor.adapter.ListItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BtListActivity extends AppCompatActivity {
    private ListView listView;
    private BtAdapter adapter;
    private BluetoothAdapter btAdapter;
    private List<ListItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bt_list);
        init();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bt_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else if (item.getItemId() == R.id.search) {
            ListItem itemTitle = new ListItem();
            itemTitle.setItemType(BtAdapter.TITLE_ITEM_TYPE);
            list.add(itemTitle);
            adapter.notifyDataSetChanged();
        }

        return super.onOptionsItemSelected(item);
    }

    private void init() {
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        list = new ArrayList<>();

        ActionBar ab = getSupportActionBar();
        if (ab == null) return;
        ab.setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.listView);
        adapter = new BtAdapter(this, R.layout.bt_list_item, list);
        listView.setAdapter(adapter);

        getPairedDevices();
    }

    @SuppressLint("MissingPermission")
    private void getPairedDevices() {
        @SuppressLint("MissingPermission")
        Set<BluetoothDevice> pairedDevices = btAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            list.clear();
            for (BluetoothDevice device : pairedDevices) {
                @SuppressLint("MissingPermission")
                ListItem item = new ListItem();
                item.setBtName(device.getName());
                item.setBtMac(device.getAddress());
                list.add(item);
            }
            adapter.notifyDataSetChanged();
        }
    }
}
























