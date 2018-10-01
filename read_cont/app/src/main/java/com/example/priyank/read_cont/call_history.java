package com.example.priyank.read_cont;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class call_history extends AppCompatActivity
{
    ListView histroy;
    List<String> h_data;
    ArrayAdapter<String> adapter;
    TextView no_history;
    String where= CallLog.Calls.NUMBER+"=?";
    String[] arg__=new String[]{data_str.u_number};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_history);
        h_data = new ArrayList<String>();
        adapter = new ArrayAdapter<>(call_history.this, android.R.layout.simple_list_item_1, h_data);
        histroy= findViewById(R.id.history);
        histroy.setAdapter(adapter);
        no_history= findViewById(R.id.no_history);
        c_details();
        /*if(h_data.size()==0)
        {
            histroy.setVisibility(View.GONE);
            no_history.setVisibility(View.VISIBLE);
        }
        else
        {
            histroy.setVisibility(View.VISIBLE);
            no_history.setVisibility(View.GONE);
        }*/

    }
    void c_details()
    {
        Cursor data = null;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        data = getContentResolver().query(CallLog.Calls.CONTENT_URI, null, where,arg__, null);
        int number = data.getColumnIndex(CallLog.Calls.NUMBER);
        int type = data.getColumnIndex(CallLog.Calls.TYPE);
        int date = data.getColumnIndex(CallLog.Calls.DATE);
        int duration = data.getColumnIndex(CallLog.Calls.DURATION);


        //CallLog.Calls.PHONE_ACCOUNT_ID


        while(data.moveToNext())
        {
                String phoneNumber = data.getString(number);
                String callType = data.getString(type);
                String callDuration = data.getString(duration);
                h_data.add("\nPhone Number:--- " + phoneNumber + "\nCall Type:--- " + "\nCall Date:---"
                        + "\nCall Duration:---" + getime_p(duration));

        }
        adapter.notifyDataSetChanged();
        data.close();
    }

    String getime_p(int time)
    {
        if(time<60)
        {
            return time+" Seconds";
        }
        if(time>60)
        {
            return time/60+" MInutes"+ time%60+" Seconds";
        }
       return null;
    }

}
/*
    String phoneNumber = data.getString(number);
            if(phoneNumber.equals(data_str.u_number))
                    {
                    String callType = data.getString(type);
                    String callDuration = data.getString(duration);
                    h_data.add("\nPhone Number:--- " + phoneNumber + "\nCall Type:--- " + "\nCall Date:---"
                    + "\nCall Duration:---" + callDuration);
                    }*/