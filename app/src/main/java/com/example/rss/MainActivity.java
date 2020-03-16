package com.example.rss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
//    List<BTSNews> listBTS;
////    List<String> titles;
////    List<String> descriptions;
////    List<String> links;
    private RequestQueue mQueue;
    List<BTSNews> btsNewsList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mQueue = MySingleton.getInstance(this).getRequestQueue();
        btsNewsList = new ArrayList<>();

        String url ="https://www.bing.com/news/search?q=bts&format=rss";

        //do in background
        int coreCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(coreCount);
        executorService.execute(()->{
        try {
            Document doc = Jsoup.connect(url).get();
            Log.d("DOC",doc.toString());
            Elements itemElements = doc.select("item");
            for(Element item:itemElements) {
                String title = item.child(0).text();
                String link = item.child(1).text();
                String description = item.child(2).text();
                String pubDate = item.child(3).text();
                String newsSource = item.child(4).text();
                String newsImage = item.child(5).text();
                BTSNews btsNews = new BTSNews(title,link,description,pubDate,newsSource,newsImage);
                btsNewsList.add(btsNews);
                Log.d("TEST", title + link + description + pubDate + newsSource + newsImage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        });

        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.hasFixedSize();
        BTSAdapter myAdapter = new BTSAdapter(btsNewsList,MainActivity.this);
        recyclerView.setAdapter(myAdapter);
//        recyclerView.setVisibility(RecyclerView.VISIBLE);

//        listBTS = new ArrayList<>();
//        titles = new ArrayList<>();
//        links = new ArrayList<>();
//        descriptions = new ArrayList<>();
//        int coreCount = Runtime.getRuntime().availableProcessors();
//        ExecutorService executorService = Executors.newFixedThreadPool(coreCount);
//        executorService.execute(()->{
//            try{
//                URL url = new URL("https://www.bing.com/news/search?q=bts&format=rss");
//                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//                factory.setNamespaceAware(false);
//                XmlPullParser xpp = factory.newPullParser();
//                xpp.setInput(getInputStream(url),"UTF_8");
//                boolean insideItem = false;
//                int eventType = xpp.getEventType();
//                while (eventType != XmlPullParser.END_DOCUMENT){
//                    if(eventType == XmlPullParser.START_TAG){
//                        if (xpp.getName().equalsIgnoreCase("item")) {
//                            insideItem = true;
//                        }
//                        else if (xpp.getName().equalsIgnoreCase("title")){
//                            if(insideItem) {
//                                String title = xpp.nextText().trim();
//                                Log.d("TITLE", title);
//                                titles.add(title);
//                            }
//                        }
//                        else if (xpp.getName().equalsIgnoreCase("link")){
//                            if(insideItem)
////                                Log.d("DESCRIPTION",xpp.nextText());
//                                links.add(xpp.nextText().trim());
//                        }
//                        else if (xpp.getName().equalsIgnoreCase("description")){
//                            if(insideItem)
////                                Log.d("DESCRIPTION",xpp.nextText());
//                            descriptions.add(xpp.nextText().trim());
//                        }
//                    }
//                    else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")){
//                        insideItem = false;
//                    }
//                eventType = xpp.next();
//                }
//            }
//            catch (MalformedURLException e){e.printStackTrace();}
//            catch (XmlPullParserException e){e.printStackTrace();}
//            catch (IOException e){e.printStackTrace();}
//            Log.d("Title",titles.toString());
//            Log.d("Link",links.toString());
//            Log.d("Description", descriptions.toString());
//            List<String> a = titles;
//            List<String> b = descriptions;
//            StringRequest mStringRequest  = new StringRequest(Request.Method.GET,url, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    Log.d("RES",response);
//                    Document doc = Jsoup.parse(response);
//                    Elements itemElements = doc.select("item");
//                    for(Element item:itemElements){
//                        String title        = item.child(0).text();
//                        String link         = item.child(1).text();
//                        String description  = item.child(2).text();
//                        String pubDate      = item.child(3).text();
//                        String newsSource   = item.child(4).text();
//                        String newsImage    = item.child(5).text();
//                        Log.d("TEST",title+link+description+pubDate+newsSource+newsImage);
//                    }
//                }
//            },new Response.ErrorListener(){
//                @Override
//                public void onErrorResponse(VolleyError error) {
//
//                }
//            });
//            MySingleton.getInstance(MainActivity.this).addToRequestQueue(mStringRequest);
//        });
//        Log.d("BTS",listBTS.toString());

    }

//    public InputStream getInputStream(URL url){
//        try{
////            byte[] search = "&".getBytes("UTF-8");
////            byte[] replacement = "&amp;".getBytes("UTF-8");
//            InputStream inputStream = url.openConnection().getInputStream();
////            InputStream ris = new ReplacingInputStream(inputStream, search, replacement);
//            return inputStream;
//        }
//        catch(IOException e){
//            return null;
//        }
//    }
}
//                            BTSNews btsNews = new BTSNews();
//                            while(insideItem){
//                                if (xpp.getName().equalsIgnoreCase("title")) {
//                                    btsNews.setTitle(xpp.nextText());
//                                }
//                                else if (xpp.getName().equalsIgnoreCase("description")) {
//                                    btsNews.setDescription(xpp.nextText());
//                                }
//                                else if (xpp.getName().equalsIgnoreCase("link")) {
//                                    btsNews.setLink(xpp.nextText());
//                                }
//                                else if (xpp.getName().equalsIgnoreCase("image")) {
//                                    btsNews.setImage(xpp.nextText());
//                                }
//                                else if (xpp.getEventType() == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")) {
//                                    insideItem = false;
//                                }
//                                xpp.next();
//                            }
//                            listBTS.add(btsNews);
//
//                        }
//                    }
//                    xpp.next();
