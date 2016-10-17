package rs.youthnow;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
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
                        }else if (current.getNodeName().equalsIgnoreCase("content:encoded")){
                            Log.d("if1","proslo 1");
                            Node currentchild2 = current.getFirstChild();
                            if(currentchild2.getNodeName().equalsIgnoreCase("img")){
                                Log.d("if2","proslo 2");
                                Node currentchild3 = currentchild2.getFirstChild();
                                if(currentchild3.getNodeName().equalsIgnoreCase("src")){
                                    Log.d("if3","proslo 3");
                                    Node currentchild4 = currentchild3.getFirstChild();
                                    item.setImageURL(currentchild4.getNodeName().toString());
                                }
                            }

                            /*Log.d("content:encoded","nasao je Content:encoded?");
                            org.jsoup.nodes.Document doc = null;
                            try {
                                doc = Jsoup.connect("http://www.youthnow.rs").get();
                                Log.d("doc","uspesno (doc)");
                                Log.d("docValue",doc.toString());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            org.jsoup.nodes.Element article = null;
                            if (doc != null) {
                                article = doc.select("link[type=application/rss+xml]").first();
                                Log.d("Article", article.toString());
                            }else{
                                Log.d("failedDoc","Neuspesno (Doc)");
                            }
                            org.jsoup.nodes.Element imageElement = null;
                            if (article != null) {
                                imageElement = article.select("img").first();
                            }else {
                                Log.d("articleTest","Neuspesno (Article)...");
                            }
                            imageElement.absUrl("src");  //absolute URL on src;
                            item.setImageURL(imageElement.val());
                         */
                         }

                    }
                    feedItems.add(item);
                    Log.d("itemTitle", item.getTitle());
                    Log.d("itemDescription", item.getDescription());
                    Log.d("itemPubDate", item.getPubDate());
                    Log.d("itemLink", item.getLink());
                    //Log.d("imageLink", item.getImageURL());
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
