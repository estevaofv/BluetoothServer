package bluetooth.bluetoothserver;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


public class BluetoothServerActivity extends ActionBarActivity implements BluetoothConnTaskListener {

    private BluetoothServerTask server;
    private ConnectedThread connThread;

    private TextView tv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_server);

        tv = (TextView) findViewById(R.id.textView);

        server = new BluetoothServerTask();
        server.addBTListener(this);

        server.execute();

    }

    @Override
    public void update(BluetoothSocket socket) {
        connThread = new ConnectedThread(socket,mHandler);
        connThread.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bluetooth_server, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            byte[] writeBuf = (byte[]) msg.obj;
            int begin = (int)msg.arg1;
            int end = (int)msg.arg2;

            switch(msg.what) {
                case 1:
                    String writeMessage = new String(writeBuf);


                    writeMessage = writeMessage.substring(begin, end);
                    tv.setText(writeMessage);
                    int x = 0;
                    break;
            }
        }
    };

}
