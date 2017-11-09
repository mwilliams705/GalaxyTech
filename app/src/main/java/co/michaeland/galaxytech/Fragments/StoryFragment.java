package co.michaeland.galaxytech.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.Profile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.michaeland.galaxytech.Objects.Story;
import co.michaeland.galaxytech.adapters.StoryRecyclerAdapter;
import co.michaeland.galaxytech.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class StoryFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private RecyclerView storyRecycler;



    public StoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Context context = container.getContext();
        View storyView = inflater.inflate(R.layout.fragment_story, container, false);
        storyPostBuilder();
        storyRecycler = storyView.findViewById(R.id.storyRecycler);
        storyRecycler.setLayoutManager(new LinearLayoutManager(context));
        storyRecycler.setAdapter(new StoryRecyclerAdapter(context, storyPostBuilder()));
        storyRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(
                        context,
                        "Fix this toast to tell which item is clicked!!!",
                        Toast.LENGTH_LONG)
                        .show();
            }
        });
        return storyView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed() {
        if (mListener != null) {
            mListener.onFragmentInteraction();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        void onFragmentInteraction();
    }

    public ArrayList<Story> storyPostBuilder(){
        int id = 0;

        ArrayList<Story> stories = new ArrayList<>();

        stories.add(new Story(String.valueOf(id),"Michael", System.currentTimeMillis(),"Derp"));
        stories.add(new Story(String.valueOf(id),"Jorge", System.currentTimeMillis()+5000, "Michael is a loser"));
        stories.add(new Story(String.valueOf(id),"Liz", System.currentTimeMillis(),"Im a Loser!!!"));

        stories.add(new Story(String.valueOf(id),"Michael", System.currentTimeMillis(),"Derp"));
        stories.add(new Story(String.valueOf(id),"Jorge", System.currentTimeMillis()+5000, "Michael is a loser"));
        stories.add(new Story(String.valueOf(id),"Liz", System.currentTimeMillis(),"Im a Loser!!!"));

        stories.add(new Story(String.valueOf(id),"Michael", System.currentTimeMillis(),"Derp"));
        stories.add(new Story(String.valueOf(id),"Jorge", System.currentTimeMillis()+5000, "Michael is a loser"));
        stories.add(new Story(String.valueOf(id),"Liz", System.currentTimeMillis(),"Im a Loser!!!"));

        stories.add(new Story(String.valueOf(id),"Michael", System.currentTimeMillis(),"Derp"));
        stories.add(new Story(String.valueOf(id),"Jorge", System.currentTimeMillis()+5000, "Michael is a loser"));
        stories.add(new Story(String.valueOf(id),"Liz", System.currentTimeMillis(),"Im a Loser!!!"));

        stories.add(new Story(String.valueOf(id),"Michael", System.currentTimeMillis(),"Derp"));
        stories.add(new Story(String.valueOf(id),"Jorge", System.currentTimeMillis()+5000, "Michael is a loser"));
        stories.add(new Story(String.valueOf(id),"Liz", System.currentTimeMillis(),"Im a Loser!!!"));

        stories.add(new Story(String.valueOf(id), "Michael", System.currentTimeMillis(),"Derp"));
        stories.add(new Story(String.valueOf(id),"Jorge", System.currentTimeMillis()+5000, "Michael is a loser"));
        stories.add(new Story(String.valueOf(id),"Liz", System.currentTimeMillis(),"Im a Loser!!!"));

        return stories;
    }
}
