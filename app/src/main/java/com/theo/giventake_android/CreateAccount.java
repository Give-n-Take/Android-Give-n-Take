package com.theo.giventake_android;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class CreateAccount extends AppCompatActivity {

    private Button CreateAccountButton;
    private EditText InputName, InputMail, InputPassword;
    private ProgressDialog loadingBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_account);

        CreateAccountButton = (Button) findViewById(R.id.create_btn);
        InputName = (EditText) findViewById(R.id.register_pseudo);
        InputMail = (EditText) findViewById(R.id.register_mail);
        InputPassword = (EditText) findViewById(R.id.register_password);
        loadingBar = new ProgressDialog(this);


        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreerCompte();
            }

            private void CreerCompte() {

                String name = InputName.getText().toString();
                String mail = InputMail.getText().toString();
                String password = InputPassword.getText().toString();

                if (TextUtils.isEmpty(name)){

                    Toast.makeText(CreateAccount.this, "Entrez votre pseudo", Toast.LENGTH_SHORT).show();
                }

                else if (TextUtils.isEmpty(mail)){

                    Toast.makeText(CreateAccount.this, "Entrez votre mail", Toast.LENGTH_SHORT).show();
                }

                else if (TextUtils.isEmpty(password)){

                    Toast.makeText(CreateAccount.this, "Entrez votre mot de passe", Toast.LENGTH_SHORT).show();
                }

                else{

                    loadingBar.setTitle("Création du compte");
                    loadingBar.setMessage("Patientez pendant que nous vérifions les informations");
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.show();

                    ValidateMail(name, mail, password);
                }

            }

            private void ValidateMail(String name, String mail, String password) {
                final DatabaseReference RootRef;
                RootRef = FirebaseDatabase.getInstance().getReference();

                RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(!(snapshot.child("Users").child(mail).exists())){

                            HashMap<String, Object> userdataMap = new HashMap<>();
                            userdataMap.put("mail", mail);
                            userdataMap.put("mot de passe", password);
                            userdataMap.put("pseudo", name);

                            RootRef.child("Users").child(mail).updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(CreateAccount.this, "Félicitations, votre compte a bien été créé !", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(CreateAccount.this, LoginActivity.class);
                                        startActivity(intent);
                                    }

                                    else {
                                        Toast.makeText(CreateAccount.this, "Erreur de connexion: réessayez plus tard", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                    }

                                }
                            });
                        }

                        else {
                            Toast.makeText(CreateAccount.this, "Cette adresse mail : " + mail + "est déjà utilisée", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                            Toast.makeText(CreateAccount.this, "Essayez avec une autre adresse e-mail", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(CreateAccount.this, MainActivity.class);
                            startActivity(intent);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull  DatabaseError error) {

                    }
                });

            }
        });
    }
}