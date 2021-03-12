package com.profile.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.Toast;

import com.profile.profile.databinding.ActivityDescriptionBinding;


public class DescriptionActivity extends AppCompatActivity {
    private ActivityDescriptionBinding mBinding;
    private String stDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_description);

        // get image from sharedpreference
        mBinding.etDescriptionId.setText(SharePref.getInstance(this).getDESCRIPTION());

        // onBackpressed method
        mBinding.ivBackPressedId.setOnClickListener(view -> {
            super.onBackPressed();
        });

        // update description button
        mBinding.saveDescriptionButtonId.setOnClickListener(view -> {
            stDescription = mBinding.etDescriptionId.getText().toString().trim();
            SharePref.getInstance(this).setDESCRIPTION(stDescription);
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        });
    }
}