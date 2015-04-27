package bluetooth.bluetoothserver;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by sanderson on 08/04/2015.
 */
public class BluetoothServerTask extends AsyncTask<String,String,BluetoothSocket> {

    private List<BluetoothConnTaskListener> bToothListeners = new ArrayList<BluetoothConnTaskListener>();

    private BluetoothServerSocket mmServerSocket;
    private BluetoothAdapter mBluetoothAdapter;


    private UUID uuid = UUID.fromString("00002415-0000-1000-8000-00805F9B34FB");

    public BluetoothServerTask() {
        // Use a temporary object that is later assigned to mmServerSocket,
        // because mmServerSocket is final
        BluetoothServerSocket tmp = null;
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        try {
            tmp = mBluetoothAdapter.listenUsingInsecureRfcommWithServiceRecord("sanderson", uuid);
        } catch (IOException e) { }
        mmServerSocket = tmp;
    }



    @Override
    protected BluetoothSocket doInBackground(String... params) {

        BluetoothSocket socket = null;
        // Keep listening until exception occurs or a socket is returned
        while (true) {
            try {
                socket = mmServerSocket.accept();
                socket.getInputStream();
            } catch (IOException e) {
                break;
            }
            // If a connection was accepted
            if (socket != null) {
                // Do work to manage the connection (in a separate thread)
                try {
                    //result = Converter.inputStremToString(socket.getInputStream());
                    mmServerSocket.close();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }else{

            }
        }

        return socket;

    }




    @Override
    protected void onPostExecute(BluetoothSocket s) {
        super.onPostExecute(s);
        for(BluetoothConnTaskListener btl : bToothListeners){
            btl.update(s);
        }
    }



    public void addBTListener(BluetoothConnTaskListener bt){
        bToothListeners.add(bt);
    }

}


