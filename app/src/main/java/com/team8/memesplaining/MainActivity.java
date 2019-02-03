package com.team8.memesplaining;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{

	private static final int READ_REQUEST_CODE = 42;
	private static final String TAG = "MainActivity";


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button button = findViewById(R.id.button_select_meme);
		button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				performFileSearch();
			}
		});
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent resultData)
	{
		if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK)
		{
			Uri uri;
			if (resultData != null)
			{
				uri = resultData.getData();
				Log.i(TAG, "Uri: " + uri.toString());

			}
		}
	}

	public void performFileSearch()
	{
		Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		intent.setType("image/*");
		startActivityForResult(intent, READ_REQUEST_CODE);
	}
}
