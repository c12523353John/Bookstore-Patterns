package finalyear.bookstorepatterns.Model;

/**
 * Created by College on 30/03/2016.
 */
public class Review {

    private double _rating;
    private String _comment;
    private int _reviewId, _bookId, _userId;

    public Review(int _reviewId, int _bookId, int _userId, double _rating, String _comment) {
        this._rating = _rating;
        this._comment = _comment;
        this._reviewId = _reviewId;
        this._bookId = _bookId;
        this._userId = _userId;
    }

    public double get_rating() {
        return _rating;
    }

    public void set_rating(double _rating) {
        this._rating = _rating;
    }

    public String get_comment() {
        return _comment;
    }

    public void set_comment(String _comment) {
        this._comment = _comment;
    }

    public int get_reviewId() {
        return _reviewId;
    }

    public void set_reviewId(int _reviewId) {
        this._reviewId = _reviewId;
    }

    public int get_bookId() {
        return _bookId;
    }

    public void set_bookId(int _bookId) {
        this._bookId = _bookId;
    }

    public int get_userId() {
        return _userId;
    }

    public void set_userId(int _userId) {
        this._userId = _userId;
    }
}
