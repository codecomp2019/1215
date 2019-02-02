package com.team8.memesplaining;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;


public class MainActivity extends AppCompatActivity
{

	public static final int PICK_IMAGE = 1;
	private static final int READ_REQUEST_CODE = 42;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

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
