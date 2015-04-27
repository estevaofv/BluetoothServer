package bluetooth.bluetoothserver;

import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by sanderson on 10/04/2015.
 */
public class ConnectedThread extends Thread {


    private Boolean running = false;
    private final BluetoothSocket mmSocket;
    private final InputStream mmInStream;
    private final OutputStream mmOutStream;
    private Handler mHandler;
    private static final int SUCCESS_READ = 1;

    public ConnectedThread(BluetoothSocket socket, Handler handler) {
        mmSocket = socket;
        mHandler = handler;
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



    public void run() {
        byte[] buffer = new byte[1024];
        int begin = 0;
        int bytes = 0;
        while (true) {
            try {

                // Read from the InputStream
                bytes = mmInStream.read(buffer);
                // Send the obtained bytes to the UI activity
                // String data = new String(buffer,0,bytes);
                mHandler.obtainMessage(1, 0,bytes, buffer).sendToTarget();
                //mHandler.handleMessage(msgObj);

            } catch (IOException e) {

                break;
            }
        }
    }

    public void write(byte[] bytes) {
        try {
            mmOutStream.write(bytes);
            mmOutStream.flush();
        } catch (IOException e) {
            String entrou = "";
        }
    }

    public void close() {
        running = false;
        try {
            mmSocket.close();
        } catch (IOException e) { }
    }

}




