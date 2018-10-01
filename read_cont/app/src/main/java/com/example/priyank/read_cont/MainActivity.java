
package com.example.priyank.read_cont;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
{
    ListView lv;
    List<String> c_name,c_number,call_List,c_img;
    ArrayAdapter<String> adapter;
    String where= ContactsContract.CommonDataKinds.Phone.ACCOUNT_TYPE_AND_DATA_SET+"=?";
    String arg__[]=new String[]{"com.google"};
    String ord_by=ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC";
    Cursor phones;
    @Override
    protected void onCreate(Bundle savedInstanceState)
     {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         Toolbar toolbar = findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);

         c_number=new ArrayList<>();
         call_List=new ArrayList<>();
         c_name=new ArrayList<>();
         c_img=new ArrayList<>();
         adapter=new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,call_List);

         lv= findViewById(R.id.lv);
         lv.setAdapter(adapter);
         getallcontacts();
         sort_call_list();
         lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
         {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
             {
                data_str.u_name=c_name.get(i);
                data_str.u_number=c_number.get(i);
                data_str.u_image=c_img.get(i);
                Intent n_i= new Intent(MainActivity.this,cont_details.class);
                startActivity(n_i);

             }
         });
     }

    void getallcontacts()
    {
        call_List.clear();
        c_number.clear();
        phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null,ord_by);

        while (phones.moveToNext())
        {
            String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String image1=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));
            call_List.add(name+"\n"+phoneNumber);
            c_img.add(image1);
        }
        sort_call_list();
        adapter.notifyDataSetChanged();
        phones.close();

    }
    void sort_call_list()
    {
       for(String data: call_List)
        {
            String name=data;
            String number=data;
            c_name.add(name.substring(0,name.indexOf("\n")));
            number=number.substring(number.indexOf("\n")+1,number.length());
            c_number.add(number.replace(" ",""));
        }
    }

}
