package com.example.babybuy1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class Signup extends AppCompatActivity {
    EditText Name,Email,Password,cPassword;
    Button Signup,Signin;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        findId();
    }
    public void findId(){
        Name=findViewById(R.id.etName);
        Email=findViewById(R.id.etEmail);
        Password=findViewById(R.id.EtPassword);
        cPassword=findViewById(R.id.etCpassword);
        Signup=findViewById(R.id.signupBtn);
        Signin=findViewById(R.id.signinBtn);
        firebaseAuth=FirebaseAuth.getInstance();
Signin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
    }
});

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=Name.getText().toString().trim();
                String email=Email.getText().toString().trim();
                String password=Password.getText().toString().trim();
                String cpassword=cPassword.getText().toString().trim();

                if (name.isEmpty()){
                    Toast.makeText(Signup.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                }
                if (email.isEmpty()){
                    Toast.makeText(Signup.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                }
                if (password.isEmpty()){
                    Toast.makeText(Signup.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }
                if (password.equals(cpassword)){
                    firebaseAuth.createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(Signup.this, "Successful", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }else {
                                        Toast.makeText(Signup.this, "Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}