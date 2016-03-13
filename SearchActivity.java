package com.example.mall_guru;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;

public class SearchActivity extends ListActivity {
	public final static String EXTRA_MESSAGE= "com.example.mall_guru.MESSAGE";
	
	private static ProductHelper dbHelper = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_message);
		
		// optional
		getListView().setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				Cursor c = (Cursor)parent.getAdapter().getItem(position);
				new AlertDialog.Builder(SearchActivity.this)
					.setMessage("You selected " + c.getString(c.getColumnIndex(Product.NAME)))
					.setNeutralButton("OK", new DialogInterface.OnClickListener() {
				           public void onClick(DialogInterface dialog, int which) {
				               // TODO
				           }
				       }).show();

				return true;
			}
		});
		dbHelper = new ProductHelper(this.getApplicationContext());
		dbHelper.add("HYPERDUNK",  "NIKE", "BLACK");
		dbHelper.add("HYPERREV",  "NIKE", "BLACK");
		dbHelper.add("FINGERTRAP",  "NIKE", "WHITE");
		dbHelper.add("FLIGHT",  "JORDAN", "RED");
		dbHelper.add("BOSTON",  "NEW BALANCE", "BLACK");
		dbHelper.add("ZERO TRAIL",  "NEW BALANCE", "BLUE");
		dbHelper.add("FRESH FOAM",  "NEW BALANCE", "WHITE");
		dbHelper.add("LEAD VILLE",  "NEW BALANCE", "BLACK");
		dbHelper.add("5.0",  "VANS", "BLACK");
		dbHelper.add("DUNKS",  "VANS", "WHITE");
		
		Intent intent = getIntent();
	    String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
	    
		setListAdapter(new SimpleCursorAdapter(
				this,
				R.layout.list_entry, 
				dbHelper.getProduct(message),  //getProduct
				new String [] { Product.NAME, Product.BRAND, Product.COLOR },
				new int[] { R.id.s_name, R.id.s_brand, R.id.s_color },
				0));
				
				
	}

}