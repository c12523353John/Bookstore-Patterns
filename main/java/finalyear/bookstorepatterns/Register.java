package finalyear.bookstorepatterns;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import finalyear.bookstorepatterns.Database.DatabaseHandler;
import finalyear.bookstorepatterns.Model.Address;
import finalyear.bookstorepatterns.Model.PaymentMethod;
import finalyear.bookstorepatterns.Model.User;

public class Register extends AppCompatActivity {

    EditText usernameET, emailET, passwordET, street1, street2,
    city, country, cardName, cardNumber, cardExpiry, cardCVV;
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Address> addresses = new ArrayList<>();
    ArrayList<PaymentMethod> paymentMethods = new ArrayList<>();
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
        street1 = (EditText) findViewById(R.id.textRegisterStreet1);
        street2 = (EditText) findViewById(R.id.textRegisterStreet2);
        city = (EditText) findViewById(R.id.textRegisterCity);
        country = (EditText) findViewById(R.id.textRegisterCountry);
        cardName = (EditText) findViewById(R.id.textRegisterCardHolderName);
        cardNumber = (EditText) findViewById(R.id.textRegisterCardNumber);
        cardExpiry = (EditText) findViewById(R.id.textRegisterCardExpiry);
        cardCVV = (EditText) findViewById(R.id.textRegisterCardCVV);


        dbHandler = new DatabaseHandler(getApplicationContext());

        final Button addBtn = (Button) findViewById(R.id.btnRegisterUser);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(dbHandler.getUsersCount(), String.valueOf(usernameET.getText()), String.valueOf(emailET.getText()), String.valueOf(passwordET.getText()));
                dbHandler.createUser(user);
                users.add(user);
                Address address = new Address(dbHandler.getAddressCount(), (user.get_userId()+1), String.valueOf(street1.getText()), String.valueOf(street2.getText()),
                        String.valueOf(city.getText()), String.valueOf(country.getText()));
                Log.d("User_ID: ", Integer.toString(user.get_userId()+1));
                addresses.add(address);
                dbHandler.createAddress(address);
                PaymentMethod paymentMethod = new PaymentMethod(dbHandler.getPaymentMethodCount(), (user.get_userId()+1),
                        String.valueOf(cardName.getText()), String.valueOf(cardNumber.getText()),String.valueOf(cardExpiry.getText()),String.valueOf(cardCVV.getText()));
                paymentMethods.add(paymentMethod);
                dbHandler.createPaymentMethod(paymentMethod);
                Toast.makeText(getApplicationContext(), usernameET.getText().toString() + " created", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Register.this, Login.class);
                Register.this.startActivity(intent);
                dbHandler.close();
            }
        });


    }

}
