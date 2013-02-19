package com.makksi.androtest00;

import android.os.Bundle;
import android.app.ListActivity;
import android.net.Uri;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract.Contacts;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.CursorAdapter;



public class ContactList extends ListActivity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uri uri = Contacts.CONTENT_URI;
        String[] projection = {Contacts._ID, Contacts.DISPLAY_NAME}; // {..} un array di due stringhe: id, nome
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = Contacts.DISPLAY_NAME + " ASC";
        Cursor cursor = managedQuery(uri, projection,selection,selectionArgs,sortOrder);
        setListAdapter(new CursorAdapter(this,cursor,true){
        	@Override
        	public View newView(Context context, Cursor cursor, ViewGroup parent){
        		String displayName = cursor.getString(1);
        		TextView textView = new TextView(context);
        		textView.setText(displayName);
        		return textView;
        	}
        	@Override
        	public void bindView(View view, Context context, Cursor cursor){
        		String displayName = cursor.getString(1);
        		TextView textView = (TextView) view;
        		textView.setText(displayName);
        	}
        });
    }
}
