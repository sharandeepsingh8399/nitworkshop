package com.example.sharan.nitworkshop;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class contactsfragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>,AdapterView.OnItemClickListener{

    private static final String[] FROM_COLUMNS={
            Build.VERSION.SDK_INT>Build.VERSION_CODES.HONEYCOMB ? ContactsContract.Contacts.DISPLAY_NAME :
                    ContactsContract.Contacts.DISPLAY_NAME
    };

    public static final String[] PROJECTION={ContactsContract.Data._ID,
            ContactsContract.Data.DISPLAY_NAME,
            ContactsContract.Data.CONTACT_ID, ContactsContract.Data.LOOKUP_KEY};

    private static final int[] TDS={android.R.id.text1};
    ListView mcontactlist;
    long mcontactid;
    String mcontactkey;
    Uri muri;

    private SimpleCursorAdapter mcursoeadapter;

    public contactsfragment(){}

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mcontactlist=(ListView)getActivity().findViewById(R.id.listview);
        mcursoeadapter=new SimpleCursorAdapter(getActivity(),R.layout.activity_listview,null,FROM_COLUMNS,TDS,0);
        mcontactlist.setAdapter(mcursoeadapter);
        mcontactlist.setOnItemClickListener(this);
        getLoaderManager().initLoader(0,null,this);
    }



    public static final int contact_id_index=0;
    public static final int look_up_key=0;

    public static final String Selection= ContactsContract.Contacts.DISPLAY_NAME + " LIKE ?"+
    ContactsContract.CommonDataKinds.Email.ADDRESS +"LIKE ?" + ContactsContract.Data.MIMETYPE +"='"+
            ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE+"'";

    private String msearching;
    private String mselectionargs[]={msearching};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contactsfragment, container, false);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        mselectionargs[0]="%"+ msearching +"%";
        Uri contenturi=Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_FILTER_URI,Uri.encode(msearching));

        return new CursorLoader(getActivity(),
                contenturi,
                PROJECTION,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
    mcursoeadapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    mcursoeadapter.swapCursor(null);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//        Cursor cursor;
//        cursor = parent.getAdapter().getCursor();
//        cursor.moveToPosition(position);
//        mcontactid=getLong(contact_id_index);
//        mcontactkey=getString(mcontactkey);
//        muri=ContactsContract.getLookupUri();

    }
}
