package com.profile.profile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.profile.profile.databinding.ActivityProfileImageBinding;

import java.io.ByteArrayOutputStream;

public class ProfileImageActivity extends AppCompatActivity {
    private ActivityProfileImageBinding mBinding;
    private Uri pickedUri;
    private static final String TAG = "ProfileImageActivity";
    private Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile_image);

        Log.i(TAG, "onCreate: SharePrefImage" + SharePref.getInstance(this).getIMAGE());

        // get image from sharedprefence and set in imageView
        Glide
                .with(ProfileImageActivity.this)
                .load(SharePref.getInstance(this).getIMAGE())
                .centerCrop()
                .placeholder(R.drawable.loading_image)
                .error(R.drawable.no_image)
                .into(mBinding.ivSelectedId);


        // onBackPressed listener
        mBinding.ivBackPressedId.setOnClickListener(view -> {
            super.onBackPressed();
        });

        // get image from gallery and camera listener
        mBinding.selectPhotoButtonId.setOnClickListener(view -> {
            choseImageMethodDialogue();
        });
    }

    // checkPermission external storage
    private void choseImageMethodDialogue() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileImageActivity.this);
        builder.setTitle("Add Photo!");
        builder.setCancelable(false);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                        } else {
                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, 1);
                        }
                    }
                } else if (options[item].equals("Choose from Gallery")) {
                    openFileChooser();
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    // get image from Gallery
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 0);
    }


    // set get image from result and set in imageView
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            pickedUri = data.getData();


            Log.i(TAG, "onActivityResult: pickedUri" + pickedUri);
            SharePref.getInstance(ProfileImageActivity.this).setImage(String.valueOf(pickedUri));

            Log.i(TAG, "onActivityResult: sharedPreferenceData" + SharePref.getInstance(this).getIMAGE());

            Glide
                    .with(ProfileImageActivity.this)
                    .load(SharePref.getInstance(this).getIMAGE())
                    .centerCrop()
                    .placeholder(R.drawable.loading_image)
                    .error(R.drawable.no_image)
                    .into(mBinding.ivSelectedId);

        } else if (requestCode == 1 && resultCode == RESULT_OK) {
            try {
                imageBitmap = (Bitmap) data.getExtras().get("data");
                mBinding.ivSelectedId.setImageBitmap(imageBitmap);
                pickedUri = getImageUri(getApplicationContext(), imageBitmap);
                Log.i(TAG, "onActivityResult: pickedUri" + pickedUri);
                SharePref.getInstance(ProfileImageActivity.this).setImage(String.valueOf(pickedUri));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    // convert bitmap to uri
    private Uri getImageUri(Context applicationContext, Bitmap photo) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(applicationContext.getContentResolver(), photo, "Title", null);
        return Uri.parse(path);
    }


    // take a picture from camera
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 1);
                } else {
                    Toast.makeText(this, "Permission denied! External storage not accessible.", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}