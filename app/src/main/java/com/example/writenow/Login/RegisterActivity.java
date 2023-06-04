package com.example.writenow.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.writenow.MainActivity;
import com.example.writenow.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mFirebashAuth;
    private DatabaseReference mDatabaseRef;

    private EditText editID, editPW;
    private Button btnsign;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebashAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("WriteNow");

//        editname = findViewById(R.id.editname);
        editID = findViewById(R.id.editID);
        editPW = findViewById(R.id.editpw);
//        editbirth1 = findViewById(R.id.editbirth1);
//        editbirth2 = findViewById(R.id.editbirth2);
//        editbirth3 = findViewById(R.id.editbirth3);

        btnsign = findViewById(R.id.signupbutton);

        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String strName = editname.getText().toString();
                String strID = editID.getText().toString();
                String strPW = editPW.getText().toString();
//                String strBirth1 = editbirth1.getText().toString();
//                String strBirth2 = editbirth1.getText().toString();
//                String strBirth3 = editbirth1.getText().toString();

                mFirebashAuth.createUserWithEmailAndPassword(strID, strPW).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = mFirebashAuth.getCurrentUser();
                            UserAccount account = new UserAccount();
                            account.setIdToken((firebaseUser.getUid()));
                            account.setID(firebaseUser.getEmail());
                            account.setPW(strPW);

                            mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);

                            Toast.makeText(RegisterActivity.this, "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(RegisterActivity.this, "회원가입에 실패하셨습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}