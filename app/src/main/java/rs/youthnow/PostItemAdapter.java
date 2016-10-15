package rs.youthnow;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class PostItemAdapter extends ArrayAdapter<PostData> {
    private Activity myContext;
    private PostData[] datas;

    public PostItemAdapter(Context context, int textViewResourceId, PostData[] objects) {
        super(context, textViewResourceId, objects);

        myContext = (Activity) context;
        datas = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;

        if(convertView == null) {
            LayoutInflater inflater = myContext.getLayoutInflater();
            convertView = inflater.inflate(R.layout.postitem, null);
        }

        rowView = convertView;
        ImageView thumbImageView = (ImageView) rowView.findViewById(R.id.postThumb);
        //check if the datas[position].postThumbUrl is null

        //if (datas[position].postThumbUrl == null) {
        //    thumbImageView.setImageResource(R.drawable.ic_android_black_24dp);
        // }

        TextView postTitleView = (TextView) rowView.findViewById(R.id.postTitleLabel);
        postTitleView.setText(datas[position].postTitle);

        TextView postDateView = (TextView) rowView.findViewById(R.id.postDateLabel);
        postDateView.setText(datas[position].postDate);

        return rowView;
    }
}


