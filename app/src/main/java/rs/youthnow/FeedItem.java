package rs.youthnow;

import android.media.Image;
import android.text.Html;
import android.widget.ImageView;

public class FeedItem {
    String title;
    String link;
    String description;
    String pubDate;
    String imageURL;

    public String getTitle() {
        return Html.fromHtml(title).toString();
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getDescription() {
        return Html.fromHtml(description).toString();
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPubDate() {
        return pubDate;
    }
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
    public String getImageURL(){
        return imageURL;
    }
    public void setImageURL(String imageURL){
        this.imageURL = imageURL;
    }
}
