package finalyear.bookstorepatterns.Model;

import android.net.Uri;

/**
 * Created by College on 30/03/2016.
 */
public class Book {

    private String _title, _author, _price, _category;
    private int _quantity, _bookId;
    private Uri _bookImage;

    public Book(int bookId, String title, String author, String price, String category, int quantity, Uri bookImage) {
        _bookId = bookId;
        _title = title;
        _author = author;
        _price = price;
        _category = category;
        _quantity = quantity;
        _bookImage = bookImage;
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
