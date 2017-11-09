package co.michaeland.galaxytech.Objects;



/**
 * Created by micha on 11/5/2017.
 */
public class Story {

    private String mStoryID;

    private String mUser;

    private long mPostDate;

    private String mPost;

    public Story(String StoryID, String User, long PostDate, String Post){
        mStoryID = StoryID;
        mUser = User;
        mPostDate = PostDate;
        mPost = Post;
    }
    public String getmStoryID() {
        return mStoryID;
    }
    public String getmUser() {
        return mUser;
    }
    public long getmPostDate() {
        return mPostDate;
    }
    public String getmPost() {
        return mPost;
    }
}
