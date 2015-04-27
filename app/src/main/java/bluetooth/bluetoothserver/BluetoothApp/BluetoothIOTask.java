package bluetooth.bluetoothserver.BluetoothApp;

import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by sanderson on 25/04/2015.
 */
public class BluetoothIOTask extends AsyncTask<String, String, String> {



    private final BluetoothSocket mmSocket;
    private final InputStream mmInStream;
    private final OutputStream mmOutStream;
    private boolean running = false;


    public BluetoothIOTask(BluetoothSocket socket) {

        mmSocket = socket;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;

        try {
            tmpIn = socket.getInputStream();
            tmpOut = socket.getOutputStream();
        } catch (IOException e) { }

        mmInStream = tmpIn;
        mmOutStream = tmpOut;
        running = true;
    }


    @Override
    protected String doInBackground(String... params) {

        byte[] buffer = new byte[1024];
        int begin = 0;
        int bytes = 0;
        while (true) {
            try {
                bytes = mmInStream.read(buffer);
                String data = new String(buffer,0,bytes);
            } catch (IOException e) {
                break;
            }
        }

        return null;
    }

    public void read(){

    }

    public void write(byte[] bytes) {
        try {
            mmOutStream.write(bytes);
        } catch (IOException e) { }
    }

    public void close() {
        running = false;
        try {
            mmSocket.close();
        } catch (IOException e) { }
    }

}
