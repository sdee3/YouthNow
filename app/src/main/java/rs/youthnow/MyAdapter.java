package rs.youthnow;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Stefan on 16.10.2016.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    ArrayList<FeedItem>feedItems;
    Context context;

    public MyAdapter (Context context, ArrayList<FeedItem>feedItems){
        this.context = context;
        this.feedItems = feedItems;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_row_news_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final FeedItem current = feedItems.get(position);
        holder.title.setText(current.getTitle());
        holder.desc.setText(current.getDescription());
        holder.date.setText(current.getPubDate());
        Picasso.with(context).load(current.getImageURL()).into(holder.img);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,NewsDetails.class);
                intent.putExtra("link", current.getLink());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return feedItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title, desc, date;
        ImageView img;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title_text);
            desc = (TextView) itemView.findViewById(R.id.description_text);
            date = (TextView) itemView.findViewById(R.id.dateText);
            cardView= (CardView) itemView.findViewById(R.id.cardView);
            img = (ImageView) itemView.findViewById(R.id.thumb_img);

        }
    }
}
