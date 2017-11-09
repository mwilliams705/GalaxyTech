package co.michaeland.galaxytech.Objects;

/**
 * Created by Michael on 11/9/2017.
 */

public class User {

    private String mUserID;
    private String mUserFirstName;
    private String mUserLastName;
    private String mUserEmail;
    private String mPhoneNum;
    private String mFacebookPhoto;

    public User(String mUserID, String mUserFirstName, String mUserLastName, String mUserEmail, String mPhoneNum, String mFacebookPhoto) {
        this.mUserID = mUserID;
        this.mUserFirstName = mUserFirstName;
        this.mUserLastName = mUserLastName;
        this.mUserEmail = mUserEmail;
        this.mPhoneNum = mPhoneNum;
        this.mFacebookPhoto = mFacebookPhoto;
    }

    public String getmUserID() {
        return mUserID;
    }

    public String getmUserFirstName() {
        return mUserFirstName;
    }

    public String getmUserLastName() {
        return mUserLastName;
    }

    public String getmUserEmail() {
        return mUserEmail;
    }

    public String getmPhoneNum() {
        return mPhoneNum;
    }

    public String getmFacebookPhoto() {
        return mFacebookPhoto;
    }
}
