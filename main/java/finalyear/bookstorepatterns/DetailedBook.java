package finalyear.bookstorepatterns;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import finalyear.bookstorepatterns.Database.DatabaseHandler;
import finalyear.bookstorepatterns.Model.Book;
import finalyear.bookstorepatterns.Model.PurchaseHistory;
import finalyear.bookstorepatterns.Model.Review;
import finalyear.bookstorepatterns.Model.User;
import finalyear.bookstorepatterns.Patterns.SingletonUserID;

public class DetailedBook extends AppCompatActivity {
    DatabaseHandler dbHandler;
    ImageView bookImageView;
    TextView bookTitle, bookAuthor, bookPrice, bookCategory, bookQuantity;
    EditText reviewText;
    RatingBar ratingBar;
    List<Review> reviews = new ArrayList<>();
    List<Book> booksCart = new ArrayList<>();
    ListView reviewsListView;
    PurchaseHistory purchaseHistory;
    ArrayAdapter<Review> reviewAdapter;
    List<Review> indBookReview = new ArrayList<>();
    Book book;
    List<User> users = new ArrayList<>();
    String currentUserName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_book);
        bookImageView = (ImageView) findViewById(R.id.detailedBookImageView);
        reviewText = (EditText) findViewById(R.id.detailedBookReviewEditText);
        ratingBar = (RatingBar) findViewById(R.id.detailedBookRating);
        reviewsListView = (ListView) findViewById(R.id.reviewsListView);
        dbHandler = new DatabaseHandler(getApplicationContext());
//        if(dbHandler.getReviewsCount()!= 0){
//            reviews.addAll(dbHandler.getAllReviews());
//            Log.d("Add All", Integer.toString(reviews.size()));
//        }
        reviews.addAll(dbHandler.getAllReviews());
        users.addAll(dbHandler.getAllUsers());
        getBookReviews();
        getBookInfo();
        setBookDetails();
        populateReviewsList();
        setListViewHeightBasedOnChildren(reviewsListView);

        final Button btnSubmitReview = (Button) findViewById(R.id.btnSubmitReview);
        btnSubmitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Review review = new Review(dbHandler.getReviewsCount(), book.get_bookId(), SingletonUserID.getInstance().getLoggedInId(), ratingBar.getRating(), String.valueOf(reviewText.getText()));
                dbHandler.createReview(review);
                reviews.add(review);
                Toast.makeText(getApplicationContext(), "Review Posted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DetailedBook.this, DetailedBook.class);
                intent.putExtra("Book_ID", book.get_bookId());
                startActivity(intent);
            }
        });

        final Button btnBuyBook = (Button) findViewById(R.id.btnBuyBook);
        btnBuyBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book.set_quantity(book.get_quantity() - 1);
                dbHandler.updateBook(book);
                Toast.makeText(getApplicationContext(), "Your book is on the way!", Toast.LENGTH_SHORT).show();
                Date date = new Date();
                purchaseHistory = new PurchaseHistory(dbHandler.getPurchaseHistoryCount(), SingletonUserID.getInstance().getLoggedInId(), getBookID(), date);
                dbHandler.createPurchaseHistory(purchaseHistory);
                Log.d("PH TABLE SIZE", Integer.toString(dbHandler.getPurchaseHistoryCount()));
                Intent intent = new Intent(DetailedBook.this, DetailedBook.class);
                intent.putExtra("Book_ID", book.get_bookId());
                startActivity(intent);
            }
        });



    }

    private void getBookReviews() {
        for(int i=0; i<reviews.size(); i++) {
            if(getBookID() == reviews.get(i).get_bookId()) {
                int reviewId = reviews.get(i).get_reviewId();
                int bookId = reviews.get(i).get_bookId();
                int userId = reviews.get(i).get_userId();
                double rating = reviews.get(i).get_rating();
                String comment = reviews.get(i).get_comment();
                Review review = new Review(reviewId, bookId, userId, rating, comment);
                indBookReview.add(review);
            }

            Log.d("Ind Size:", Integer.toString(indBookReview.size()));

        }
    }

    private void populateReviewsList() {
        reviewAdapter = new  ReviewListAdapter();
        reviewsListView.setAdapter(reviewAdapter);
    }

    private class ReviewListAdapter extends ArrayAdapter<Review> {
        public ReviewListAdapter() {
            super(DetailedBook.this, R.layout.listview_reviews, indBookReview);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if(view == null)
                view = getLayoutInflater().inflate(R.layout.listview_reviews, parent, false);

            Review currentReview = indBookReview.get(position);
            TextView username = (TextView) view.findViewById(R.id.reviewsListViewCustomerName);
            getCurrentUser(currentReview.get_userId());
            username.setText(currentUserName);
            TextView comment = (TextView) view.findViewById(R.id.reviewsListViewCustomerComment);
            comment.setText(currentReview.get_comment());
            RatingBar ratingBar = (RatingBar) view.findViewById(R.id.reviewsListViewCustomerRating);
            String rev = Double.toString(currentReview.get_rating());
            Float float1 = Float.valueOf(rev);
            ratingBar.setRating(float1);
           return view;
        }
    }

    private void getBookInfo() {
        book = new Book(dbHandler.getBook(getBookID()).get_bookId(),dbHandler.getBook(getBookID()).get_title(), dbHandler.getBook(getBookID()).get_author(),dbHandler.getBook(getBookID()).get_price(),
                dbHandler.getBook(getBookID()).get_category(), dbHandler.getBook(getBookID()).get_quantity(), dbHandler.getBook(getBookID()).get_bookImage());
    }

    private void setBookDetails() {
        TextView bookTitle = (TextView) findViewById(R.id.detailedBookTitle);
        bookTitle.setText("Title: " + book.get_title());
        TextView bookAuthor = (TextView) findViewById(R.id.detailedBookAuthor);
        bookAuthor.setText("Author: " + book.get_author());
        TextView bookPrice = (TextView) findViewById(R.id.detailedBookPrice);
        bookPrice.setText("Price: €" + book.get_price());
        TextView bookCategory = (TextView) findViewById(R.id.detailedBookCategory);
        bookCategory.setText("Category: " + book.get_category());
        TextView bookQuantity = (TextView) findViewById(R.id.detailedBookQuantity);
        bookQuantity.setText("Stock Remaining: " + Integer.toString(book.get_quantity()));
        ImageView ivBookImage = (ImageView) findViewById(R.id.detailedBookImageView);
        ivBookImage.setImageURI(book.get_bookImage());


    }

    public int getBookID() {
        Bundle extras = getIntent().getExtras();
        int bookId = extras.getInt("Book_ID");
        Log.d("BookID CustomerMain", Integer.toString(bookId));
        return bookId;
    }

    public void getCurrentUser(int userID) {
        for(int i=0; i<users.size(); i++) {
            if(userID == users.get(i).get_userId()) {
                currentUserName = users.get(i).get_name();
                break;
            }
        }
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
