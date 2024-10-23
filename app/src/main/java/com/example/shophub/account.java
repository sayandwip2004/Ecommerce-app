package com.example.shophub;

import static android.app.ProgressDialog.show;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;
import java.util.concurrent.Executor;


public class account extends Fragment {
    EditText name,password;
    Button bt;
    FirebaseAuth mAuth;

    public account() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        name=view.findViewById(R.id.name);
        password=view.findViewById(R.id.password);
        bt=view.findViewById(R.id.button);
        mAuth=FirebaseAuth.getInstance();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                singUp();
            }
        });



    }
    public void singUp(){
        String getName=name.getText().toString().trim();
        String getPass=password.getText().toString().trim();
        if (getPass.isEmpty() || getName.isEmpty()) {
            Toast.makeText(getContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(getName).matches()) {
            name.setError("Please enter a valid email address");
            name.requestFocus();
            return;
        }

        if(getPass.length()<6){
            password.setError("Password must be at least 6 character long");
            return;

        }
        mAuth.createUserWithEmailAndPassword(getName, getPass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        assert user != null;
                        Toast.makeText(getContext(), "Register successful:" +user.getEmail(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Registration failed: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("RegisterActivity", "Registration failed", task.getException());
                    }
                });


    }
}