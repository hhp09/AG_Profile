package com.profile.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.Toast;

import com.profile.profile.databinding.ActivityPhoneNoBinding;

public class PhoneNoActivity extends AppCompatActivity {
    private ActivityPhoneNoBinding mBinding;
    private String stPhoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_phone_no);

        // get phoneNo from sharedpreference
        mBinding.etPhoneNumberId.setText(SharePref.getInstance(this).getPHONE());

        // onBackpressed method
        mBinding.ivBackPressedId.setOnClickListener(view -> {
            super.onBackPressed();
        });

        // update phone button
        mBinding.savePhoneNoButtonId.setOnClickListener(view -> {
            stPhoneNo = mBinding.etPhoneNumberId.getText().toString().trim();
            SharePref.getInstance(this).setPHONE(stPhoneNo);
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        });
    }
}