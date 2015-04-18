package bluetooth.bluetoothserver;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class PairedDevicesActivity extends ListActivity {

    private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    List<Map<String, String>> data = new ArrayList<Map<String, String>>();


    public static final String NAME_DEVICE = "name_device";
    public static final String MAC_DEVICE  = "mac_device";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            for (BluetoothDevice device : pairedDevices) {
                Map<String, String> item = new HashMap<String, String>();

                item.put(NAME_DEVICE, device.getName());
                item.put(MAC_DEVICE,device.getAddress());

                data.add(item);

            }

            String[] from = new String[]{NAME_DEVICE, MAC_DEVICE};
            int[] to = new int[]{R.id.tv_nome_device, R.id.tv_mac_address};
            int resource = R.layout.activity_paired_devices2;

            SimpleAdapter adapter = new SimpleAdapter(this, data, resource, from, to);

            setListAdapter(adapter);

            setTitle("Dispositivos");

        }

    }



    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Map<String, String> item = data.get(position);
        String mac    = item.get(MAC_DEVICE);

        Intent intent = new Intent(this, BluetoothClientActivity.class);
        intent.putExtra(MAC_DEVICE,mac);
        startActivity(intent);

    }


}
