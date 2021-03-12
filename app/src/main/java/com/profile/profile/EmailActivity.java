package com.profile.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.Toast;

import com.profile.profile.databinding.ActivityEmailBinding;

public class EmailActivity extends AppCompatActivity {
    private ActivityEmailBinding mBinding;
    private String stEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_email);

        // get email from sharedpreference
        mBinding.etEmailId.setText(SharePref.getInstance(this).getEMAIL());

        // onBackpressed method
        mBinding.ivBackPressedId.setOnClickListener(view -> {
            super.onBackPressed();
        });

        // update email button
        mBinding.saveEmailButtonId.setOnClickListener(view -> {
            stEmail = mBinding.etEmailId.getText().toString().trim();
            SharePref.getInstance(this).setEMAIL(stEmail);
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        });
    }
}