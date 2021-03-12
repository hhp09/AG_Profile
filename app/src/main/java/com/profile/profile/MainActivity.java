package com.profile.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.profile.profile.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    private static final String TAG = "MainActivity";
    private String stFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        // all text intent
        mBinding.ivGetProfileId.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, ProfileImageActivity.class));
        });
        mBinding.etNameId.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, NameActivity.class));
        });
        mBinding.etPhoneNoId.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, PhoneNoActivity.class));
        });
        mBinding.etEmailId.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, EmailActivity.class));
        });
        mBinding.etDescriptionId.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, DescriptionActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        stFullName = SharePref.getInstance(this).getNAME() + SharePref.getInstance(this).getLAST_NAME();

        Log.i(TAG, "onCreate: stFullName" + stFullName);
        mBinding.etNameId.setText(stFullName);

        mBinding.etPhoneNoId.setText(SharePref.getInstance(this).getPHONE());
        mBinding.etEmailId.setText(SharePref.getInstance(this).getEMAIL());
        mBinding.etDescriptionId.setText(SharePref.getInstance(this).getDESCRIPTION());


        Log.i(TAG, "onResume: SharedPrefImage" + SharePref.getInstance(this).getIMAGE());
        Glide
                .with(MainActivity.this)
                .load(SharePref.getInstance(this).getIMAGE())
                .centerCrop()
                .placeholder(R.drawable.loading_image)
                .error(R.drawable.no_image)
                .into(mBinding.circleImageViewId);

    }
}