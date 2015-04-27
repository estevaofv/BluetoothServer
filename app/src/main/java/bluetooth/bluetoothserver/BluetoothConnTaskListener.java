package bluetooth.bluetoothserver;

import android.bluetooth.BluetoothSocket;

/**
 * Created by sanderson on 08/04/2015.
 */
public interface BluetoothConnTaskListener {

    public void update(BluetoothSocket socket);

}
