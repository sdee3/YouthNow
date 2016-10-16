package rs.youthnow;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;


public class PostItemAdapter extends ArrayAdapter<PostData> {
    private Activity myContext;
    private PostData[] datas;

    public PostItemAdapter(Context context, int textViewResourceId, PostData[] objects) {
        super(context, textViewResourceId, objects);

        myContext = (Activity) context;
        datas = objects;
    }
}


