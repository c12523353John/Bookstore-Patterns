package finalyear.bookstorepatterns.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "bookstorePatterns",

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
    KEY_REVIEW_COMMENT = "comment",
    KEY_REVIEW_RATING = "rating",

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
        db.execSQL("CREATE TABLE " + TABLE_REVIEW + "(" + KEY_REVIEW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_REVIEW_BOOK_ID + " TEXT," + KEY_REVIEW_USER_ID + " TEXT," + KEY_REVIEW_COMMENT + " TEXT," + KEY_REVIEW_RATING + " TEXT" + ")");
        db.execSQL("CREATE TABLE " + TABLE_PAYMENT_METHOD + "(" + KEY_PAYMENT_METHOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_PAYMENT_METHOD_USER_ID + " TEXT," + KEY_PAYMENT_METHOD_CARD_HOLDER_NAME + " TEXT," + KEY_PAYMENT_METHOD_CARD_NUMBER + " TEXT," + KEY_PAYMENT_METHOD_CARD_EXPIRY + " TEXT," + KEY_PAYMENT_METHOD_CARD_CVV + " TEXT" + ")");
        db.execSQL("CREATE TABLE " + TABLE_PURCHASE_HISTORY + "(" + KEY_PURCHASE_HISTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_PURCHASE_HISTORY_USER_ID + " TEXT," + KEY_PURCHASE_HISTORY_BOOK_ID + KEY_PURCHASE_HISTORY_DATE + " TEXT" + ")");
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
        values.put(KEY_REVIEW_COMMENT, review.get_comment());
        values.put(KEY_REVIEW_RATING, review.get_rating());
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
        values.put(KEY_PURCHASE_HISTORY_DATE, String.valueOf(purchaseHistory.get_date()));
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


}
