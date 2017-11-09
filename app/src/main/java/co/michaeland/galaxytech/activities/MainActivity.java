package co.michaeland.galaxytech.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;

import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.io.InputStream;

import co.michaeland.galaxytech.base_java_classes.CoreAction;
import co.michaeland.galaxytech.base_java_classes.GalaxyTechNotification;
import co.michaeland.galaxytech.FillerFragments.Left;
import co.michaeland.galaxytech.Fragments.StoryFragment;
import co.michaeland.galaxytech.R;
import co.michaeland.galaxytech.FillerFragments.Right;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements
StoryFragment.OnFragmentInteractionListener
{
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private CoreAction coreAction = new CoreAction(this);
    private GalaxyTechNotification galaxyTechNotification = new GalaxyTechNotification(this);

    //Activity Objects
    CallbackManager callbackManager;
    LoginButton loginButton;
    TextView facebookFirstName;
    TextView facebookLastName;
    CircleImageView facebookUserImage;
    AccessTokenTracker accessTokenTracker;
    String ProfileImageURI = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main);

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken newAccessToken) {
                updateWithToken(newAccessToken);
            }
        };
        updateWithToken(AccessToken.getCurrentAccessToken());

        FacebookLogin(this);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        Parallax(mViewPager,mSectionsPagerAdapter);
        FloatingActionButtonMenu(mViewPager);
        mViewPager.setCurrentItem(1);

    }

    @Override
    public void onFragmentInteraction() {

    }

    public void FloatingActionButtonMenu(final ViewPager viewPager){

        final View fabOverlay = findViewById(R.id.fabOverlay);
        fabOverlay.setVisibility(View.GONE);

        final FloatingActionsMenu MenuFAB = findViewById(R.id.MenuFAB);

        MenuFAB.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                coreAction.hapticFeedback();
                fabOverlay.startAnimation(coreAction.slideIN());
                fabOverlay.setVisibility(View.VISIBLE);
                fabOverlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MenuFAB.collapse();
                    }
                });
            }

            @Override
            public void onMenuCollapsed() {

                fabOverlay.startAnimation(coreAction.slideOUT());
                fabOverlay.setVisibility(View.GONE);
            }
        });



        //SignUp Action Button
        final FloatingActionButton SignUp = findViewById(R.id.SignUp);
        SignUp.setSize(FloatingActionButton.SIZE_MINI);
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                coreAction.hapticFeedback();
                viewPager.setCurrentItem(2);
                collapseActionMenu(fabOverlay,MenuFAB);
            }
        });

        //Text Tech Action Button
        final FloatingActionButton TextTech = findViewById(R.id.TextTech);
        TextTech.setSize(FloatingActionButton.SIZE_MINI);
        TextTech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                coreAction.hapticFeedback();
                galaxyTechNotification.Welcome();
                collapseActionMenu(fabOverlay,MenuFAB);
            }
        });

        //NerdsNearMe Action Button
        final FloatingActionButton NerdsNearMe = findViewById(R.id.NerdsNearMe);
        NerdsNearMe.setSize(com.getbase.floatingactionbutton.FloatingActionButton.SIZE_MINI);
        NerdsNearMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                coreAction.hapticFeedback();
                Intent intent = new Intent(view.getContext(),MapsActivity.class);
                startActivity(intent);
                collapseActionMenu(fabOverlay,MenuFAB);
            }
        });

        //Appointment Action Button
        final FloatingActionButton Appointment = findViewById(R.id.Appointment);
        Appointment.setSize(com.getbase.floatingactionbutton.FloatingActionButton.SIZE_MINI);
        Appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                coreAction.hapticFeedback();
                Intent appointmentMaker = new Intent(view.getContext(), AppointmentMaker.class);
                startActivity(appointmentMaker);
                collapseActionMenu(fabOverlay,MenuFAB);
            }
        });
    }

    //Facebook Methods
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        callbackManager.onActivityResult(requestCode,resultCode,data);

    }
    public void FacebookLogin(final Context context){

        callbackManager = CallbackManager.Factory.create();

        facebookFirstName = findViewById(R.id.facebookFirstName);
        facebookLastName = findViewById(R.id.facebookLastName);
        facebookUserImage = findViewById(R.id.facebookUserImage);

//
        loginButton = findViewById(R.id.loginFacebook);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                ProfileImageURI = Profile.getCurrentProfile().getProfilePictureUri(200,200).toString();
                facebookFirstName.setText(Profile.getCurrentProfile().getFirstName());
                facebookLastName.setText(Profile.getCurrentProfile().getLastName());
                new DownloadImage((CircleImageView)findViewById(R.id.facebookUserImage)).execute(ProfileImageURI);

            }

            @Override
            public void onCancel() {
                Toast.makeText(context,"Facebook Login Cancelled", Toast.LENGTH_LONG);
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(context, "Login Error", Toast.LENGTH_LONG);
            }
        });


    }
    private void updateWithToken(AccessToken currentAccessToken) {

        if (currentAccessToken != null) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    ProfileImageURI = Profile.getCurrentProfile().getProfilePictureUri(200,200).toString();
                    facebookFirstName.setText(Profile.getCurrentProfile().getFirstName());
                    facebookLastName.setText(Profile.getCurrentProfile().getLastName());
                    new DownloadImage((CircleImageView)findViewById(R.id.facebookUserImage)).execute(ProfileImageURI);

                }
            },100);
        } else {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    loginButton.setVisibility(View.VISIBLE);
                }
            }, 100);
        }
    }



    //Animation Methods
    public void collapseActionMenu(View fabOverlay, FloatingActionsMenu MenuFAB){
        fabOverlay.startAnimation(coreAction.slideOUT());
        MenuFAB.collapse();
        fabOverlay.setVisibility(View.INVISIBLE);
    }
    public void Parallax(final ViewPager viewPager, final FragmentPagerAdapter customPagerAdapter){
        final HorizontalScrollView scrollView = findViewById(R.id.scroll_view);
        final ImageView imageView = findViewById(R.id.background);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int x = (int) ((viewPager.getWidth() * position + positionOffsetPixels) * computeFactor());
                scrollView.scrollTo(x, 0);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

            private float computeFactor() {
                return (imageView.getWidth() - viewPager.getWidth()) /
                        (float)(viewPager.getWidth() * (viewPager.getAdapter().getCount() - 1))/7;
            }
        });
        viewPager.setAdapter(customPagerAdapter);
    }


    //Internal Classes
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    Left left = new Left();
                    return left;
                case 1:
                    StoryFragment storyFragment = new StoryFragment();
                    return storyFragment;
                case 2:
                    Right right = new Right();
                    return right;
                default: return null;
            }


        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }


    }

    public class DownloadImage extends AsyncTask<String ,Void, Bitmap>{
        ImageView bitmapImage;

        public DownloadImage(ImageView bitmapImage) {
            this.bitmapImage = bitmapImage;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String urlDisplay = urls[0];
            Bitmap profileImage = null;
            try{
                InputStream in = new java.net.URL(urlDisplay).openStream();
                profileImage = BitmapFactory.decodeStream(in);

            }catch (Exception e){
                Log.e("Error:", e.getMessage());
                e.printStackTrace();
            }
            return profileImage;
        }
        protected void onPostExecute(Bitmap result){
            bitmapImage.setImageBitmap(result);
        }

    }

}
