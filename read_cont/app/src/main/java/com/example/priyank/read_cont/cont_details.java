package com.example.priyank.read_cont;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class cont_details extends AppCompatActivity {

    TextView c_num, c_name;
    ImageButton c_but,m_but,h_but;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cont_details);

        c_num = findViewById(R.id.c_num);
        c_name = findViewById(R.id.c_name);
        c_but= findViewById(R.id.c_but);
        m_but= findViewById(R.id.s_but);
        h_but= findViewById(R.id.h_but);
        c_name.setText(data_str.u_name);
        c_num.setText(data_str.u_number);
        iv=(ImageView)findViewById(R.id.c_img);
        if(data_str.u_image!=null)
        {
            iv.setImageURI(Uri.parse(data_str.u_image));
        }
        else
        {
            iv.setBackgroundResource(R.drawable.common);
            iv.setBackgroundColor(cont_details.this.getResources().getColor(R.color.colorPrimary));
        }


        c_but.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                make_call();
            }
        });

        m_but.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

        h_but.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i=new Intent(cont_details.this,call_history.class);
                startActivity(i);
            }
        });
    }

  void make_call()
  {
      Toast.makeText(getApplicationContext(),"here 1",Toast.LENGTH_LONG).show();
      String num="tel:"+data_str.u_number;
      Intent i = new Intent(Intent.ACTION_CALL, Uri.parse(num));
      if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
          // TODO: Consider calling
          //    ActivityCompat#requestPermissions
          // here to request the missing permissions, and then overriding
          //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
          //                                          int[] grantResults)
          // to handle the case where the user grants the permission. See the documentation
          // for ActivityCompat#requestPermissions for more details.
          return;
      }
      startActivity(i);

  }
}
