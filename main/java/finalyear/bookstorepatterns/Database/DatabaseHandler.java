package finalyear.bookstorepatterns.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by College on 30/03/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "bookstorePatters",

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
    KEY_BOOK_IMAGE = "bookImage";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
