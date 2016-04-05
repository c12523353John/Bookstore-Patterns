package finalyear.bookstorepatterns;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import finalyear.bookstorepatterns.Database.DatabaseHandler;
import finalyear.bookstorepatterns.Model.User;
import finalyear.bookstorepatterns.Patterns.SingletonUserID;

public class Login extends AppCompatActivity {

    Boolean exist = false;
    EditText emailET, passwordET;
    ArrayList<User> users = new ArrayList<>();
    DatabaseHandler dbHandler;
    public int loggedInID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailET = (EditText) findViewById(R.id.textLoginEmail);
        passwordET = (EditText) findViewById(R.id.textLoginPassword);
        dbHandler = new DatabaseHandler(getApplicationContext());

        users.addAll(dbHandler.getAllUsers());

        final Button addBtn = (Button) findViewById(R.id.btnLogin);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emailET.getText().toString().trim().equalsIgnoreCase("admin") && passwordET.getText().toString().trim().equalsIgnoreCase("admin")) {
                    Intent intent = new Intent(Login.this, AdminMain.class);
                    startActivity(intent);
                } else findIfExists(emailET.getText().toString(), passwordET.getText().toString());
            }
        });
    }

    public void findIfExists(String email, String password) {

        for(int i=0; i< users.size(); i++) {
            if(this.users.get(i).get_email().equalsIgnoreCase(email) && this.users.get(i).get_password().equalsIgnoreCase(password)) {
                exist = true;
                loggedInID = this.users.get(i).get_userId();
                SingletonUserID.getInstance().setLoggedInId(loggedInID);
                Log.d("Logged in Login", Integer.toString(SingletonUserID.getInstance().getLoggedInId()));
                break;
            }
        }

        if(exist) {
            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Login.this, CustomerMain.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Login Unsuccessful", Toast.LENGTH_SHORT).show();
        }
    }


}
