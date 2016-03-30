package finalyear.bookstorepatterns.Model;

import java.util.Date;

/**
 * Created by College on 30/03/2016.
 */
public class PurchaseHistory {

    private Date _date;
    private int _purchaseHistoryId, _userId, _bookId;

    public PurchaseHistory(int _purchaseHistoryId, int _userId, int _bookId, Date _date) {
        this._purchaseHistoryId = _purchaseHistoryId;
        this._userId = _userId;
        this._bookId = _bookId;
        this._date = _date;
    }

    public Date get_date() {
        return _date;
    }

    public void set_date(Date _date) {
        this._date = _date;
    }

    public int get_purchaseHistoryId() {
        return _purchaseHistoryId;
    }

    public void set_purchaseHistoryId(int _purchaseHistoryId) {
        this._purchaseHistoryId = _purchaseHistoryId;
    }

    public int get_userId() {
        return _userId;
    }

    public void set_userId(int _userId) {
        this._userId = _userId;
    }

    public int get_bookId() {
        return _bookId;
    }

    public void set_bookId(int _bookId) {
        this._bookId = _bookId;
    }
}
