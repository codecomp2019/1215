package com.team8.memesplaining;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity
{
	private static final int READ_REQUEST_CODE = 42;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	/*

	 */
	public void performPhotoSearch()
	{
		Intent photoPicker = new Intent(Intent.ACTION_OPEN_DOCUMENT);
		photoPicker.addCategory(Intent.CATEGORY_OPENABLE);
		photoPicker.setType("image/*");
	}

	@Override
	public void onActivityResult (int requestCode, int resultCode, Intent resultData)
	{
		if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK)
		{
			Uri uri;
			if (resultData != null)
			{
				uri = resultData.getData();
				//Log.i(TAG, "Uri: " + uri.toString());
				//showImage(uri);
			}
		}
	}
}
