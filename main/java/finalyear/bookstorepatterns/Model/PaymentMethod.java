package finalyear.bookstorepatterns.Model;

/**
 * Created by College on 30/03/2016.
 */
public class PaymentMethod {

    private String _cardHolderName, _cardNumber, _cardExpiry, _cardCVV;
    int _paymentMethodId, _userId;

    public PaymentMethod(int _paymentMethodId, int _userId, String _cardHolderName, String _cardNumber, String _cardExpiry, String _cardCVV) {
        this._paymentMethodId = _paymentMethodId;
        this._userId = _userId;
        this._cardHolderName = _cardHolderName;
        this._cardNumber = _cardNumber;
        this._cardExpiry = _cardExpiry;
        this._cardCVV = _cardCVV;
    }

    public String get_cardHolderName() {
        return _cardHolderName;
    }

    public void set_cardHolderName(String _cardHolderName) {
        this._cardHolderName = _cardHolderName;
    }

    public String get_cardNumber() {
        return _cardNumber;
    }

    public void set_cardNumber(String _cardNumber) {
        this._cardNumber = _cardNumber;
    }

    public String get_cardExpiry() {
        return _cardExpiry;
    }

    public void set_cardExpiry(String _cardExpiry) {
        this._cardExpiry = _cardExpiry;
    }

    public String get_cardCVV() {
        return _cardCVV;
    }

    public void set_cardCVV(String _cardCVV) {
        this._cardCVV = _cardCVV;
    }

    public int get_paymentMethodId() {
        return _paymentMethodId;
    }

    public void set_paymentMethodId(int _paymentMethodId) {
        this._paymentMethodId = _paymentMethodId;
    }

    public int get_userId() {
        return _userId;
    }

    public void set_userId(int _userId) {
        this._userId = _userId;
    }
}
