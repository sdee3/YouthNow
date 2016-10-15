package rs.youthnow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private PostData[] listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.generateDummyData();
        ListView listView = (ListView) this.findViewById(R.id.postlistview);
        PostItemAdapter itemAdapter = new PostItemAdapter(this, R.layout.postitem, listData);
        listView.setAdapter(itemAdapter);

    }

    private void generateDummyData() {
        PostData data = null;
        listData = new PostData[10];
        for (int i = 0; i < 10; i++) {
            data = new PostData();
            data.postDate = "May 21, 2016";
            data.postTitle = "Post " + (i + 1) + " Title: This is the Post Title from RSS Feed";
            data.postThumbUrl = null;
            listData[i] = data;
        }
    }


}
