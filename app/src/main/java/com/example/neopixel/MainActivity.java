package com.example.neopixel;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import android.bluetooth.BluetoothSocket;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


import java.io.IOException;
import java.util.UUID;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener{
    static final int[] Colorlist = {Color.rgb(255,0,0),Color.rgb(255,165,0),Color.rgb(255,255,0),Color.rgb(204,255,0),
                       Color.rgb(0,255,0),Color.rgb(0,255,255),Color.rgb(0,0,255),Color.rgb(0,127,255),
                       Color.rgb(255,0,127),Color.rgb(255,0,255),Color.rgb(139,0,255),Color.rgb(170,170,170),
                       Color.rgb(255,255,255)};
    static int ColorNumber=11;
    static final String[] Led=new String[121];
    static final String[] ColorState=new String[121];
    static final int[] ColorStore=new int[121];

    String Colorcode;
    String sendtxt;


    static final String db_name = "LEDStore";
    static final String tb_name =  "LEDTable";
    SQLiteDatabase db;


    public final static String MODULE_MAC = "00:19:07:00:15:80";
    public final static int REQUEST_ENABLE_BT = 1;
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
    BluetoothAdapter bta;                 //bluetooth stuff
    BluetoothSocket mmSocket;             //bluetooth stuff
    BluetoothDevice mmDevice;             //bluetooth stuff
    projects.pers.sbp.ardcon.ConnectedThread btt = null;           //Our custom thread
    public Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        InitialListener();
        InitialLed();
        EditText edit = findViewById(R.id.editText);


        db = openOrCreateDatabase(db_name, Context.MODE_PRIVATE,null);
        String createTable = "CREATE TABLE IF NOT EXISTS " + tb_name + "(LED VARCHAR(125),NAME VARCHAR(32))";
        db.execSQL(createTable);

        bta = BluetoothAdapter.getDefaultAdapter();
        if(!bta.isEnabled()){
            Intent enableBTIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBTIntent, REQUEST_ENABLE_BT);
        }
        else{
            initiateBluetoothProcess();
        }

    }
    public void InitialListener() {
        findViewById(R.id.view0).setOnClickListener(this);
        findViewById(R.id.view1).setOnClickListener(this);
        findViewById(R.id.view2).setOnClickListener(this);
        findViewById(R.id.view3).setOnClickListener(this);
        findViewById(R.id.view4).setOnClickListener(this);
        findViewById(R.id.view5).setOnClickListener(this);
        findViewById(R.id.view6).setOnClickListener(this);
        findViewById(R.id.view7).setOnClickListener(this);
        findViewById(R.id.view8).setOnClickListener(this);
        findViewById(R.id.view9).setOnClickListener(this);
        findViewById(R.id.view10).setOnClickListener(this);
        findViewById(R.id.view11).setOnClickListener(this);
        findViewById(R.id.view12).setOnClickListener(this);
        findViewById(R.id.view13).setOnClickListener(this);
        findViewById(R.id.view14).setOnClickListener(this);
        findViewById(R.id.view15).setOnClickListener(this);
        findViewById(R.id.view16).setOnClickListener(this);
        findViewById(R.id.view17).setOnClickListener(this);
        findViewById(R.id.view18).setOnClickListener(this);
        findViewById(R.id.view19).setOnClickListener(this);
        findViewById(R.id.view20).setOnClickListener(this);
        findViewById(R.id.view21).setOnClickListener(this);
        findViewById(R.id.view22).setOnClickListener(this);
        findViewById(R.id.view23).setOnClickListener(this);
        findViewById(R.id.view24).setOnClickListener(this);
        findViewById(R.id.view25).setOnClickListener(this);
        findViewById(R.id.view26).setOnClickListener(this);
        findViewById(R.id.view27).setOnClickListener(this);
        findViewById(R.id.view28).setOnClickListener(this);
        findViewById(R.id.view29).setOnClickListener(this);
        findViewById(R.id.view30).setOnClickListener(this);
        findViewById(R.id.view31).setOnClickListener(this);
        findViewById(R.id.view32).setOnClickListener(this);
        findViewById(R.id.view33).setOnClickListener(this);
        findViewById(R.id.view34).setOnClickListener(this);
        findViewById(R.id.view35).setOnClickListener(this);
        findViewById(R.id.view36).setOnClickListener(this);
        findViewById(R.id.view37).setOnClickListener(this);
        findViewById(R.id.view38).setOnClickListener(this);
        findViewById(R.id.view39).setOnClickListener(this);
        findViewById(R.id.view40).setOnClickListener(this);
        findViewById(R.id.view41).setOnClickListener(this);
        findViewById(R.id.view42).setOnClickListener(this);
        findViewById(R.id.view43).setOnClickListener(this);
        findViewById(R.id.view44).setOnClickListener(this);
        findViewById(R.id.view45).setOnClickListener(this);
        findViewById(R.id.view46).setOnClickListener(this);
        findViewById(R.id.view47).setOnClickListener(this);
        findViewById(R.id.view48).setOnClickListener(this);
        findViewById(R.id.view49).setOnClickListener(this);
        findViewById(R.id.view50).setOnClickListener(this);
        findViewById(R.id.view51).setOnClickListener(this);
        findViewById(R.id.view52).setOnClickListener(this);
        findViewById(R.id.view53).setOnClickListener(this);
        findViewById(R.id.view54).setOnClickListener(this);
        findViewById(R.id.view55).setOnClickListener(this);
        findViewById(R.id.view56).setOnClickListener(this);
        findViewById(R.id.view57).setOnClickListener(this);
        findViewById(R.id.view58).setOnClickListener(this);
        findViewById(R.id.view59).setOnClickListener(this);
        findViewById(R.id.view60).setOnClickListener(this);
        findViewById(R.id.view61).setOnClickListener(this);
        findViewById(R.id.view62).setOnClickListener(this);
        findViewById(R.id.view63).setOnClickListener(this);
        findViewById(R.id.view64).setOnClickListener(this);
        findViewById(R.id.view65).setOnClickListener(this);
        findViewById(R.id.view66).setOnClickListener(this);
        findViewById(R.id.view67).setOnClickListener(this);
        findViewById(R.id.view68).setOnClickListener(this);
        findViewById(R.id.view69).setOnClickListener(this);
        findViewById(R.id.view70).setOnClickListener(this);
        findViewById(R.id.view71).setOnClickListener(this);
        findViewById(R.id.view72).setOnClickListener(this);
        findViewById(R.id.view73).setOnClickListener(this);
        findViewById(R.id.view74).setOnClickListener(this);
        findViewById(R.id.view75).setOnClickListener(this);
        findViewById(R.id.view76).setOnClickListener(this);
        findViewById(R.id.view77).setOnClickListener(this);
        findViewById(R.id.view78).setOnClickListener(this);
        findViewById(R.id.view79).setOnClickListener(this);
        findViewById(R.id.view80).setOnClickListener(this);
        findViewById(R.id.view81).setOnClickListener(this);
        findViewById(R.id.view82).setOnClickListener(this);
        findViewById(R.id.view83).setOnClickListener(this);
        findViewById(R.id.view84).setOnClickListener(this);
        findViewById(R.id.view85).setOnClickListener(this);
        findViewById(R.id.view86).setOnClickListener(this);
        findViewById(R.id.view87).setOnClickListener(this);
        findViewById(R.id.view88).setOnClickListener(this);
        findViewById(R.id.view89).setOnClickListener(this);
        findViewById(R.id.view90).setOnClickListener(this);
        findViewById(R.id.view91).setOnClickListener(this);
        findViewById(R.id.view92).setOnClickListener(this);
        findViewById(R.id.view93).setOnClickListener(this);
        findViewById(R.id.view94).setOnClickListener(this);
        findViewById(R.id.view95).setOnClickListener(this);
        findViewById(R.id.view96).setOnClickListener(this);
        findViewById(R.id.view97).setOnClickListener(this);
        findViewById(R.id.view98).setOnClickListener(this);
        findViewById(R.id.view99).setOnClickListener(this);
        findViewById(R.id.view100).setOnClickListener(this);
        findViewById(R.id.view101).setOnClickListener(this);
        findViewById(R.id.view102).setOnClickListener(this);
        findViewById(R.id.view103).setOnClickListener(this);
        findViewById(R.id.view104).setOnClickListener(this);
        findViewById(R.id.view105).setOnClickListener(this);
        findViewById(R.id.view106).setOnClickListener(this);
        findViewById(R.id.view107).setOnClickListener(this);
        findViewById(R.id.view108).setOnClickListener(this);
        findViewById(R.id.view109).setOnClickListener(this);
        findViewById(R.id.view110).setOnClickListener(this);
        findViewById(R.id.view111).setOnClickListener(this);
        findViewById(R.id.view112).setOnClickListener(this);
        findViewById(R.id.view113).setOnClickListener(this);
        findViewById(R.id.view114).setOnClickListener(this);
        findViewById(R.id.view115).setOnClickListener(this);
        findViewById(R.id.view116).setOnClickListener(this);
        findViewById(R.id.view117).setOnClickListener(this);
        findViewById(R.id.view118).setOnClickListener(this);
        findViewById(R.id.view119).setOnClickListener(this);
        findViewById(R.id.view120).setOnClickListener(this);
    }
    public void InitialLed() {
        for (int i=0;i<121;i++) {
            ColorState[i] = ";";
            ColorStore[i] = 11;
            if (i<10)
                Led[i] = "00" + i;
            else if (i<100)
                Led[i] = "0" + i;
            else
                Led[i] = Integer.toString(i);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    protected void onResume() {
        super.onResume();
        updateLED();
        refresh();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == REQUEST_ENABLE_BT){
            initiateBluetoothProcess();
        }
    }

    public void initiateBluetoothProcess(){
        if(bta.isEnabled()){
            //attempt to connect to bluetooth module
            BluetoothSocket tmp = null;
            mmDevice = bta.getRemoteDevice(MODULE_MAC);
            //create socket
            try {
            tmp = mmDevice.createRfcommSocketToServiceRecord(MY_UUID);
            mmSocket = tmp;
            mmSocket.connect();
            Log.i("[BLUETOOTH]","Connected to: "+mmDevice.getName());
        }catch(IOException e){
            try{mmSocket.close();}catch(IOException c){return;}
        }
        Log.i("[BLUETOOTH]", "Creating handler");
        mHandler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == projects.pers.sbp.ardcon.ConnectedThread.RESPONSE_MESSAGE){

            }
        }
    };
        Log.i("[BLUETOOTH]", "Creating and running Thread");
    btt = new projects.pers.sbp.ardcon.ConnectedThread(mmSocket,mHandler);
        btt.start();
}
}


    @Override
    public void onClick(View v) {
        findViewById(v.getId()).setBackgroundColor(Colorlist[ColorNumber]);
        ConvertColor(ColorNumber);
        switch (v.getId()){
            case R.id.view0:
                ColorState[0] = Colorcode;
                ColorStore[0] = ColorNumber;
                sendtxt ='A' + Led[0] + ColorState[0];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view1:
                ColorState[1] = Colorcode;
                ColorStore[1] = ColorNumber;
                sendtxt ='A' + Led[1] + ColorState[1];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view2:
                ColorState[2] = Colorcode;
                ColorStore[2] = ColorNumber;
                sendtxt ='A' + Led[2] + ColorState[2];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view3:
                ColorState[3] = Colorcode;
                ColorStore[3] = ColorNumber;
                sendtxt ='A' + Led[3] + ColorState[3];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view4:
                ColorState[4] = Colorcode;
                ColorStore[4] = ColorNumber;
                sendtxt ='A' + Led[4] + ColorState[4];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view5:
                ColorState[5] = Colorcode;
                ColorStore[5] = ColorNumber;
                sendtxt ='A' + Led[5] + ColorState[5];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view6:
                ColorState[6] = Colorcode;
                ColorStore[6] = ColorNumber;
                sendtxt ='A' + Led[6] + ColorState[6];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view7:
                ColorState[7] = Colorcode;
                ColorStore[7] = ColorNumber;
                sendtxt ='A' + Led[7] + ColorState[7];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view8:
                ColorState[8] = Colorcode;
                ColorStore[8] = ColorNumber;
                sendtxt ='A' + Led[8] + ColorState[8];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view9:
                ColorState[9] = Colorcode;
                ColorStore[9] = ColorNumber;
                sendtxt ='A' + Led[9] + ColorState[9];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view10:
                ColorState[10] = Colorcode;
                ColorStore[10] = ColorNumber;
                sendtxt ='A' + Led[10] + ColorState[10];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view11:
                ColorState[11] = Colorcode;
                ColorStore[11] = ColorNumber;
                sendtxt ='A' + Led[11] + ColorState[11];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view12:
                ColorState[12] = Colorcode;
                ColorStore[12] = ColorNumber;
                sendtxt ='A' + Led[12] + ColorState[12];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view13:
                ColorState[13] = Colorcode;
                ColorStore[13] = ColorNumber;
                sendtxt ='A' + Led[13] + ColorState[13];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view14:
                ColorState[14] = Colorcode;
                ColorStore[14] = ColorNumber;
                sendtxt ='A' + Led[14] + ColorState[14];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view15:
                ColorState[15] = Colorcode;
                ColorStore[15] = ColorNumber;
                sendtxt ='A' + Led[15] + ColorState[15];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view16:
                ColorState[16] = Colorcode;
                ColorStore[16] = ColorNumber;
                sendtxt ='A' + Led[16] + ColorState[16];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view17:
                ColorState[17] = Colorcode;
                ColorStore[17] = ColorNumber;
                sendtxt ='A' + Led[17] + ColorState[17];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view18:
                ColorState[18] = Colorcode;
                ColorStore[18] = ColorNumber;
                sendtxt ='A' + Led[18] + ColorState[18];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view19:
                ColorState[19] = Colorcode;
                ColorStore[19] = ColorNumber;
                sendtxt ='A' + Led[19] + ColorState[19];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view20:
                ColorState[20] = Colorcode;
                ColorStore[20] = ColorNumber;
                sendtxt ='A' + Led[20] + ColorState[20];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view21:
                ColorState[21] = Colorcode;
                ColorStore[21] = ColorNumber;
                sendtxt ='A' + Led[21] + ColorState[21];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view22:
                ColorState[22] = Colorcode;
                ColorStore[22] = ColorNumber;
                sendtxt ='A' + Led[22] + ColorState[22];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view23:
                ColorState[23] = Colorcode;
                ColorStore[23] = ColorNumber;
                sendtxt ='A' + Led[23] + ColorState[23];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view24:
                ColorState[24] = Colorcode;
                ColorStore[24] = ColorNumber;
                sendtxt ='A' + Led[24] + ColorState[24];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view25:
                ColorState[25] = Colorcode;
                ColorStore[25] = ColorNumber;
                sendtxt ='A' + Led[25] + ColorState[25];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view26:
                ColorState[26] = Colorcode;
                ColorStore[26] = ColorNumber;
                sendtxt ='A' + Led[26] + ColorState[26];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view27:
                ColorState[27] = Colorcode;
                ColorStore[27] = ColorNumber;
                sendtxt ='A' + Led[27] + ColorState[27];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view28:
                ColorState[28] = Colorcode;
                ColorStore[28] = ColorNumber;
                sendtxt ='A' + Led[28] + ColorState[28];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view29:
                ColorState[29] = Colorcode;
                ColorStore[29] = ColorNumber;
                sendtxt ='A' + Led[29] + ColorState[29];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view30:
                ColorState[30] = Colorcode;
                ColorStore[30] = ColorNumber;
                sendtxt ='A' + Led[30] + ColorState[30];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view31:
                ColorState[31] = Colorcode;
                ColorStore[31] = ColorNumber;
                sendtxt ='A' + Led[31] + ColorState[31];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view32:
                ColorState[32] = Colorcode;
                ColorStore[32] = ColorNumber;
                sendtxt ='A' + Led[32] + ColorState[32];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view33:
                ColorState[33] = Colorcode;
                ColorStore[33] = ColorNumber;
                sendtxt ='A' + Led[33] + ColorState[33];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view34:
                ColorState[34] = Colorcode;
                ColorStore[34] = ColorNumber;
                sendtxt ='A' + Led[34] + ColorState[34];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view35:
                ColorState[35] = Colorcode;
                ColorStore[35] = ColorNumber;
                sendtxt ='A' + Led[35] + ColorState[35];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view36:
                ColorState[36] = Colorcode;
                ColorStore[36] = ColorNumber;
                sendtxt ='A' + Led[36] + ColorState[36];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view37:
                ColorState[37] = Colorcode;
                ColorStore[37] = ColorNumber;
                sendtxt ='A' + Led[37] + ColorState[37];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view38:
                ColorState[38] = Colorcode;
                ColorStore[38] = ColorNumber;
                sendtxt ='A' + Led[38] + ColorState[38];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view39:
                ColorState[39] = Colorcode;
                ColorStore[39] = ColorNumber;
                sendtxt ='A' + Led[39] + ColorState[39];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view40:
                ColorState[40] = Colorcode;
                ColorStore[40] = ColorNumber;
                sendtxt ='A' + Led[40] + ColorState[40];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view41:
                ColorState[41] = Colorcode;
                ColorStore[41] = ColorNumber;
                sendtxt ='A' + Led[41] + ColorState[41];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view42:
                ColorState[42] = Colorcode;
                ColorStore[42] = ColorNumber;
                sendtxt ='A' + Led[42] + ColorState[42];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view43:
                ColorState[43] = Colorcode;
                ColorStore[43] = ColorNumber;
                sendtxt ='A' + Led[43] + ColorState[43];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view44:
                ColorState[44] = Colorcode;
                ColorStore[44] = ColorNumber;
                sendtxt ='A' + Led[44] + ColorState[44];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view45:
                ColorState[45] = Colorcode;
                ColorStore[45] = ColorNumber;
                sendtxt ='A' + Led[45] + ColorState[45];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view46:
                ColorState[46] = Colorcode;
                ColorStore[46] = ColorNumber;
                sendtxt ='A' + Led[46] + ColorState[46];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view47:
                ColorState[47] = Colorcode;
                ColorStore[47] = ColorNumber;
                sendtxt ='A' + Led[47] + ColorState[47];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view48:
                ColorStore[48] = ColorNumber;
                ColorState[48] = Colorcode;
                sendtxt ='A' + Led[48] + ColorState[48];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view49:
                ColorStore[49] = ColorNumber;
                ColorState[49] = Colorcode;
                sendtxt ='A' + Led[49] + ColorState[49];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view50:
                ColorStore[50] = ColorNumber;
                ColorState[50] = Colorcode;
                sendtxt ='A' + Led[50] + ColorState[50];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view51:
                ColorStore[51] = ColorNumber;
                ColorState[51] = Colorcode;
                sendtxt ='A' + Led[51] + ColorState[51];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view52:
                ColorStore[52] = ColorNumber;
                ColorState[52] = Colorcode;
                sendtxt ='A' + Led[52] + ColorState[52];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view53:
                ColorStore[53] = ColorNumber;
                ColorState[53] = Colorcode;
                sendtxt ='A' + Led[53] + ColorState[53];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view54:
                ColorState[54] = Colorcode;
                ColorStore[54] = ColorNumber;
                sendtxt ='A' + Led[54] + ColorState[54];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view55:
                ColorState[55] = Colorcode;
                ColorStore[55] = ColorNumber;
                sendtxt ='A' + Led[55] + ColorState[55];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view56:
                ColorState[56] = Colorcode;
                ColorStore[56] = ColorNumber;
                sendtxt ='A' + Led[56] + ColorState[56];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view57:
                ColorState[57] = Colorcode;
                ColorStore[57] = ColorNumber;
                sendtxt ='A' + Led[57] + ColorState[57];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view58:
                ColorState[58] = Colorcode;
                ColorStore[58] = ColorNumber;
                sendtxt ='A' + Led[58] + ColorState[58];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view59:
                ColorState[59] = Colorcode;
                ColorStore[59] = ColorNumber;
                sendtxt ='A' + Led[59] + ColorState[59];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view60:
                ColorState[60] = Colorcode;
                ColorStore[60] = ColorNumber;
                sendtxt ='A' + Led[60] + ColorState[60];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view61:
                ColorState[61] = Colorcode;
                ColorStore[61] = ColorNumber;
                sendtxt ='A' + Led[61] + ColorState[61];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view62:
                ColorState[62] = Colorcode;
                ColorStore[62] = ColorNumber;
                sendtxt ='A' + Led[62] + ColorState[62];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view63:
                ColorStore[63] = ColorNumber;
                ColorState[63] = Colorcode;
                sendtxt ='A' + Led[63] + ColorState[63];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view64:
                ColorStore[64] = ColorNumber;
                ColorState[64] = Colorcode;
                sendtxt ='A' + Led[64] + ColorState[64];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view65:
                ColorStore[65] = ColorNumber;
                ColorState[65] = Colorcode;
                sendtxt ='A' + Led[65] + ColorState[65];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view66:
                ColorStore[66] = ColorNumber;
                ColorState[66] = Colorcode;
                sendtxt ='A' + Led[66] + ColorState[66];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view67:
                ColorStore[67] = ColorNumber;
                ColorState[67] = Colorcode;
                sendtxt ='A' + Led[67] + ColorState[67];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view68:
                ColorStore[68] = ColorNumber;
                ColorState[68] = Colorcode;
                sendtxt ='A' + Led[68] + ColorState[68];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view69:
                ColorStore[69] = ColorNumber;
                ColorState[69] = Colorcode;
                sendtxt ='A' + Led[69] + ColorState[69];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view70:
                ColorState[70] = Colorcode;
                ColorStore[70] = ColorNumber;
                sendtxt ='A' + Led[70] + ColorState[70];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view71:
                ColorState[71] = Colorcode;
                ColorStore[71] = ColorNumber;
                sendtxt ='A' + Led[71] + ColorState[71];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view72:
                ColorState[72] = Colorcode;
                ColorStore[72] = ColorNumber;
                sendtxt ='A' + Led[72] + ColorState[72];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view73:
                ColorState[73] = Colorcode;
                ColorStore[73] = ColorNumber;
                sendtxt ='A' + Led[73] + ColorState[73];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view74:
                ColorState[74] = Colorcode;
                ColorStore[74] = ColorNumber;
                sendtxt ='A' + Led[74] + ColorState[74];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view75:
                ColorState[75] = Colorcode;
                ColorStore[75] = ColorNumber;
                sendtxt ='A' + Led[75] + ColorState[75];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view76:
                ColorState[76] = Colorcode;
                ColorStore[76] = ColorNumber;
                sendtxt ='A' + Led[76] + ColorState[76];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view77:
                ColorState[77] = Colorcode;
                ColorStore[77] = ColorNumber;
                sendtxt ='A' + Led[77] + ColorState[77];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view78:
                ColorState[78] = Colorcode;
                ColorStore[78] = ColorNumber;
                sendtxt ='A' + Led[78] + ColorState[78];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view79:
                ColorState[79] = Colorcode;
                ColorStore[79] = ColorNumber;
                sendtxt ='A' + Led[79] + ColorState[79];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view80:
                ColorState[80] = Colorcode;
                ColorStore[80] = ColorNumber;
                sendtxt ='A' + Led[80] + ColorState[80];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view81:
                ColorState[81] = Colorcode;
                ColorStore[81] = ColorNumber;
                sendtxt ='A' + Led[81] + ColorState[81];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view82:
                ColorState[82] = Colorcode;
                ColorStore[82] = ColorNumber;
                sendtxt ='A' + Led[82] + ColorState[82];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view83:
                ColorState[83] = Colorcode;
                ColorStore[83] = ColorNumber;
                sendtxt ='A' + Led[83] + ColorState[83];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view84:
                ColorState[84] = Colorcode;
                ColorStore[84] = ColorNumber;
                sendtxt ='A' + Led[84] + ColorState[84];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view85:
                ColorState[85] = Colorcode;
                ColorStore[85] = ColorNumber;
                sendtxt ='A' + Led[85] + ColorState[85];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view86:
                ColorState[86] = Colorcode;
                ColorStore[86] = ColorNumber;
                sendtxt ='A' + Led[86] + ColorState[86];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view87:
                ColorState[87] = Colorcode;
                ColorStore[87] = ColorNumber;
                sendtxt ='A' + Led[87] + ColorState[87];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view88:
                ColorState[88] = Colorcode;
                ColorStore[88] = ColorNumber;
                sendtxt ='A' + Led[88] + ColorState[88];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view89:
                ColorState[89] = Colorcode;
                ColorStore[89] = ColorNumber;
                sendtxt ='A' + Led[89] + ColorState[89];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view90:
                ColorState[90] = Colorcode;
                ColorStore[90] = ColorNumber;
                sendtxt ='A' + Led[90] + ColorState[90];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view91:
                ColorState[91] = Colorcode;
                ColorStore[91] = ColorNumber;
                sendtxt ='A' + Led[91] + ColorState[91];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view92:
                ColorState[92] = Colorcode;
                ColorStore[92] = ColorNumber;
                sendtxt ='A' + Led[92] + ColorState[92];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view93:
                ColorState[93] = Colorcode;
                ColorStore[93] = ColorNumber;
                sendtxt ='A' + Led[93] + ColorState[93];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view94:
                ColorState[94] = Colorcode;
                ColorStore[94] = ColorNumber;
                sendtxt ='A' + Led[94] + ColorState[94];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view95:
                ColorState[95] = Colorcode;
                ColorStore[95] = ColorNumber;
                sendtxt ='A' + Led[95] + ColorState[95];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view96:
                ColorState[96] = Colorcode;
                ColorStore[96] = ColorNumber;
                sendtxt ='A' + Led[96] + ColorState[96];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view97:
                ColorState[97] = Colorcode;
                ColorStore[97] = ColorNumber;
                sendtxt ='A' + Led[97] + ColorState[97];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view98:
                ColorState[98] = Colorcode;
                ColorStore[98] = ColorNumber;
                sendtxt ='A' + Led[98] + ColorState[98];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view99:
                ColorState[99] = Colorcode;
                ColorStore[99] = ColorNumber;
                sendtxt ='A' + Led[99] + ColorState[99];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view100:
                ColorState[100] = Colorcode;
                ColorStore[100] = ColorNumber;
                sendtxt ='A' + Led[100] + ColorState[100];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view101:
                ColorState[101] = Colorcode;
                ColorStore[101] = ColorNumber;
                sendtxt ='A' + Led[101] + ColorState[101];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view102:
                ColorState[102] = Colorcode;
                ColorStore[102] = ColorNumber;
                sendtxt ='A' + Led[102] + ColorState[102];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view103:
                ColorState[103] = Colorcode;
                ColorStore[103] = ColorNumber;
                sendtxt ='A' + Led[103] + ColorState[103];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view104:
                ColorState[104] = Colorcode;
                ColorStore[104] = ColorNumber;
                sendtxt ='A' + Led[104] + ColorState[104];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view105:
                ColorState[105] = Colorcode;
                ColorStore[105] = ColorNumber;
                sendtxt ='A' + Led[105] + ColorState[105];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view106:
                ColorState[106] = Colorcode;
                ColorStore[106] = ColorNumber;
                sendtxt ='A' + Led[106] + ColorState[106];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view107:
                ColorState[107] = Colorcode;
                ColorStore[107] = ColorNumber;
                sendtxt ='A' + Led[107] + ColorState[107];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view108:
                ColorState[108] = Colorcode;
                ColorStore[108] = ColorNumber;
                sendtxt ='A' + Led[108] + ColorState[108];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view109:
                ColorState[109] = Colorcode;
                ColorStore[109] = ColorNumber;
                sendtxt ='A' + Led[109] + ColorState[109];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view110:
                ColorState[110] = Colorcode;
                ColorStore[110] = ColorNumber;
                sendtxt ='A' + Led[110] + ColorState[110];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view111:
                ColorState[111] = Colorcode;
                ColorStore[111] = ColorNumber;
                sendtxt ='A' + Led[111] + ColorState[111];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view112:
                ColorState[112] = Colorcode;
                ColorStore[112] = ColorNumber;
                sendtxt ='A' + Led[112] + ColorState[112];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view113:
                ColorState[113] = Colorcode;
                ColorStore[113] = ColorNumber;
                sendtxt ='A' + Led[113] + ColorState[113];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view114:
                ColorState[114] = Colorcode;
                ColorStore[114] = ColorNumber;
                sendtxt ='A' + Led[114] + ColorState[114];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view115:
                ColorState[115] = Colorcode;
                ColorStore[115] = ColorNumber;
                sendtxt ='A' + Led[115] + ColorState[115];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view116:
                ColorState[116] = Colorcode;
                ColorStore[116] = ColorNumber;
                sendtxt ='A' + Led[116] + ColorState[116];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view117:
                ColorState[117] = Colorcode;
                ColorStore[117] = ColorNumber;
                sendtxt ='A' + Led[117] + ColorState[117];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view118:
                ColorState[118] = Colorcode;
                ColorStore[118] = ColorNumber;
                sendtxt ='A' + Led[118] + ColorState[118];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view119:
                ColorState[119] = Colorcode;
                ColorStore[119] = ColorNumber;
                sendtxt ='A' + Led[119] + ColorState[119];
                btt.write(sendtxt.getBytes());
                break;
            case R.id.view120:
                ColorState[120] = Colorcode;
                ColorStore[120] = ColorNumber;
                sendtxt ='A' + Led[120] + ColorState[120];
                btt.write(sendtxt.getBytes());
                break;
        }
    }
    public void ConvertColor(int n) {
        if (n==0)
            Colorcode="0";
        else if (n==1)
            Colorcode="1";
        else if (n==2)
            Colorcode="2";
        else if (n==3)
            Colorcode="3";
        else if (n==4)
            Colorcode="4";
        else if (n==5)
            Colorcode="5";
        else if (n==6)
            Colorcode="6";
        else if (n==7)
            Colorcode="7";
        else if (n==8)
            Colorcode="8";
        else if (n==9)
            Colorcode="9";
        else if (n==10)
            Colorcode=":";
        else if (n==11)
            Colorcode=";";
        else if (n==12)
            Colorcode="<";
        else Colorcode=":";
    }
    static public void decodeColor(String S) {
        if (S.equals("0"))
            ColorNumber=0;
        else if (S.equals("1"))
            ColorNumber=1;
        else if (S.equals("2"))
            ColorNumber=2;
        else if (S.equals("3"))
            ColorNumber=3;
        else if (S.equals("4"))
            ColorNumber=4;
        else if (S.equals("5"))
            ColorNumber=5;
        else if (S.equals("6"))
            ColorNumber=6;
        else if (S.equals("7"))
            ColorNumber=7;
        else if (S.equals("8"))
            ColorNumber=8;
        else if (S.equals("9"))
            ColorNumber=9;
        else if (S.equals(":"))
            ColorNumber=10;
        else if (S.equals(";"))
            ColorNumber=11;
        else if (S.equals("<"))
            ColorNumber=12;
    }
    public void SelectColor(View v){
        findViewById(R.id.viewSelectColor).setBackgroundColor(((ColorDrawable) findViewById(v.getId()).getBackground()).getColor());
        switch (v.getId()){
            case R.id.viewColor0:
                ColorNumber=0;
                break;
            case R.id.viewColor1:
                ColorNumber=1;
                break;
            case R.id.viewColor2:
                ColorNumber=2;
                break;
            case R.id.viewColor3:
                ColorNumber=3;
                break;
            case R.id.viewColor4:
                ColorNumber=4;
                break;
            case R.id.viewColor5:
                ColorNumber=5;
                break;
            case R.id.viewColor6:
                ColorNumber=6;
                break;
            case R.id.viewColor7:
                ColorNumber=7;
                break;
            case R.id.viewColor8:
                ColorNumber=8;
                break;
            case R.id.viewColor9:
                ColorNumber=9;
                break;
            case R.id.viewColor10:
                ColorNumber=10;
                break;
            case R.id.viewColor11:
                ColorNumber=11;
                break;
            case R.id.viewColor12:
                ColorNumber=12;
                break;
        }
    }
    public void ClearLED(View v) {
        sendtxt = "C";
        btt.write(sendtxt.getBytes());
        for (int i=0;i<121;i++) {
            ColorState[i] = ";";
            ColorStore[i] = 11;
        }
        updateLED();
    }
    public void Connect(View v) {
        initiateBluetoothProcess();

    }
    public void Refresh(View v) {
        refresh();
    }
    public void refresh(){
        sendtxt = "R";
        btt.write(sendtxt.getBytes());
        for (int i=0;i<121;i++) {
            sendtxt = ColorState[i];
            btt.write(sendtxt.getBytes());
            try{
                Thread.sleep(20);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        try{
            Thread.sleep(1300);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    private void addData(String[] Data,String Name) {
        String dataWrite = "";
        for (int i=0;i<121;i++) {
            dataWrite += Data[i];
        }
        ContentValues cv=new ContentValues(2);
        cv.put("LED",dataWrite);
        cv.put("NAME",Name);
        db.insert(tb_name,null,cv);
    }
    public void updateLED() {
            findViewById(R.id.view0).setBackgroundColor(Colorlist[ColorStore[0]]);
            findViewById(R.id.view1).setBackgroundColor(Colorlist[ColorStore[1]]);
            findViewById(R.id.view2).setBackgroundColor(Colorlist[ColorStore[2]]);
            findViewById(R.id.view3).setBackgroundColor(Colorlist[ColorStore[3]]);
            findViewById(R.id.view4).setBackgroundColor(Colorlist[ColorStore[4]]);
            findViewById(R.id.view5).setBackgroundColor(Colorlist[ColorStore[5]]);
            findViewById(R.id.view6).setBackgroundColor(Colorlist[ColorStore[6]]);
            findViewById(R.id.view7).setBackgroundColor(Colorlist[ColorStore[7]]);
            findViewById(R.id.view8).setBackgroundColor(Colorlist[ColorStore[8]]);
            findViewById(R.id.view9).setBackgroundColor(Colorlist[ColorStore[9]]);
            findViewById(R.id.view10).setBackgroundColor(Colorlist[ColorStore[10]]);
            findViewById(R.id.view11).setBackgroundColor(Colorlist[ColorStore[11]]);
            findViewById(R.id.view12).setBackgroundColor(Colorlist[ColorStore[12]]);
            findViewById(R.id.view13).setBackgroundColor(Colorlist[ColorStore[13]]);
            findViewById(R.id.view14).setBackgroundColor(Colorlist[ColorStore[14]]);
            findViewById(R.id.view15).setBackgroundColor(Colorlist[ColorStore[15]]);
            findViewById(R.id.view16).setBackgroundColor(Colorlist[ColorStore[16]]);
            findViewById(R.id.view17).setBackgroundColor(Colorlist[ColorStore[17]]);
            findViewById(R.id.view18).setBackgroundColor(Colorlist[ColorStore[18]]);
            findViewById(R.id.view19).setBackgroundColor(Colorlist[ColorStore[19]]);
            findViewById(R.id.view20).setBackgroundColor(Colorlist[ColorStore[20]]);
            findViewById(R.id.view21).setBackgroundColor(Colorlist[ColorStore[21]]);
            findViewById(R.id.view22).setBackgroundColor(Colorlist[ColorStore[22]]);
            findViewById(R.id.view23).setBackgroundColor(Colorlist[ColorStore[23]]);
            findViewById(R.id.view24).setBackgroundColor(Colorlist[ColorStore[24]]);
            findViewById(R.id.view25).setBackgroundColor(Colorlist[ColorStore[25]]);
            findViewById(R.id.view26).setBackgroundColor(Colorlist[ColorStore[26]]);
            findViewById(R.id.view27).setBackgroundColor(Colorlist[ColorStore[27]]);
            findViewById(R.id.view28).setBackgroundColor(Colorlist[ColorStore[28]]);
            findViewById(R.id.view29).setBackgroundColor(Colorlist[ColorStore[29]]);
            findViewById(R.id.view30).setBackgroundColor(Colorlist[ColorStore[30]]);
            findViewById(R.id.view31).setBackgroundColor(Colorlist[ColorStore[31]]);
            findViewById(R.id.view32).setBackgroundColor(Colorlist[ColorStore[32]]);
            findViewById(R.id.view33).setBackgroundColor(Colorlist[ColorStore[33]]);
            findViewById(R.id.view34).setBackgroundColor(Colorlist[ColorStore[34]]);
            findViewById(R.id.view35).setBackgroundColor(Colorlist[ColorStore[35]]);
            findViewById(R.id.view36).setBackgroundColor(Colorlist[ColorStore[36]]);
            findViewById(R.id.view37).setBackgroundColor(Colorlist[ColorStore[37]]);
            findViewById(R.id.view38).setBackgroundColor(Colorlist[ColorStore[38]]);
            findViewById(R.id.view39).setBackgroundColor(Colorlist[ColorStore[39]]);
            findViewById(R.id.view40).setBackgroundColor(Colorlist[ColorStore[40]]);
            findViewById(R.id.view41).setBackgroundColor(Colorlist[ColorStore[41]]);
            findViewById(R.id.view42).setBackgroundColor(Colorlist[ColorStore[42]]);
            findViewById(R.id.view43).setBackgroundColor(Colorlist[ColorStore[43]]);
            findViewById(R.id.view44).setBackgroundColor(Colorlist[ColorStore[44]]);
            findViewById(R.id.view45).setBackgroundColor(Colorlist[ColorStore[45]]);
            findViewById(R.id.view46).setBackgroundColor(Colorlist[ColorStore[46]]);
            findViewById(R.id.view47).setBackgroundColor(Colorlist[ColorStore[47]]);
            findViewById(R.id.view48).setBackgroundColor(Colorlist[ColorStore[48]]);
            findViewById(R.id.view49).setBackgroundColor(Colorlist[ColorStore[49]]);
            findViewById(R.id.view50).setBackgroundColor(Colorlist[ColorStore[50]]);
            findViewById(R.id.view51).setBackgroundColor(Colorlist[ColorStore[51]]);
            findViewById(R.id.view52).setBackgroundColor(Colorlist[ColorStore[52]]);
            findViewById(R.id.view53).setBackgroundColor(Colorlist[ColorStore[53]]);
            findViewById(R.id.view54).setBackgroundColor(Colorlist[ColorStore[54]]);
            findViewById(R.id.view55).setBackgroundColor(Colorlist[ColorStore[55]]);
            findViewById(R.id.view56).setBackgroundColor(Colorlist[ColorStore[56]]);
            findViewById(R.id.view57).setBackgroundColor(Colorlist[ColorStore[57]]);
            findViewById(R.id.view58).setBackgroundColor(Colorlist[ColorStore[58]]);
            findViewById(R.id.view59).setBackgroundColor(Colorlist[ColorStore[59]]);
            findViewById(R.id.view60).setBackgroundColor(Colorlist[ColorStore[60]]);
            findViewById(R.id.view61).setBackgroundColor(Colorlist[ColorStore[61]]);
            findViewById(R.id.view62).setBackgroundColor(Colorlist[ColorStore[62]]);
            findViewById(R.id.view63).setBackgroundColor(Colorlist[ColorStore[63]]);
            findViewById(R.id.view64).setBackgroundColor(Colorlist[ColorStore[64]]);
            findViewById(R.id.view65).setBackgroundColor(Colorlist[ColorStore[65]]);
            findViewById(R.id.view66).setBackgroundColor(Colorlist[ColorStore[66]]);
            findViewById(R.id.view67).setBackgroundColor(Colorlist[ColorStore[67]]);
            findViewById(R.id.view68).setBackgroundColor(Colorlist[ColorStore[68]]);
            findViewById(R.id.view69).setBackgroundColor(Colorlist[ColorStore[69]]);
            findViewById(R.id.view70).setBackgroundColor(Colorlist[ColorStore[70]]);
            findViewById(R.id.view71).setBackgroundColor(Colorlist[ColorStore[71]]);
            findViewById(R.id.view72).setBackgroundColor(Colorlist[ColorStore[72]]);
            findViewById(R.id.view73).setBackgroundColor(Colorlist[ColorStore[73]]);
            findViewById(R.id.view74).setBackgroundColor(Colorlist[ColorStore[74]]);
            findViewById(R.id.view75).setBackgroundColor(Colorlist[ColorStore[75]]);
            findViewById(R.id.view76).setBackgroundColor(Colorlist[ColorStore[76]]);
            findViewById(R.id.view77).setBackgroundColor(Colorlist[ColorStore[77]]);
            findViewById(R.id.view78).setBackgroundColor(Colorlist[ColorStore[78]]);
            findViewById(R.id.view79).setBackgroundColor(Colorlist[ColorStore[79]]);
            findViewById(R.id.view80).setBackgroundColor(Colorlist[ColorStore[80]]);
            findViewById(R.id.view81).setBackgroundColor(Colorlist[ColorStore[81]]);
            findViewById(R.id.view82).setBackgroundColor(Colorlist[ColorStore[82]]);
            findViewById(R.id.view83).setBackgroundColor(Colorlist[ColorStore[83]]);
            findViewById(R.id.view84).setBackgroundColor(Colorlist[ColorStore[84]]);
            findViewById(R.id.view85).setBackgroundColor(Colorlist[ColorStore[85]]);
            findViewById(R.id.view86).setBackgroundColor(Colorlist[ColorStore[86]]);
            findViewById(R.id.view87).setBackgroundColor(Colorlist[ColorStore[87]]);
            findViewById(R.id.view88).setBackgroundColor(Colorlist[ColorStore[88]]);
            findViewById(R.id.view89).setBackgroundColor(Colorlist[ColorStore[89]]);
            findViewById(R.id.view90).setBackgroundColor(Colorlist[ColorStore[90]]);
            findViewById(R.id.view91).setBackgroundColor(Colorlist[ColorStore[91]]);
            findViewById(R.id.view92).setBackgroundColor(Colorlist[ColorStore[92]]);
            findViewById(R.id.view93).setBackgroundColor(Colorlist[ColorStore[93]]);
            findViewById(R.id.view94).setBackgroundColor(Colorlist[ColorStore[94]]);
            findViewById(R.id.view95).setBackgroundColor(Colorlist[ColorStore[95]]);
            findViewById(R.id.view96).setBackgroundColor(Colorlist[ColorStore[96]]);
            findViewById(R.id.view97).setBackgroundColor(Colorlist[ColorStore[97]]);
            findViewById(R.id.view98).setBackgroundColor(Colorlist[ColorStore[98]]);
            findViewById(R.id.view99).setBackgroundColor(Colorlist[ColorStore[99]]);
            findViewById(R.id.view100).setBackgroundColor(Colorlist[ColorStore[100]]);
            findViewById(R.id.view101).setBackgroundColor(Colorlist[ColorStore[101]]);
            findViewById(R.id.view102).setBackgroundColor(Colorlist[ColorStore[102]]);
            findViewById(R.id.view103).setBackgroundColor(Colorlist[ColorStore[103]]);
            findViewById(R.id.view104).setBackgroundColor(Colorlist[ColorStore[104]]);
            findViewById(R.id.view105).setBackgroundColor(Colorlist[ColorStore[105]]);
            findViewById(R.id.view106).setBackgroundColor(Colorlist[ColorStore[106]]);
            findViewById(R.id.view107).setBackgroundColor(Colorlist[ColorStore[107]]);
            findViewById(R.id.view108).setBackgroundColor(Colorlist[ColorStore[108]]);
            findViewById(R.id.view109).setBackgroundColor(Colorlist[ColorStore[109]]);
            findViewById(R.id.view110).setBackgroundColor(Colorlist[ColorStore[110]]);
            findViewById(R.id.view111).setBackgroundColor(Colorlist[ColorStore[111]]);
            findViewById(R.id.view112).setBackgroundColor(Colorlist[ColorStore[112]]);
            findViewById(R.id.view113).setBackgroundColor(Colorlist[ColorStore[113]]);
            findViewById(R.id.view114).setBackgroundColor(Colorlist[ColorStore[114]]);
            findViewById(R.id.view115).setBackgroundColor(Colorlist[ColorStore[115]]);
            findViewById(R.id.view116).setBackgroundColor(Colorlist[ColorStore[116]]);
            findViewById(R.id.view117).setBackgroundColor(Colorlist[ColorStore[117]]);
            findViewById(R.id.view118).setBackgroundColor(Colorlist[ColorStore[118]]);
            findViewById(R.id.view119).setBackgroundColor(Colorlist[ColorStore[119]]);
            findViewById(R.id.view120).setBackgroundColor(Colorlist[ColorStore[120]]);
    }
    public void saveLED(View v){
        EditText edit = findViewById(R.id.editText);
        addData(ColorState,edit.getText().toString());
    }
    public void LoadLED(View v) {
        Intent it = new Intent();
        it.setClass(this,Main2Activity.class);
        startActivity(it);
    }

}
