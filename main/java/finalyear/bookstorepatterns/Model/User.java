package finalyear.bookstorepatterns.Model;

/**
 * Created by College on 30/03/2016.
 */
public class User {

    private int _userId;
    private String _name, _email, _password;

    public User(int _userId, String _name, String _email, String _password) {
        this._userId = _userId;
        this._name = _name;
        this._email = _email;
        this._password = _password;
    }

    public int get_userId() {
        return _userId;
    }

    public void set_userId(int _userId) {
        this._userId = _userId;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }
}
