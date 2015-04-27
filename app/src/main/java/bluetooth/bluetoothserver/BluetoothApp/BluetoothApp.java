package bluetooth.bluetoothserver.BluetoothApp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import bluetooth.bluetoothserver.*;

/**
 * Created by sanderson on 26/04/2015.
 */
public class BluetoothApp implements BluetoothConnTaskListener{

    //constantes
    public static final int BLUETOOTH_SERVER = 0;
    public static final int BLUETOOTH_CLIENT = 1;

    //listeners
    public List<BluetoothAppListener> listeners = new ArrayList<BluetoothAppListener>();

    private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    private BluetoothClientTask clientTask;
    private BluetoothServerTask server;

    private BluetoothIOTask ioTask;
    private BluetoothDevice bluetoothDevice;
    private BluetoothSocket socket;

    private int typeBluetooth;

    public BluetoothApp(int typeBluetooth, String macAdressServer){
        this.typeBluetooth = typeBluetooth;

        switch(typeBluetooth) {
            case BLUETOOTH_SERVER:
                server = new BluetoothServerTask();
                server.execute();
            case BLUETOOTH_CLIENT:
                getDevice(macAdressServer);
                clientTask = new BluetoothClientTask(bluetoothDevice);
                clientTask.execute("");
        }
    }

    public void addBluetoothAppListener(BluetoothAppListener l){
        listeners.add(l);
    }

    private BluetoothDevice getDevice(String macAdress){
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

        // If there are paired devices
        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            for (BluetoothDevice device : pairedDevices) {
                if(device.getAddress().equals(macAdress)){
                    bluetoothDevice = device;
                }

            }
        }

        return bluetoothDevice;
    }



    @Override
    public void connected(BluetoothSocket socket) {
        for(BluetoothAppListener bt: listeners)
            bt.connected("Conexão realizada com sucesso");

        ioTask = new BluetoothIOTask(socket);
        ioTask.execute();
    }

    @Override
    public void read(String... args) {
        for(BluetoothAppListener bt: listeners)
            bt.read(args);
    }

    public void write(String string){
        if(ioTask!=null)
            ioTask.write(string.getBytes());
    }
}
