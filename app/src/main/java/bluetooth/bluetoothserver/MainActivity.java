package bluetooth.bluetoothserver;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity{


    private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private static final int REQUEST_ENABLE_BT = 123;

    private Button btn_config;
    private Button btn_server;
    private Button btn_client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getButtons();
        disableButtons();

        if (mBluetoothAdapter == null) {
            Toast.makeText(this,R.string.bt_doesnt_work,Toast.LENGTH_LONG).show();
        }else{
            btn_config.setEnabled(true);
            if (mBluetoothAdapter.isEnabled()) {
                enableButtonsBlueTooth();
            }
        }

    }


    public void configBT(View v){
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }else{
            Toast.makeText(this, R.string.btActiv, Toast.LENGTH_SHORT).show();
        }
    }


    public void goToBluetoothServer(View v){
        Intent i = new Intent(this, BluetoothServerActivity.class);
        startActivity(i);
    }

    public void goToBluetoothClient(View v){
        Intent i = new Intent(this, PairedDevicesActivity.class);
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_ENABLE_BT){
            if(resultCode== Activity.RESULT_OK){
                enableButtonsBlueTooth();
                Toast.makeText(this,R.string.bluetooth_activ,Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void getButtons(){
        btn_config = (Button)findViewById(R.id.btn_config_bt);
        btn_server = (Button)findViewById(R.id.btn_bt_server);
        btn_client = (Button)findViewById(R.id.btn_bt_client);
    }

    private void disableButtons(){
        btn_config.setEnabled(false);
        btn_server.setEnabled(false);
        btn_client.setEnabled(false);
    }

    private void enableButtonsBlueTooth(){
        btn_server.setEnabled(true);
        btn_client.setEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


}
