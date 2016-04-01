package finalyear.bookstorepatterns;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import finalyear.bookstorepatterns.Database.DatabaseHandler;
import finalyear.bookstorepatterns.Model.Address;
import finalyear.bookstorepatterns.Model.PaymentMethod;
import finalyear.bookstorepatterns.Model.User;

public class DetailedUser extends AppCompatActivity {

    DatabaseHandler dbhandler;
    TextView customerDetailsName,customerDetailsEmail, customerAddressStreet1, customerAddressStreet2,
    customerAddressCity, customerAddressCountry, customerPaymentCardholderName, customerPaymentCardNumber,
    customerPaymentCardExpiry, customerPaymentCardCVV;
    List<User> users = new ArrayList<>();
    List<PaymentMethod> paymentMethods = new ArrayList<>();
    List<Address> addresses = new ArrayList<>();
    int userID=-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_user);
        dbhandler = new DatabaseHandler(getApplicationContext());
        userID = getUserID();

        if(dbhandler.getUsersCount() !=0) {
            users.addAll(dbhandler.getAllUsers());
            paymentMethods.addAll(dbhandler.getAllPaymentMethods());
            addresses.addAll(dbhandler.getAllAddresses());
        }
        setDetails();
        setAddress();
        setPaymentMethod();
    }

    private void setPaymentMethod() {

        PaymentMethod paymentMethod = new PaymentMethod(dbhandler.getPaymentMethodCount(), users.get(0).get_userId(), "test", "user 0", "","");
        dbhandler.createPaymentMethod(paymentMethod);
        paymentMethods.add(paymentMethod);
        PaymentMethod paymentMethod2 = new PaymentMethod(dbhandler.getPaymentMethodCount(), users.get(0).get_userId(), "test payment", "user 1", "","");
        dbhandler.createPaymentMethod(paymentMethod2);
        paymentMethods.add(paymentMethod2);

        for(int i =0; i<paymentMethods.size(); i++) {
            if (getUserID() == paymentMethods.get(i).get_userId()) {
                customerPaymentCardholderName = (TextView) findViewById(R.id.customerPaymentCardholderName);
                customerPaymentCardholderName.setText(paymentMethods.get(i).get_cardHolderName());
                customerPaymentCardNumber = (TextView) findViewById(R.id.customerPaymentCardNumber);
                customerPaymentCardNumber.setText(paymentMethods.get(i).get_cardNumber());
                customerPaymentCardExpiry = (TextView) findViewById(R.id.customerPaymentCardExpiry);
                customerPaymentCardExpiry.setText(paymentMethods.get(i).get_cardExpiry());
                customerPaymentCardCVV = (TextView) findViewById(R.id.customerPaymentCardholderCVV);
                customerPaymentCardCVV.setText(paymentMethods.get(i).get_cardCVV());
            } else  {
                customerPaymentCardholderName = (TextView) findViewById(R.id.customerPaymentCardholderName);
                customerPaymentCardholderName.setText("Customer has not added payment method");
                customerPaymentCardNumber = (TextView) findViewById(R.id.customerPaymentCardNumber);
                customerPaymentCardNumber.setText(null);
                customerPaymentCardExpiry = (TextView) findViewById(R.id.customerPaymentCardExpiry);
                customerPaymentCardExpiry.setText(null);
                customerPaymentCardCVV = (TextView) findViewById(R.id.customerPaymentCardholderCVV);
                customerPaymentCardCVV.setText(null);
            }
        }
    }

    private void setDetails() {
        for (int i = 0; i < users.size(); i++) {
            if(getUserID() == users.get(i).get_userId()) {
                customerDetailsName = (TextView) findViewById(R.id.customerDetailsName);
                customerDetailsName.setText(users.get(i).get_name());
                customerDetailsEmail = (TextView) findViewById(R.id.customerDetailsEmail);
                customerDetailsEmail.setText(users.get(i).get_email());
                break;
            }
        }
    }

    private void setAddress() {
        Address address = new Address(dbhandler.getAddressCount(), users.get(0).get_userId(), "test", "user 0", "","");
        dbhandler.createAddress(address);
        addresses.add(address);
        Address address2 = new Address(dbhandler.getAddressCount(), users.get(1).get_userId(), "test", "user 1", "","");
        dbhandler.createAddress(address2);
        addresses.add(address2);

        for(int i=0; i<addresses.size(); i++) {
            if (getUserID() == addresses.get(i).get_userId()) {
                customerAddressStreet1 = (TextView) findViewById(R.id.customerAddressStreet1);
                customerAddressStreet1.setText(addresses.get(i).get_street1());
                customerAddressStreet2 = (TextView) findViewById(R.id.customerAddressStreet2);
                customerAddressStreet2.setText(addresses.get(i).get_street2());
                customerAddressCity = (TextView) findViewById(R.id.customerAddressCity);
                customerAddressCity.setText(addresses.get(i).get_city());
                customerAddressCountry = (TextView) findViewById(R.id.customerAddressCountry);
                customerAddressCountry.setText(addresses.get(i).get_country());
            } else  {
                customerAddressStreet1 = (TextView) findViewById(R.id.customerAddressStreet1);
                customerAddressStreet1.setText("Customer has not added address");
                customerAddressStreet2 = (TextView) findViewById(R.id.customerAddressStreet2);
                customerAddressStreet2.setText(null);
                customerAddressCity = (TextView) findViewById(R.id.customerAddressCity);
                customerAddressCity.setText(null);
                customerAddressCountry = (TextView) findViewById(R.id.customerAddressCountry);
                customerAddressCountry.setText(null);
            }
        }
    }


    public int getUserID() {
        Bundle extras = getIntent().getExtras();
        int userId = extras.getInt("User_ID");
        Log.d("UserIdFromUserDetail", Integer.toString(userId));
        return  userId;
    }
}
