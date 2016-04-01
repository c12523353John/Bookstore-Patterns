package finalyear.bookstorepatterns;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import finalyear.bookstorepatterns.Database.DatabaseHandler;
import finalyear.bookstorepatterns.Model.Book;
import finalyear.bookstorepatterns.Model.PaymentMethod;
import finalyear.bookstorepatterns.Model.PurchaseHistory;

public class UserTransactionHistory extends AppCompatActivity {

    List<PurchaseHistory> transactionHistories= new ArrayList<>();
    List<Book> books = new ArrayList<>();
    DatabaseHandler dbHandler;
    int userID=-1;
    ListView tHListView;
    ArrayAdapter<PurchaseHistory> tHArrayAdapter;
    List<PurchaseHistory> userHistory = new ArrayList<>();
    String bookName;
    String bookPrice;
    Book currentBook = new Book(0, null, null,null, null, 0,null );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_transaction_history);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        dbHandler = new DatabaseHandler(getApplicationContext());
        transactionHistories.addAll(dbHandler.getAllPurchasHistory());
        books.addAll(dbHandler.getAllBooks());
        tHListView = (ListView) findViewById(R.id.transactionListView);
        Date date = new Date();
        PurchaseHistory ph = new PurchaseHistory(dbHandler.getPurchaseHistoryCount(), 1,6, date);
        PurchaseHistory ph2 = new PurchaseHistory(dbHandler.getPurchaseHistoryCount(), 1,6, date);
        userHistory.add(ph);
        userHistory.add(ph2);
        getUserHistory();
        populateTransactionsList();
    }

    private void getUserHistory() {
        for(int i=0; i<transactionHistories.size(); i++) {
            if(getUserID() == transactionHistories.get(i).get_userId()) {
                int userId = transactionHistories.get(i).get_userId();
                Date date = transactionHistories.get(i).get_date();
                int bookId = transactionHistories.get(i).get_bookId();
                PurchaseHistory ph = new PurchaseHistory(dbHandler.getPurchaseHistoryCount(), userId, bookId, date);
                userHistory.add(ph);
                }

        }
    }

    private void populateTransactionsList() {
        tHArrayAdapter = new PMListAdapter();
        tHListView.setAdapter(tHArrayAdapter);
    }

    private class PMListAdapter extends ArrayAdapter<PurchaseHistory> {
        public PMListAdapter() {
            super(UserTransactionHistory.this, R.layout.listview_transactions, userHistory);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if(view == null)
                view = getLayoutInflater().inflate(R.layout.listview_transactions, parent, false);


            PurchaseHistory ph = userHistory.get(position);
            int bookId = ph.get_bookId();
            getBookInfo(bookId);
            TextView dateOfPurchase = (TextView) view.findViewById(R.id.transactionListDateOfPurchase);
            dateOfPurchase.setText("Purchase Date: " + ph.get_date().toString());
            TextView bookNameTv = (TextView) view.findViewById(R.id.transactionListBookName);
            bookNameTv.setText("Title: " + currentBook.get_title());
            TextView price = (TextView) view.findViewById(R.id.transactionListTotalPrice);
            price.setText("Price: €" + currentBook.get_price());

            return view;
        }
    }

    public int getUserID() {
        Bundle extras = getIntent().getExtras();
        int userId = extras.getInt("USER_ID");
        return  userId;
    }

    public void getBookInfo(int bookIdInfo) {

        int bookInfoId = bookIdInfo;

        for(int i=0; i<books.size(); i++) {
            Log.d("BookId: ", Integer.toString(books.get(i).get_bookId()));
            if(bookInfoId == books.get(i).get_bookId()) {
                String bookTitle = books.get(i).get_title();
                String bookPrice = books.get(i).get_price();
                currentBook.set_bookId(bookInfoId);
                currentBook.set_title(bookTitle);
                currentBook.set_price(bookPrice);
                break;
            }

        }
    }

}
