package bluetooth.bluetoothserver.BluetoothApp;

import android.content.BroadcastReceiver;

/**
 * Created by sanderson on 26/04/2015.
 */
public interface BluetoothAppListener{

    public void connected(String... args);
    public void read(String... args);

}
