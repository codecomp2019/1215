package com.team8.memesplaining;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.net.URI;
import java.net.URL;


public class MainActivity extends AppCompatActivity
{

	public static final int PICK_IMAGE = 1;
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
			Uri uri = null;
			if (resultData != null)
			{
				uri = resultData.getData();
				Log.i(TAG, "Uri: " + uri.toString());

				ImageView imageView = findViewById(R.id.image_view);

				getBitmapFromUri(uri);

				//Bitmap photo = (Bitmap) resultData.getExtras().get("uri");
				//imageView.setImageBitmap(photo);
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

	public void onImageGalleryClicked(View v)
	{
		Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

		File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		String pictureDirectorypath = pictureDirectory.getPath();

		Uri data = Uri.parse(pictureDirectorypath);

		photoPickerIntent.setDataAndType(data, "image/*");

		startActivityForResult(photoPickerIntent, PICK_IMAGE);
	}
}
