 package com.example.fares.blue_cerist;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

 public class MainActivity extends AppCompatActivity {

     BluetoothManager btManager;
     BluetoothAdapter btAdapter=null;
     BluetoothLeScanner btScanner;
     BluetoothGatt btGatt;
     BluetoothGattService btgattservice;

     BluetoothGattCharacteristic SWITCHCharacteristic;

     Button switchonButton;
     Button switchoffButton;
     Button timerButton;
     TextView peripheralTextView;
     ImageSwitcher imageSwitcher;
     SeekBar seekBar;
     String adress;
     String devicename;
     private final static int REQUEST_ENABLE_BT = 1;


     private static final UUID UUID_Service = UUID.fromString("0000f00d-1212-efde-1523-785feabcd123");

     private static final UUID UUID_SWITCH = UUID.fromString("0000beef-1212-efde-1523-785feabcd123");



     private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;

     MenuItem scanitem;
     MenuItem stopscanitem;
     MenuItem connecteitem;
     MenuItem deconnectitem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        timerButton=(Button)findViewById(R.id.timerset);
        registerForContextMenu(timerButton);
        seekBar=(SeekBar)findViewById(R.id.seek);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                               @Override
                                               public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                                                   int seekvalue=0;
                                                   if(i>=0 && i<25)
                                                   {
                                                       seekvalue=1;
                                                   }
                                                   else if(i>25 && i<50)
                                                   {
                                                       seekvalue=2;
                                                   }
                                                   else if(i>50 && i<75)
                                                   {
                                                       seekvalue=3;
                                                   }
                                                   else if(i>75 && i<100)
                                                   {
                                                       seekvalue=4;
                                                   }
                                                   if(btGatt!=null)
                                                   {   byte[] value=new byte[1];
                                                       // value [0]=(byte)(01&0xff);
                                                       value [0]=(byte)(seekvalue);
                                                       SWITCHCharacteristic.setValue(value);
                                                       btGatt.writeCharacteristic(SWITCHCharacteristic);
                                                       imageSwitcher.setImageResource(R.drawable.on);}
                                                   else
                                                   {
                                                       peripheralTextView.setText("ble cerist device not found");
                                                   }
                                                   Log.d("progressbar======>>"," "+seekvalue);

                                               }

                                               @Override
                                               public void onStartTrackingTouch(SeekBar seekBar) {

                                               }

                                               @Override
                                               public void onStopTrackingTouch(SeekBar seekBar) {

                                               }

                                           }
        );

        imageSwitcher=(ImageSwitcher)findViewById(R.id.imageswitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView=new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                return imageView;
            }
        });
        imageSwitcher.setImageResource(R.drawable.off);
        peripheralTextView = (TextView) findViewById(R.id.hello);


        switchonButton = (Button) findViewById(R.id.on);


        switchoffButton = (Button) findViewById(R.id.off);



        btManager = (BluetoothManager)getSystemService(Context.BLUETOOTH_SERVICE);
        btAdapter = btManager.getAdapter();


        if (btAdapter == null || !btAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent,REQUEST_ENABLE_BT);

        }

    }

     @Override
     protected void onResume() {
         super.onResume();
         if (btAdapter == null || !btAdapter.isEnabled()) {
             Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
             startActivityForResult(enableIntent,REQUEST_ENABLE_BT);

         }
     }

     @Override
     public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // super.onCreateContextMenu(menu, v, menuInfo);
         MenuInflater inflater=getMenuInflater();
         inflater.inflate(R.menu.timer_menu,menu);
     }

     @Override
     public boolean onContextItemSelected(MenuItem item) {



         switch (item.getItemId())
         {

             case R.id.ten :
                 byte[] value_ten=new byte[1];
                 value_ten [0]=(byte)(04&0xff);
                 SWITCHCharacteristic.setValue(value_ten);
                 btGatt.writeCharacteristic(SWITCHCharacteristic);

                 Toast.makeText(getApplicationContext(),"ten seconde selected",Toast.LENGTH_SHORT).show();
                 return true;
             case R.id.twenty :

                 byte[] value_tenty=new byte[1];
                 value_tenty [0]=(byte)(02&0xff);
                 SWITCHCharacteristic.setValue(value_tenty);
                 btGatt.writeCharacteristic(SWITCHCharacteristic);

                 Toast.makeText(getApplicationContext(),"ten seconde selected",Toast.LENGTH_SHORT).show();
                 return true;
             case R.id.thirty :

                 byte[] value_thirty=new byte[1];
                 value_thirty [0]=(byte)(03&0xff);
                 SWITCHCharacteristic.setValue(value_thirty);
                 btGatt.writeCharacteristic(SWITCHCharacteristic);

                 Toast.makeText(getApplicationContext(),"ten seconde selected",Toast.LENGTH_SHORT).show();
                 return true;
             case R.id.two_menute :

                 byte[] value=new byte[1];
                 value [0]=(byte)(05&0xff);
                 SWITCHCharacteristic.setValue(value);
                 btGatt.writeCharacteristic(SWITCHCharacteristic);

                 Toast.makeText(getApplicationContext(),"ten seconde selected",Toast.LENGTH_SHORT).show();
                 return true;

             default:
                 return super.onContextItemSelected(item);

         }
     }

     // Device scan callback.
     private ScanCallback leScanCallback = new ScanCallback() {
         @Override
         public void onScanResult(int callbackType, ScanResult result) {

             adress=result.getDevice().getAddress();
             devicename=result.getDevice().getName();
             if (devicename.equals("Anis"))
             {
                 peripheralTextView.setText("Device Name: " + result.getDevice().getName() + " rssi: " + result.getRssi() + "\n");
             }
             else
             {
                 peripheralTextView.setText("device not found")  ;
             }


         }
     };

     private BluetoothGattCallback connectecallabck=new BluetoothGattCallback() {
         @Override
         public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
             super.onConnectionStateChange(gatt, status, newState);
             btGatt=gatt;
             gatt.discoverServices();

         }

         @Override
         public void onServicesDiscovered(BluetoothGatt gatt, int status) {
             super.onServicesDiscovered(gatt, status);
             BluetoothGattService service = gatt.getService(UUID_Service);
             SWITCHCharacteristic = service.getCharacteristic(UUID_SWITCH);


         }



         @Override
         public void onCharacteristicRead(BluetoothGatt gatt,final BluetoothGattCharacteristic characteristic, int status) {
             super.onCharacteristicRead(gatt, characteristic, status);

             final byte[] data=characteristic.getValue();

             String value = null;
             try {
                 value = new String(data,"ASCII");
             } catch (UnsupportedEncodingException e) {
                 e.printStackTrace();
             }
             peripheralTextView.setText(value);

             super.onCharacteristicRead(gatt, characteristic, status);
         }

         @Override
         public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
             super.onCharacteristicWrite(gatt, characteristic, status);
             Toast.makeText(getApplicationContext(),"on a ecrit",Toast.LENGTH_SHORT).show();
         }
     };

     public void startScanning() {



         btScanner.startScan(leScanCallback);


     }

     public void stopScanning() {

         btScanner.stopScan(leScanCallback);


     }
     public void connection()
     {

         if(devicename!=null)
         {   if (devicename.equals("Anis") )
         {
             peripheralTextView.setText("blue_cerist found");
             BluetoothDevice device=btAdapter.getRemoteDevice(adress);
             device.connectGatt(getApplicationContext(),false,connectecallabck);
             switchonButton.setEnabled(true);
             switchoffButton.setEnabled(true);
             seekBar.setEnabled(true);
             timerButton.setEnabled(true);
         }
         else
         {
             Toast.makeText(getApplicationContext(),"device not found",Toast.LENGTH_SHORT).show();
             peripheralTextView.setText("ble cerist device not found !");
         }}
         else
         {
             peripheralTextView.setText("no device detected!!");
             deconnectitem.setVisible(false);
             scanitem.setVisible(true);
         }



     }
     public void deconnection()
     {
         if(devicename.equals("blue_cerist")&& btGatt!=null)
         {  btGatt.disconnect();
         btGatt.close();
         switchonButton.setEnabled(false);
         switchoffButton.setEnabled(false);
         timerButton.setEnabled(false);
         seekBar.setEnabled(false);}
         else
         {
             switchonButton.setEnabled(false);
             switchoffButton.setEnabled(false);
             timerButton.setEnabled(false);
              peripheralTextView.setText("ble cerist device not found");
         }
     }
     public void read(View v)
     {

         btGatt.readCharacteristic(SWITCHCharacteristic);
     }
     public void writeON(View v)
     {
        if(btGatt!=null)
        {   byte[] value=new byte[1];
        // value [0]=(byte)(01&0xff);
            value [0]=(byte)(01);
         SWITCHCharacteristic.setValue(value);
         btGatt.writeCharacteristic(SWITCHCharacteristic);
         imageSwitcher.setImageResource(R.drawable.on);}
         else
        {
            peripheralTextView.setText("ble cerist device not found");
        }
     }
     public void writeOff(View v)
     {
         if(btGatt!=null)
         { byte[] value=new byte[1];
        // value [0]=(byte)(00&0xff);
             value [0]=(byte)(00);
         SWITCHCharacteristic.setValue(value);
         btGatt.writeCharacteristic(SWITCHCharacteristic);
         imageSwitcher.setImageResource(R.drawable.off);}
         else
         {
             peripheralTextView.setText("ble cerist device not found");
         }
     }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         //return super.onCreateOptionsMenu(menu);
         MenuInflater menuInflater=getMenuInflater();
         menuInflater.inflate(R.menu.menu_main,menu);
         scanitem=menu.findItem(R.id.scan);
         stopscanitem=menu.findItem(R.id.stopscan);
         connecteitem=menu.findItem(R.id.connecter);
         deconnectitem=menu.findItem(R.id.deconnecter);
         return  true;
     }

     @Override
     public boolean onOptionsItemSelected(MenuItem item) {

         switch (item.getItemId())
         {
             case R.id.scan:
                 if(btAdapter.isEnabled())
                 { Toast.makeText(getApplicationContext(),"scan commance",Toast.LENGTH_SHORT).show();
                     btScanner = btAdapter.getBluetoothLeScanner();
                     scanitem.setVisible(false);
                     stopscanitem.setVisible(true);
                     connecteitem.setVisible(false);
                     deconnectitem.setVisible(false);
                     startScanning();
                 }
                 else
                 {
                     Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                     startActivityForResult(enableIntent,REQUEST_ENABLE_BT);
                 }
                 return true;
             case R.id.stopscan:
                 if(btAdapter.isEnabled()){
                 Toast.makeText(getApplicationContext(),"stop scan",Toast.LENGTH_SHORT).show();
                     scanitem.setVisible(true);
                     stopscanitem.setVisible(false);
                     connecteitem.setVisible(true);
                     deconnectitem.setVisible(false);
                 stopScanning();

                 }
                 else
                 {
                     Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                     startActivityForResult(enableIntent,REQUEST_ENABLE_BT);
                 }
                 return true;
             case R.id.connecter:
                 if(btAdapter.isEnabled()){
                 Toast.makeText(getApplicationContext(),"connecter a blue_cerist",Toast.LENGTH_SHORT).show();
                     scanitem.setVisible(false);
                     stopscanitem.setVisible(false);
                     connecteitem.setVisible(false);
                     deconnectitem.setVisible(true);
                 connection();}
                 else
                 {
                     Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                     startActivityForResult(enableIntent,REQUEST_ENABLE_BT);
                 }
                 return true;
             case R.id.deconnecter:
                 if(btAdapter.isEnabled()){
                 Toast.makeText(getApplicationContext(),"deconnecter de blue_cerist",Toast.LENGTH_SHORT).show();
                     scanitem.setVisible(true);
                     stopscanitem.setVisible(false);
                     connecteitem.setVisible(true);
                     deconnectitem.setVisible(false);
                 deconnection();}
                 else
                 {
                     Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                     startActivityForResult(enableIntent,REQUEST_ENABLE_BT);
                 }
                 return true;

         }
         return super.onOptionsItemSelected(item);
     }

 }
