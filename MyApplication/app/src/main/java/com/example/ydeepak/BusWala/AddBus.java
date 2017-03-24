package com.example.ydeepak.BusWala;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ydeepak.BusWala.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.example.ydeepak.BusWala.GeneralInfo.busAdd;

public class AddBus extends AppCompatActivity {

    private String userId;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference userPrefDatabaseReference;
    private EditText mMessageEditText;
    private Button mSendButton;
    private int DEFAULT_MSG_LENGTH_LIMIT = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bus);
        Bundle extras = getIntent().getExtras();
        userId = extras.get("id").toString();
        Toast.makeText(this, userId, Toast.LENGTH_LONG).show();
        firebaseDatabase = FirebaseDatabase.getInstance();
        userPrefDatabaseReference = firebaseDatabase.getReference().child("userPref").child(userId);



        //*************Button Functions****************//

        mMessageEditText = (EditText) findViewById(R.id.busAddition);
        mSendButton = (Button) findViewById (R.id.addBtn);
        mSendButton.setEnabled(false);
        mMessageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mSendButton.setEnabled(true);
                } else {
                    mSendButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});

        // Send button sends a message and clears the EditText
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Send messages on click

                userPrefDatabaseReference.push().setValue(mMessageEditText.getText().toString());
                // Clear input box
                mMessageEditText.setText("");
            }
        });
    }
}
