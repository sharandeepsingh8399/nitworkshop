package com.example.sharan.nitworkshop;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.List;

public class MainActivity extends Activity {
//String[]mContactListColumns=;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);

        Fragment fragment= new contactsfragment();
        FragmentManager fm = getFragmentManager();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        fragmentTransaction.replace(R.id.fragment1, fragment);

        fragmentTransaction.commit();

    }
}
