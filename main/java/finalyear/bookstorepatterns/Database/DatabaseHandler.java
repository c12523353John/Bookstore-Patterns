package finalyear.bookstorepatterns.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ParseException;
import android.net.Uri;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import finalyear.bookstorepatterns.Model.Address;
import finalyear.bookstorepatterns.Model.Book;
import finalyear.bookstorepatterns.Model.PaymentMethod;
import finalyear.bookstorepatterns.Model.PurchaseHistory;
import finalyear.bookstorepatterns.Model.Review;
import finalyear.bookstorepatterns.Model.User;

/**
 * Created by College on 30/03/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 5;

    private static final String DATABASE_NAME = "bookstorePatterns2",

    TABLE_USERS = "users",
    KEY_USER_ID = "userId",
    KEY_USER_NAME = "userName",
    KEY_USER_EMAIL = "userEmail",
    KEY_USER_PASSWORD = "userPassword",

    TABLE_ADDRESS = "addresses",
    KEY_ADDRESS_ID = "addressId",
    KEY_ADDRESS_USER_ID = "userId",
    KEY_ADDRESS_STREET1 = "street1",
    KEY_ADDRESS_STREET2 = "street2",
    KEY_ADDRESS_CITY = "city",
    KEY_ADDRESS_COUNTRY = "country",

    TABLE_REVIEW = "reviews",
    KEY_REVIEW_ID = "reviewId",
    KEY_REVIEW_BOOK_ID = "bookId",
    KEY_REVIEW_USER_ID = "userId",
    KEY_REVIEW_RATING = "rating",
    KEY_REVIEW_COMMENT = "comment",


    TABLE_PAYMENT_METHOD = "paymentMethods",
    KEY_PAYMENT_METHOD_ID = "paymentMethodId",
    KEY_PAYMENT_METHOD_USER_ID = "userId",
    KEY_PAYMENT_METHOD_CARD_HOLDER_NAME = "cardHolderName",
    KEY_PAYMENT_METHOD_CARD_NUMBER = "cardNumber",
    KEY_PAYMENT_METHOD_CARD_EXPIRY = "cardExpiry",
    KEY_PAYMENT_METHOD_CARD_CVV = "cardCVV",

    TABLE_PURCHASE_HISTORY = "purchaseHistory",
    KEY_PURCHASE_HISTORY_ID = "purchaseHistoryId",
    KEY_PURCHASE_HISTORY_USER_ID = "userId",
    KEY_PURCHASE_HISTORY_BOOK_ID = "bookId",
    KEY_PURCHASE_HISTORY_DATE = "date",

    TABLE_BOOKS = "books",
    KEY_BOOK_ID = "bookId",
    KEY_BOOK_TITLE = "bookTitle",
    KEY_BOOK_AUTHOR = "bookAuthor",
    KEY_BOOK_PRICE = "bookPrice",
    KEY_BOOK_CATEGORY = "bookCategory",
    KEY_BOOK_QUANTITY = "bookQuantity",
    KEY_BOOK_IMAGE = "bookImage";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USERS + "(" + KEY_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_USER_NAME + " TEXT," + KEY_USER_EMAIL + " TEXT," + KEY_USER_PASSWORD + " TEXT" + ")");
        db.execSQL("CREATE TABLE " + TABLE_ADDRESS + "(" + KEY_ADDRESS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_ADDRESS_USER_ID + " TEXT," + KEY_ADDRESS_STREET1 + " TEXT," + KEY_ADDRESS_STREET2 + " TEXT," + KEY_ADDRESS_CITY + " TEXT," + KEY_ADDRESS_COUNTRY + " TEXT" + ")");
        db.execSQL("CREATE TABLE " + TABLE_REVIEW + "(" + KEY_REVIEW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_REVIEW_BOOK_ID + " TEXT," + KEY_REVIEW_USER_ID + " TEXT," + KEY_REVIEW_RATING + " TEXT," +  KEY_REVIEW_COMMENT + " TEXT" + ")");
        db.execSQL("CREATE TABLE " + TABLE_PAYMENT_METHOD + "(" + KEY_PAYMENT_METHOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_PAYMENT_METHOD_USER_ID + " TEXT," + KEY_PAYMENT_METHOD_CARD_HOLDER_NAME + " TEXT," + KEY_PAYMENT_METHOD_CARD_NUMBER + " TEXT," + KEY_PAYMENT_METHOD_CARD_EXPIRY + " TEXT," + KEY_PAYMENT_METHOD_CARD_CVV + " TEXT" + ")");
        db.execSQL("CREATE TABLE " + TABLE_PURCHASE_HISTORY + "(" + KEY_PURCHASE_HISTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_PURCHASE_HISTORY_USER_ID + " TEXT," + KEY_PURCHASE_HISTORY_BOOK_ID + " TEXT," + KEY_PURCHASE_HISTORY_DATE + " TEXT" + ")");
        db.execSQL("CREATE TABLE " + TABLE_BOOKS + "(" + KEY_BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_BOOK_TITLE + " TEXT," + KEY_BOOK_AUTHOR + " TEXT," + KEY_BOOK_PRICE + " TEXT," + KEY_BOOK_CATEGORY + " TEXT," + KEY_BOOK_QUANTITY + " TEXT," + KEY_BOOK_IMAGE + " TEXT" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDRESS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REVIEW);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAYMENT_METHOD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PURCHASE_HISTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        db.execSQL("CREATE TABLE " + TABLE_USERS + "(" + KEY_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_USER_NAME + " TEXT," + KEY_USER_EMAIL + " TEXT," + KEY_USER_PASSWORD + " TEXT" + ")");
        db.execSQL("CREATE TABLE " + TABLE_ADDRESS + "(" + KEY_ADDRESS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_ADDRESS_USER_ID + " TEXT," + KEY_ADDRESS_STREET1 + " TEXT," + KEY_ADDRESS_STREET2 + " TEXT," + KEY_ADDRESS_CITY + " TEXT," + KEY_ADDRESS_COUNTRY + " TEXT" + ")");
        db.execSQL("CREATE TABLE " + TABLE_REVIEW + "(" + KEY_REVIEW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_REVIEW_BOOK_ID + " TEXT," + KEY_REVIEW_USER_ID + " TEXT," + KEY_REVIEW_RATING + " TEXT," +  KEY_REVIEW_COMMENT + " TEXT" + ")");
        db.execSQL("CREATE TABLE " + TABLE_PAYMENT_METHOD + "(" + KEY_PAYMENT_METHOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_PAYMENT_METHOD_USER_ID + " TEXT," + KEY_PAYMENT_METHOD_CARD_HOLDER_NAME + " TEXT," + KEY_PAYMENT_METHOD_CARD_NUMBER + " TEXT," + KEY_PAYMENT_METHOD_CARD_EXPIRY + " TEXT," + KEY_PAYMENT_METHOD_CARD_CVV + " TEXT" + ")");
        db.execSQL("CREATE TABLE " + TABLE_PURCHASE_HISTORY + "(" + KEY_PURCHASE_HISTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_PURCHASE_HISTORY_USER_ID + " TEXT," + KEY_PURCHASE_HISTORY_BOOK_ID + " TEXT," + KEY_PURCHASE_HISTORY_DATE + " TEXT" + ")");
        db.execSQL("CREATE TABLE " + TABLE_BOOKS + "(" + KEY_BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_BOOK_TITLE + " TEXT," + KEY_BOOK_AUTHOR + " TEXT," + KEY_BOOK_PRICE + " TEXT," + KEY_BOOK_CATEGORY + " TEXT," + KEY_BOOK_QUANTITY + " TEXT," + KEY_BOOK_IMAGE + " TEXT" + ")");



    }


    /**
     * CREATE OBJECTS AND INSERT INTO DATABASE
     */

    public void createUser(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, user.get_name());
        values.put(KEY_USER_EMAIL, user.get_email());
        values.put(KEY_USER_PASSWORD, user.get_password());
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public void createAddress(Address address) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ADDRESS_USER_ID, address.get_userId());
        values.put(KEY_ADDRESS_STREET1, address.get_street1());
        values.put(KEY_ADDRESS_STREET2, address.get_street2());
        values.put(KEY_ADDRESS_CITY, address.get_city());
        values.put(KEY_ADDRESS_COUNTRY, address.get_country());
        db.insert(TABLE_ADDRESS, null, values);
        db.close();
    }

    public void createReview(Review review) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_REVIEW_BOOK_ID, review.get_bookId());
        values.put(KEY_REVIEW_USER_ID, review.get_userId());
        values.put(KEY_REVIEW_RATING, review.get_rating());
        values.put(KEY_REVIEW_COMMENT, review.get_comment());
        db.insert(TABLE_REVIEW, null, values);
        db.close();
    }

    public void createPaymentMethod(PaymentMethod paymentMethod) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PAYMENT_METHOD_USER_ID, paymentMethod.get_userId());
        values.put(KEY_PAYMENT_METHOD_CARD_HOLDER_NAME, paymentMethod.get_cardHolderName());
        values.put(KEY_PAYMENT_METHOD_CARD_NUMBER, paymentMethod.get_cardNumber());
        values.put(KEY_PAYMENT_METHOD_CARD_EXPIRY, paymentMethod.get_cardExpiry());
        values.put(KEY_PAYMENT_METHOD_CARD_CVV, paymentMethod.get_cardCVV());
        db.insert(TABLE_PAYMENT_METHOD, null, values);
        db.close();
    }

    public void createPurchaseHistory(PurchaseHistory purchaseHistory) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PURCHASE_HISTORY_USER_ID, purchaseHistory.get_userId());
        values.put(KEY_PURCHASE_HISTORY_BOOK_ID, purchaseHistory.get_bookId());
        values.put(KEY_PURCHASE_HISTORY_DATE, "'" + String.valueOf(purchaseHistory.get_date()) + "'");
        db.insert(TABLE_PURCHASE_HISTORY, null, values);
        db.close();
    }

    public void createBook(Book book) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_BOOK_TITLE, book.get_title());
        values.put(KEY_BOOK_AUTHOR, book.get_author());
        values.put(KEY_BOOK_PRICE, book.get_price());
        values.put(KEY_BOOK_CATEGORY, book.get_category());
        values.put(KEY_BOOK_QUANTITY, book.get_quantity());
        values.put(KEY_BOOK_IMAGE, book.get_bookImage().toString());
        db.insert(TABLE_BOOKS, null, values);
        db.close();
    }


    /**
     * DELETE OBJECTS AND REMOVE FROM DATABASE
     */

    //Delete a User and any associated objects.
    public void deleteUser(User user) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_USERS, KEY_USER_ID + "=?", new String[]{String.valueOf(user.get_userId())});
        db.delete(TABLE_ADDRESS, KEY_ADDRESS_USER_ID + "=?", new String[]{String.valueOf(user.get_userId())});
        db.delete(TABLE_REVIEW, KEY_REVIEW_USER_ID + "=?", new String[]{String.valueOf(user.get_userId())});
        db.delete(TABLE_PAYMENT_METHOD, KEY_PAYMENT_METHOD_USER_ID + "=?", new String[]{String.valueOf(user.get_userId())});
        db.delete(TABLE_PURCHASE_HISTORY, KEY_PURCHASE_HISTORY_USER_ID + "=?", new String[]{String.valueOf(user.get_userId())});
        db.close();
    }

    public void deleteAddress(Address address) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_ADDRESS, KEY_ADDRESS_ID + "=?", new String[]{String.valueOf(address.get_addressId())});
        db.close();
    }

    public void deleteReview(Review review) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_REVIEW, KEY_REVIEW_ID + "=?", new String[]{String.valueOf(review.get_reviewId())});
        db.close();
    }

    public void deletePaymentMethod(PaymentMethod paymentMethod) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_PAYMENT_METHOD, KEY_PAYMENT_METHOD_ID + "=?", new String[]{String.valueOf(paymentMethod.get_paymentMethodId())});
        db.close();
    }

    public void deletePurchaseHistory(PurchaseHistory purchaseHistory) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_PURCHASE_HISTORY, KEY_PURCHASE_HISTORY_ID + "=?", new String[]{String.valueOf(purchaseHistory.get_purchaseHistoryId())});
        db.close();
    }

    //Delete Book and associated reviews.
    public void deleteBook(Book book) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_BOOKS, KEY_BOOK_ID + "=?", new String[]{String.valueOf(book.get_bookId())});
        db.delete(TABLE_REVIEW, KEY_REVIEW_BOOK_ID  + "=?", new String[]{String.valueOf(book.get_bookId())});
        db.close();
    }


    /**
     * UPDATE OBJECTS AND UPDATE IN DATABASE
     */

    public int updateUser(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, user.get_name());
        values.put(KEY_USER_EMAIL, user.get_email());
        values.put(KEY_USER_PASSWORD, user.get_password());
        int rowsAffected = db.update(TABLE_USERS, values, KEY_USER_ID + "=?", new String[]{String.valueOf(user.get_userId())});
        db.close();
        return rowsAffected;
    }

    public int updateAddress(Address address) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ADDRESS_USER_ID, address.get_userId());
        values.put(KEY_ADDRESS_STREET1, address.get_street1());
        values.put(KEY_ADDRESS_STREET2, address.get_street2());
        values.put(KEY_ADDRESS_CITY, address.get_city());
        values.put(KEY_ADDRESS_COUNTRY, address.get_country());
        int rowsAffected = db.update(TABLE_ADDRESS, values, KEY_ADDRESS_ID + "=?", new String[]{String.valueOf(address.get_addressId())});
        db.close();
        return rowsAffected;
    }

    public int updateReview(Review review) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_REVIEW_BOOK_ID, review.get_bookId());
        values.put(KEY_REVIEW_USER_ID, review.get_userId());
        values.put(KEY_REVIEW_RATING, review.get_rating());
        values.put(KEY_REVIEW_COMMENT, review.get_comment());
        int rowsAffected = db.update(TABLE_REVIEW, values, KEY_REVIEW_ID + "=?", new String[]{String.valueOf(review.get_reviewId())});
        db.close();
        return rowsAffected;
    }

    public int updatePaymentMethod(PaymentMethod paymentMethod) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PAYMENT_METHOD_USER_ID, paymentMethod.get_userId());
        values.put(KEY_PAYMENT_METHOD_CARD_HOLDER_NAME, paymentMethod.get_cardHolderName());
        values.put(KEY_PAYMENT_METHOD_CARD_NUMBER, paymentMethod.get_cardNumber());
        values.put(KEY_PAYMENT_METHOD_CARD_EXPIRY, paymentMethod.get_cardExpiry());
        values.put(KEY_PAYMENT_METHOD_CARD_CVV, paymentMethod.get_cardCVV());
        int rowsAffected = db.update(TABLE_PAYMENT_METHOD, values, KEY_PAYMENT_METHOD_ID + "=?", new String[]{String.valueOf(paymentMethod.get_paymentMethodId())});
        db.close();
        return rowsAffected;
    }

    public int updatePurchaseHistory(PurchaseHistory purchaseHistory){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PURCHASE_HISTORY_USER_ID, purchaseHistory.get_userId());
        values.put(KEY_PURCHASE_HISTORY_BOOK_ID, purchaseHistory.get_bookId());
        values.put(KEY_PURCHASE_HISTORY_DATE, String.valueOf(purchaseHistory.get_date()));
        int rowsAffected = db.update(TABLE_PURCHASE_HISTORY, values, KEY_PURCHASE_HISTORY_ID + "=?", new String[]{String.valueOf(purchaseHistory.get_purchaseHistoryId())});
        db.close();
        return rowsAffected;
    }

    public int updateBook(Book book) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_BOOK_TITLE, book.get_title());
        values.put(KEY_BOOK_AUTHOR, book.get_author());
        values.put(KEY_BOOK_PRICE, book.get_price());
        values.put(KEY_BOOK_CATEGORY, book.get_category());
        values.put(KEY_BOOK_QUANTITY, book.get_quantity());
        values.put(KEY_BOOK_IMAGE, book.get_bookImage().toString());
        int rowsAffected = db.update(TABLE_BOOKS, values, KEY_BOOK_ID + "=?", new String[]{String.valueOf(book.get_bookId())});
        db.close();
        return rowsAffected;

    }

    /**
     * READ INDIVIDUAL OBJECTS FROM DATABASE
     */

    public User getUser(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[] {KEY_USER_ID,KEY_USER_NAME, KEY_USER_EMAIL, KEY_USER_PASSWORD},
                KEY_USER_ID + "=?", new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor !=null)
            cursor.moveToFirst();
        User user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        db.close();
        cursor.close();
        return user;
    }

    public Address getAddress(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_ADDRESS, new String[] {KEY_ADDRESS_ID,KEY_ADDRESS_USER_ID, KEY_ADDRESS_STREET1, KEY_ADDRESS_STREET2, KEY_ADDRESS_CITY, KEY_ADDRESS_COUNTRY},
                KEY_ADDRESS_ID + "=?", new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor !=null)
            cursor.moveToFirst();
        Address address = new Address(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
        db.close();
        cursor.close();
        return address;
    }

    public Review getReview(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_REVIEW, new String[] {KEY_REVIEW_ID,KEY_REVIEW_BOOK_ID, KEY_REVIEW_USER_ID, KEY_REVIEW_RATING, KEY_REVIEW_COMMENT},
                KEY_REVIEW_ID + "=?", new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor !=null)
            cursor.moveToFirst();
        Review review = new Review(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), Double.parseDouble(cursor.getString(3)), cursor.getString(4));
        db.close();
        cursor.close();
        return review;
    }

    public PaymentMethod getPaymentMethod(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_PAYMENT_METHOD, new String[] {KEY_PAYMENT_METHOD_ID,KEY_PAYMENT_METHOD_USER_ID, KEY_PAYMENT_METHOD_CARD_HOLDER_NAME, KEY_PAYMENT_METHOD_CARD_NUMBER, KEY_PAYMENT_METHOD_CARD_EXPIRY, KEY_PAYMENT_METHOD_CARD_CVV},
                KEY_PAYMENT_METHOD_ID + "=?", new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor !=null)
            cursor.moveToFirst();
        PaymentMethod paymentMethod = new PaymentMethod(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
        db.close();
        cursor.close();
        return paymentMethod;
    }

    public PurchaseHistory getPurchaseHistory(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Date date = null;
        Cursor cursor = db.query(TABLE_PURCHASE_HISTORY, new String[] {KEY_PURCHASE_HISTORY_ID, KEY_PURCHASE_HISTORY_USER_ID, KEY_PURCHASE_HISTORY_BOOK_ID, KEY_PURCHASE_HISTORY_DATE},
                KEY_PURCHASE_HISTORY_ID + "=?", new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor !=null)
            cursor.moveToFirst();
        DateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = iso8601Format.parse(cursor.getString(3));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        PurchaseHistory purchaseHistory = new PurchaseHistory(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), date);
        db.close();
        cursor.close();
        return purchaseHistory;
    }

    public Book getBook(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_BOOKS, new String[] {KEY_BOOK_ID,KEY_BOOK_TITLE, KEY_BOOK_AUTHOR, KEY_BOOK_PRICE, KEY_BOOK_CATEGORY, KEY_BOOK_QUANTITY, KEY_BOOK_IMAGE},
                KEY_BOOK_ID + "=?", new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor !=null)
            cursor.moveToFirst();
        Book book = new Book(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), Integer.parseInt(cursor.getString(5)), Uri.parse(cursor.getString(6)));
        db.close();
        cursor.close();
        return book;
    }


    /**
     * Get the count of each table
     */

    public int getUsersCount() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int getAddressCount() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ADDRESS, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int getReviewsCount() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_REVIEW, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int getPaymentMethodCount() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PAYMENT_METHOD, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int getPurchaseHistoryCount() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PURCHASE_HISTORY, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int getBooksCount() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BOOKS, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }


    /**
     * RETURN COMPLETE TABLES TO LIST
     */

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS, null);
        if(cursor.moveToFirst()) {
            do {
                users.add(new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3)));

            } while (cursor.moveToNext()) ;
        }
        return users;
    }

    public List<Address> getAllAddresses() {
        List<Address> addresses = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ADDRESS, null);
        if(cursor.moveToFirst()) {
            do {
                addresses.add(new Address(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)));

            } while (cursor.moveToNext()) ;
        }
        return addresses;
    }

    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_REVIEW, null);
        if(cursor.moveToFirst()) {
            do {
                reviews.add(new Review(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), Double.parseDouble(cursor.getString(3)), cursor.getString(4)));

            } while (cursor.moveToNext()) ;
        }
        return reviews;
    }

    public List<PaymentMethod> getAllPaymentMethods() {
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PAYMENT_METHOD, null);
        if(cursor.moveToFirst()) {
            do {
                paymentMethods.add(new PaymentMethod(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)));

            } while (cursor.moveToNext()) ;
        }
        return paymentMethods;
    }

    public List<PurchaseHistory> getAllPurchaseHistory() {
        List<PurchaseHistory> purchaseHistories = new ArrayList<>();
        Date date = new Date();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PURCHASE_HISTORY, null);
        if(cursor.moveToFirst()) {
            do {
                DateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    date = iso8601Format.parse(cursor.getString(3));
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }
                purchaseHistories.add(new PurchaseHistory(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), date));
            } while (cursor.moveToNext()) ;
        }
        return purchaseHistories;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BOOKS, null);
        if(cursor.moveToFirst()) {
            do {
                books.add(new Book(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), Integer.parseInt(cursor.getString(5)), Uri.parse(cursor.getString(6))));

            } while (cursor.moveToNext()) ;
        }
        return books;
    }

    public void deleteTableContents(String tableName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from "+ tableName);
    }
}
