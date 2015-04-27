package bluetooth.bluetoothserver.BluetoothApp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import bluetooth.bluetoothserver.BluetoothConnTaskListener;
import bluetooth.bluetoothserver.ConnectedThread;

/**
 * Created by sanderson on 09/04/2015.
 */
public class BluetoothClientTask extends AsyncTask<String,String,BluetoothSocket> {

    private List<BluetoothConnTaskListener> bToothConnListeners = new ArrayList<BluetoothConnTaskListener>();

    private BluetoothSocket mmSocket=null;
    private BluetoothDevice mmDevice=null;
    private BluetoothAdapter mBluetoothAdapter;
    private ConnectedThread mConnectedThread;

    private UUID uuid = UUID.fromString("00002415-0000-1000-8000-00805F9B34FB");

    public BluetoothClientTask(BluetoothDevice device){
        BluetoothSocket tmp = null;
        mmDevice = device;

        // Get a BluetoothSocket to connect with the given BluetoothDevice
        try {
            // MY_UUID is the app's UUID string, also used by the server code
            tmp = device.createRfcommSocketToServiceRecord(uuid);
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        } catch (IOException e) { }
        mmSocket = tmp;
    }


    @Override
    protected BluetoothSocket doInBackground(String... params) {
        String result = "";
        mBluetoothAdapter.cancelDiscovery();

        try {

            mmSocket.connect();

        } catch (IOException connectException) {
            // Unable to connect; close the socket and get out
            try {
                mmSocket.close();
            } catch (IOException closeException) { }

        }

        return mmSocket;
    }


    @Override
    protected void onPostExecute(BluetoothSocket socket) {
        super.onPostExecute(socket);
        for(bluetooth.bluetoothserver.BluetoothConnTaskListener l: bToothConnListeners){
            l.update(socket);
        }
    }

    public void addBluetoothConnTaskListener(BluetoothConnTaskListener listerner){
        bToothConnListeners.add(listerner);
    }


}



