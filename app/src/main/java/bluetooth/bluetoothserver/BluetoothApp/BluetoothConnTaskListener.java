package bluetooth.bluetoothserver.BluetoothApp;

import android.bluetooth.BluetoothSocket;

/**
 * Created by sanderson on 08/04/2015.
 */
public interface BluetoothConnTaskListener {

    public void connected(BluetoothSocket socket);
    public void read(String... args);
}
