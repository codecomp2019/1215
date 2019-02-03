package com.team8.memesplaining;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

public class MainActivity extends AppCompatActivity
{

	private static final int READ_REQUEST_CODE = 42;
	private static final String TAG = "MainActivity";
	private static final int REQUEST_PERMISSION = 1;


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
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK)
		{
			Uri uri;
			if (data != null)
			{
				uri = data.getData();
				Log.i(TAG, "Uri: " + uri.toString());

				ImageView imgView = findViewById(R.id.image_view);
				imgView.setImageURI(uri);

				try
				{
					Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
					FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);
					Log.i(TAG, "FirebaseVisionImage " + image);

					recognizeText(image);

				} catch (IOException e)
				{
					e.printStackTrace();
				}

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

	private void recognizeText(FirebaseVisionImage image)
	{

		FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance()
						.getOnDeviceTextRecognizer();

		Task<FirebaseVisionText> result =
						detector.processImage(image)
										.addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>()
										{
											@Override
											public void onSuccess(FirebaseVisionText firebaseVisionText)
											{
												// Task completed successfully
												// [START_EXCLUDE]
												// [START get_text]

												for (FirebaseVisionText.TextBlock block : firebaseVisionText.getTextBlocks())
												{
													Rect boundingBox = block.getBoundingBox();
													Point[] cornerPoints = block.getCornerPoints();
													String text = block.getText();

													for (FirebaseVisionText.Line line : block.getLines())
													{
														// ...
														for (FirebaseVisionText.Element element : line.getElements())
														{
															// ...
															Log.i(TAG, "text " + text);
														}
													}
												}
											}
										})
										.addOnFailureListener(
														new OnFailureListener()
														{
															@Override
															public void onFailure(@NonNull Exception e)
															{
															}
														});

	}
/*
	private void requestReadFilePermission()
	{
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
						&& ContextCompat.checkSelfPermission(mView.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
		{
			ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
		} else
		{
			//permission is already granted - do what you need
		}
	}
	*/
/*
	@Override
	public void onRequestPermissionsResult(int requestCode,
	                                       String permissions[], int[] grantResults) {
		switch (requestCode) {
			case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
				// If request is cancelled, the result arrays are empty.
				if (grantResults.length > 0
								&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					// permission was granted, yay! Do the
					// contacts-related task you need to do.
				} else {
					// permission denied, boo! Disable the
					// functionality that depends on this permission.
				}
				return;
			}

			// other 'case' lines to check for other
			// permissions this app might request.
		}
	}
	*/
}
