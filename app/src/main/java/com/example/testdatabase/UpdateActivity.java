package com.example.testdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testdatabase.database.UserDatabase;

public class UpdateActivity extends AppCompatActivity {
    private EditText edtUsername, edtAddress;
    private Button btnUpdateUser;

    private User mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edtUsername = findViewById(R.id.edt_username);
        edtAddress = findViewById(R.id.edt_address);
        btnUpdateUser = findViewById(R.id.btn_update_user);

        mUser = (User) getIntent().getExtras().get("object_user");
        if (mUser != null) {
            edtUsername.setText(mUser.getUsername());
            edtAddress.setText(mUser.getAddress());
        }

        btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });
    }

    //Ham update
    private void updateUser() {
        String strUsername = edtUsername.getText().toString().trim();
        String strAddress = edtAddress.getText().toString().trim();

        if (TextUtils.isEmpty(strUsername) || TextUtils.isEmpty(strAddress)) {
            return;
        }

        //Update user trong database
        mUser.setUsername(strUsername);
        mUser.setAddress(strAddress);

        UserDatabase.getInstance(this).userDao().updateUser(mUser);

        Toast.makeText(this, "Update user successfully", Toast.LENGTH_SHORT).show();

        Intent intentResult = new Intent();
        setResult(Activity.RESULT_OK, intentResult);
        finish();
    }
}