package finalyear.bookstorepatterns;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import finalyear.bookstorepatterns.Database.DatabaseHandler;
import finalyear.bookstorepatterns.Model.User;

public class Register extends AppCompatActivity {

    EditText usernameET, emailET, passwordET;
    ArrayList<User> users = new ArrayList<>();
    DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        usernameET = (EditText) findViewById(R.id.textRegisterName);
        emailET = (EditText) findViewById(R.id.textRegisterEmail);
        passwordET = (EditText) findViewById(R.id.textRegisterPassword);
        dbHandler = new DatabaseHandler(getApplicationContext());

        final Button addBtn = (Button) findViewById(R.id.btnRegisterUser);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(dbHandler.getUsersCount(), String.valueOf(usernameET.getText()), String.valueOf(emailET.getText()), String.valueOf(passwordET.getText()));
                dbHandler.createUser(user);
                users.add(user);
                Toast.makeText(getApplicationContext(), usernameET.getText().toString() + " created", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Register.this, Login.class);
                Register.this.startActivity(intent);
                dbHandler.close();
            }
        });


    }

}
