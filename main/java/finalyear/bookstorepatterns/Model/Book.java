package finalyear.bookstorepatterns.Model;

import android.net.Uri;

/**
 * Created by College on 30/03/2016.
 */
public class Book {

    private String _title, _author, _price, _category;
    private int _quantity, _bookId;
    private Uri _bookImage;

    public Book(int _bookId, String _title, String _author, String _price, String _category, int _quantity, Uri _bookImage) {
        this._bookId = _bookId;
        this._title = _title;
        this._author = _author;
        this._price = _price;
        this._category = _category;
        this._quantity = _quantity;
        this._bookImage = _bookImage;
    }

    public Uri get_bookImage() {
        return _bookImage;
    }

    public void set_bookImage(Uri _bookImage) {
        this._bookImage = _bookImage;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public String get_author() {
        return _author;
    }

    public void set_author(String _author) {
        this._author = _author;
    }

    public String get_price() {
        return _price;
    }

    public void set_price(String _price) {
        this._price = _price;
    }

    public String get_category() {
        return _category;
    }

    public void set_category(String _category) {
        this._category = _category;
    }

    public int get_quantity() {
        return _quantity;
    }

    public void set_quantity(int _quantity) {
        this._quantity = _quantity;
    }

    public int get_bookId() {
        return _bookId;
    }

    public void set_bookId(int _bookId) {
        this._bookId = _bookId;
    }
}
