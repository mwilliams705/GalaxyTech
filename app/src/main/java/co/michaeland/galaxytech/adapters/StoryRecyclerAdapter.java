package co.michaeland.galaxytech.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import co.michaeland.galaxytech.Objects.Story;
import co.michaeland.galaxytech.R;

/**
 * Created by micha on 11/5/2017.
 */

public class StoryRecyclerAdapter extends RecyclerView.Adapter<StoryRecyclerAdapter.StoryCardSmallViewHolder> {

    List<Story> mStories;
    Context mContext;
    StoryCardSmallViewHolder storyCardSmallViewHolder;
    public StoryRecyclerAdapter(Context context, List<Story> stories){
        mContext = context;
        mStories = stories;
    }

    private Context getContext(){
        return mContext;
    }

//    Drawable bookmarkBorder = getContext().getResources().getDrawable(R.drawable.ic_bookmark_border);
//    Drawable bookmarkFill = getContext().getResources().getDrawable(R.drawable.ic_bookmark);

    @Override
    public StoryCardSmallViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View storyCardSmall = layoutInflater.inflate(R.layout.story_card_small, parent, false);
        storyCardSmallViewHolder = new StoryCardSmallViewHolder(storyCardSmall);



        return storyCardSmallViewHolder;
    }

    @Override
    public void onBindViewHolder(StoryCardSmallViewHolder holder, int position) {

        Story story = mStories.get(position);

        TextView storyCardUsername = holder.storyCardUsername;
        storyCardUsername.setText(story.getmUser());

        TextView storyCardPost = holder.storyCardPost;
        storyCardPost.setText(story.getmPost());

        TextView storyCardDate = holder.storyCardDate;
        storyCardDate.setText("???");

        final Button bookmark = holder.bookmarkBtn;
//        bookmark.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (bookmark.getBackground()==bookmarkBorder){
//                    bookmark.setBackground(bookmarkFill);
//                }else {
//                    bookmark.setBackground(bookmarkBorder);
//                }
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return mStories.size();
    }

    public class StoryCardSmallViewHolder extends RecyclerView.ViewHolder {

        public TextView storyCardUsername;
        public TextView storyCardPost;
        public TextView storyCardDate;
        public Button bookmarkBtn;

        public StoryCardSmallViewHolder(View itemView) {
            super(itemView);

            storyCardUsername = itemView.findViewById(R.id.storyCardUsername);
            storyCardPost = itemView.findViewById(R.id.storyCardPost);
            storyCardDate = itemView.findViewById(R.id.storyCardDate);
            bookmarkBtn = itemView.findViewById(R.id.bookmarkBtn);
        }


    }

}
