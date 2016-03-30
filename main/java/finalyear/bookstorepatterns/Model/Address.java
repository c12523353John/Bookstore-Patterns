package finalyear.bookstorepatterns.Model;

/**
 * Created by College on 30/03/2016.
 */
public class Address {

    private String _street1, _street2, _city, _country;
    int _addressId, _userId;

    public Address(int _addressId, int _userId, String _street1, String _street2, String _city, String _country) {
        this._addressId = _addressId;
        this._userId = _userId;
        this._street1 = _street1;
        this._street2 = _street2;
        this._city = _city;
        this._country = _country;
    }

    public String get_street1() {
        return _street1;
    }

    public void set_street1(String _street1) {
        this._street1 = _street1;
    }

    public String get_street2() {
        return _street2;
    }

    public void set_street2(String _street2) {
        this._street2 = _street2;
    }

    public String get_city() {
        return _city;
    }

    public void set_city(String _city) {
        this._city = _city;
    }

    public String get_country() {
        return _country;
    }

    public void set_country(String _country) {
        this._country = _country;
    }

    public int get_addressId() {
        return _addressId;
    }

    public void set_addressId(int _addressId) {
        this._addressId = _addressId;
    }

    public int get_userId() {
        return _userId;
    }

    public void set_userId(int _userId) {
        this._userId = _userId;
    }
}
