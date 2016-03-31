package finalyear.bookstorepatterns;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import finalyear.bookstorepatterns.Database.DatabaseHandler;
import finalyear.bookstorepatterns.Model.Book;

public class AdminMain extends AppCompatActivity {

    private static final int EDIT=0, DELETE=1;

    EditText title, author, price, category, quantity;
    ImageView bookImageImgView;
    Uri bookImageUri = Uri.parse("android.resource://finalyear.bookstorepatterns/drawable/booksil.jpg");
    List<Book> books = new ArrayList<>();
    ListView booksListView;
    DatabaseHandler dbHandler;
    int longClickedItemIndex;
    ArrayAdapter<Book> bookAdapter;
    private boolean isEditMode;
    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        setUpTabs();

        title = (EditText) findViewById(R.id.bookTitle);
        author = (EditText) findViewById(R.id.bookAuthor);
        price = (EditText) findViewById(R.id.bookPrice);
        category = (EditText) findViewById(R.id.bookCategory);
        bookImageImgView = (ImageView) findViewById(R.id.imgViewBookImg);
        quantity = (EditText) findViewById(R.id.bookStockQuantity);
        booksListView = (ListView) findViewById(R.id.booksListView);
        dbHandler = new DatabaseHandler(getApplicationContext());

        registerForContextMenu(booksListView);

        booksListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                longClickedItemIndex = position;
                return false;
            }
        });

        final Button addBtn = (Button) findViewById(R.id.btnAddBook);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEditMode) {
                    Book updateBook= books.get(longClickedItemIndex);
                    updateBook.set_title(String.valueOf(title.getText()));
                    updateBook.set_author(String.valueOf(author.getText()));
                    updateBook.set_price(String.valueOf(price.getText()));
                    updateBook.set_category(String.valueOf(category.getText()));
                    updateBook.set_quantity(Integer.parseInt(String.valueOf(quantity.getText())));
                    updateBook.set_bookImage(bookImageUri);
                    dbHandler.updateBook(updateBook);
                    bookAdapter.notifyDataSetChanged();
                    TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
                    tabHost.setCurrentTab(1);
                    addBtn.setText("Add Car");
                    resetValues();
                    isEditMode = false;


                } else {
                    Book book = new Book(dbHandler.getBooksCount(), String.valueOf(title.getText()), String.valueOf(author.getText()), String.valueOf(price.getText()),
                            String.valueOf(category.getText()), Integer.parseInt(String.valueOf(quantity.getText())), bookImageUri);
                    dbHandler.createBook(book);
                    books.add(book);
                    bookAdapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), title.getText().toString() + " added", Toast.LENGTH_SHORT).show();
                    resetValues();
                }


            }
        });

        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addBtn.setEnabled(String.valueOf(title.getText()).trim().length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bookImageImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Car Image"), 1);

            }
        });

        if(dbHandler.getUsersCount() !=0) {
            books.addAll(dbHandler.getAllBooks());
        }


        populateList();





    }

    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);

        menu.setHeaderIcon(R.drawable.pencil_icon);
        menu.setHeaderTitle("Car Options");
        menu.add(Menu.NONE, EDIT, menu.NONE, "Edit Book Info");
        menu.add(Menu.NONE, DELETE, menu.NONE, "Delete Book");
    }

    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case EDIT:
                enableEditMode(books.get(longClickedItemIndex));
                break;

            case DELETE:
                dbHandler.deleteBook(books.get(longClickedItemIndex));
                books.remove(longClickedItemIndex);
                bookAdapter.notifyDataSetChanged();
                break;

        }

        return false;
    }

    public  void onActivityResult(int reqCode, int resCode, Intent data) {
        if(resCode == RESULT_OK) {
            if(reqCode == 1) {
                bookImageUri = data.getData();
                bookImageImgView.setImageURI(data.getData());
            }
        }
    }


    private void populateList() {
        bookAdapter = new BookListAdapter();
        booksListView.setAdapter(bookAdapter);
    }

    private class BookListAdapter extends ArrayAdapter<Book> {
        public BookListAdapter() {
            super(AdminMain.this, R.layout.listview_books, books);
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
            TextView price = (TextView) view.findViewById(R.id.listBookPrice);
            price.setText("€" + currentBook.get_price());
            ImageView ivBookImage = (ImageView) view.findViewById(R.id.ivBookImage);
            ivBookImage.setImageURI(currentBook.get_bookImage());
            return view;
    }
    }

    private void resetValues() {
        title.setText(null);
        author.setText(null);
        price.setText(null);
        category.setText(null);
        quantity.setText(null);
        bookImageImgView.setImageResource(R.drawable.booksil);
    }

    private void setUpTabs() {
        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Add Book");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Add Book");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Book Inventory");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Book Inventory");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Customers");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Customers");
        host.addTab(spec);

    }

    private void enableEditMode(Book book) {
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setCurrentTab(0);
        title.setText(book.get_title());
        author.setText(book.get_author());
        price.setText(book.get_price());
        category.setText(book.get_category());
        quantity.setText(Integer.toString(book.get_quantity()));
        bookImageUri = book.get_bookImage();
        bookImageImgView.setImageURI(bookImageUri);
        Button edit = (Button) findViewById(R.id.btnAddBook);
        edit.setText("Update");
        isEditMode = true;
    }

}
