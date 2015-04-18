package bluetooth.bluetoothserver;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by sanderson on 09/04/2015.
 */
public class BluetoothClientTask  extends AsyncTask<String,String,String> {

    private List<BlueToothTaskListener> bToothListeners = new ArrayList<BlueToothTaskListener>();

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
    protected String doInBackground(String... params) {
        String result = "";
        mBluetoothAdapter.cancelDiscovery();

        try {
            // Connect the device through the socket. This will block
            // until it succeeds or throws an exception

            mmSocket.connect();

        } catch (IOException connectException) {
            // Unable to connect; close the socket and get out
            try {
                mmSocket.close();
            } catch (IOException closeException) { }

        }

        manageConnectedSocket(mmSocket);
        return null;
    }

    private void manageConnectedSocket(BluetoothSocket mmSocket) {
        int x =0;
    }


}



