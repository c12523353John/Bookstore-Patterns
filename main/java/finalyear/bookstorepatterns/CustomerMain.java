package finalyear.bookstorepatterns;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import finalyear.bookstorepatterns.Database.DatabaseHandler;
import finalyear.bookstorepatterns.Model.Book;

public class CustomerMain extends AppCompatActivity {

    List<Book> books = new ArrayList<>();
    ListView booksListView;
    DatabaseHandler dbHandler;
    int clickedItemIndex;
    ArrayAdapter<Book> bookAdapter;
    TabHost tabHost;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);
        setUpTabs();

        booksListView = (ListView) findViewById(R.id.customerBooksListView);
        dbHandler = new DatabaseHandler(getApplicationContext());

        if(dbHandler.getUsersCount() !=0) {
            books.addAll(dbHandler.getAllBooks());
        }

        populateBookList();


        booksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CustomerMain.this, DetailedBook.class);
                Book book = books.get(position);
                intent.putExtra("Book_ID", book.get_bookId());
                startActivity(intent);
            }
        });


    }

    private void populateBookList() {
        bookAdapter = new BookListAdapter();
        booksListView.setAdapter(bookAdapter);
    }

    private class BookListAdapter extends ArrayAdapter<Book> {
        public BookListAdapter() {
            super(CustomerMain.this, R.layout.listview_books, books);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if(view == null)
                view = getLayoutInflater().inflate(R.layout.listview_books, parent, false);

            Book currentBook = books.get(position);
            TextView title = (TextView) view.findViewById(R.id.listBookTitle);
            title.setText(currentBook.get_title());
            TextView author = (TextView) view.findViewById(R.id.listBookAuthor);
            author.setText(currentBook.get_author());
            TextView quantity = (TextView) view.findViewById(R.id.listBookQuantity);
            quantity.setText("Stock Level: " + currentBook.get_quantity());
            ImageView ivBookImage = (ImageView) view.findViewById(R.id.ivBookImage);
            ivBookImage.setImageURI(currentBook.get_bookImage());
            return view;
        }
    }

    private void setUpTabs() {
        TabHost host = (TabHost)findViewById(R.id.customerTabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Books Available");
        spec.setContent(R.id.customerTab1);
        spec.setIndicator("Books Available");
        host.addTab(spec);
//
//        //Tab 2
//        spec = host.newTabSpec("Book Inventory");
//        spec.setContent(R.id.customerTab2);
//        spec.setIndicator("Book Inventory");
//        host.addTab(spec);
    }
}
