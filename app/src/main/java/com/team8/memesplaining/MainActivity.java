package com.team8.memesplaining;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

	Button memeSelect;
	Button memeProcess;
	ImageView imageView;
	Bitmap mBitmap;
	private static int RESULT_LOAD_MEME = 1;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Associate appropriate layout buttons.
		memeSelect = (Button) findViewById(R.id.button_select_meme);
		memeProcess = (Button) findViewById(R.id.button_process_meme);
		imageView = findViewById(R.id.image_view);

		mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_background);

		// Click listener for the Select Meme button.
		memeSelect.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.d("Button: ", "Select meme clicked");
				Intent i = new Intent(
				Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(i,RESULT_LOAD_MEME);
			}
		});

		// Click listener for the Process Meme button


		memeProcess.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Log.d("Button: ", "Process meme clicked");
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == RESULT_LOAD_MEME && resultCode == RESULT_OK && null != data) {
			Uri selectedImage = data.getData();
			Log.d("Image URI: ",selectedImage.toString());
			String[] filePathColumn = {MediaStore.Images.Media.DATA};

			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
					cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();

			ImageView imageView = (ImageView) findViewById(R.id.image_view);
			imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
			Log.d("Image: ", "image set to imageView");

		}


	}
}


