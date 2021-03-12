package com.profile.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.Toast;

import com.profile.profile.databinding.ActivityNameBinding;


public class NameActivity extends AppCompatActivity {
    private ActivityNameBinding mBinding;
    private String stFirstName, stLastName;
    private static final String TAG = "NameActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_name);
        // get name from sharedpreference
        mBinding.etFirstNameId.setText(SharePref.getInstance(this).getNAME());
        mBinding.etLastNameId.setText(SharePref.getInstance(this).getLAST_NAME());

        // onBackpressed method
        mBinding.ivBackPressedId.setOnClickListener(view -> {
            super.onBackPressed();
        });

        // update name button

        mBinding.saveNameButtonId.setOnClickListener(view -> {
            stFirstName = mBinding.etFirstNameId.getText().toString().trim();
            stLastName = mBinding.etLastNameId.getText().toString().trim();
            SharePref.getInstance(NameActivity.this).setNAME(stFirstName);
            SharePref.getInstance(NameActivity.this).setLAST_NAME(stLastName);
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        });
    }
}