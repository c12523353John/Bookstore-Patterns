package finalyear.bookstorepatterns.Patterns;

/**
 * Created by College on 04/04/2016.
 */
public class SingletonUserID {
    private static SingletonUserID instance = null;

    private int loggedInId;

    protected SingletonUserID(){

    }

    public static SingletonUserID getInstance() {
        if(instance == null) {
            instance = new SingletonUserID();
        }

        return instance;
    }

    public void setLoggedInId(int i) {
        loggedInId = i;
    }

    public int getLoggedInId() {
        return loggedInId;
    }


}
