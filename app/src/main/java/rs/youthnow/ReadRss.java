package rs.youthnow;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Stefan on 16.10.2016.
 */

public class ReadRss extends AsyncTask<Void,Void,Void> {

    Context context;
    String address ="http://www.youthnow.rs/feed/";
    URL url;
    ProgressDialog loading;
    ArrayList<FeedItem> feedItems;
    RecyclerView recyclerView;

    public ReadRss (Context context, RecyclerView recyclerView){
        this.recyclerView = recyclerView;
        this.context = context;
        loading = new ProgressDialog(context);
        loading.setMessage("Učitavanje...");
    }

    public String stripHtml(String html) {
        return Html.fromHtml(html).toString();
    }

    @Override
    protected void onPreExecute() {
        loading.show();
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            ProcessXml(getData());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        loading.dismiss();

        MyAdapter adapter = new MyAdapter(context, feedItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new VerticalSpace(50));
    }


    private void ProcessXml(Document data) throws ParseException {
        if (data != null) {
           feedItems = new ArrayList<>();
            Element root = data.getDocumentElement();
            Node channel = root.getChildNodes().item(1);
            NodeList items = channel.getChildNodes();
            for (int i = 0; i < items.getLength(); i++) {
                Node currentchild = items.item(i);
                if (currentchild.getNodeName().equalsIgnoreCase("item")) {
                    FeedItem item = new FeedItem();
                    NodeList itemchilds = currentchild.getChildNodes();
                    for (int j = 0; j < itemchilds.getLength(); j++) {
                        Node current = itemchilds.item(j);
                        if (current.getNodeName().equalsIgnoreCase("title")) {
                            item.setTitle(current.getTextContent());
                        } else if (current.getNodeName().equalsIgnoreCase("description")) {
                            item.setDescription(current.getTextContent());
                        } else if (current.getNodeName().equalsIgnoreCase("pubDate")) {
                            item.setPubDate(current.getTextContent());
                        } else if (current.getNodeName().equalsIgnoreCase("link")) {
                            item.setLink(current.getTextContent());
                        }else if (current.getNodeName().equalsIgnoreCase("img")){
                            //this will return us thumbnail url
                            String url=current.getAttributes().item(0).getTextContent();
                            item.setImageURL(url);
                        }
                    }
                    feedItems.add(item);
                    Log.d("itemTitle", item.getTitle());
                    Log.d("itemDescription", item.getDescription());
                    Log.d("itemPubDate", item.getPubDate());
                    Log.d("itemLink", item.getLink());
                }
            }
        }
    }

    public Document getData(){
        try {
            url = new URL(stripHtml(address));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            Document xmlDoc = documentBuilder.parse(inputStream);

            return xmlDoc;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
