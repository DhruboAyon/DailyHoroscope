package com.test.staticgridview;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
 
public class FullImage extends Activity {
	
	InitTask _initTask;
	private ProgressDialog pd;
	private int id;
	String[] list = {"aquarius","aries","cancer","capricorn","gemini","leo","libra","pisces","sagittarius","scorpio","taurus","virago"};
	String text;
	TextView textView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		id = getIntent().getExtras().getInt("id");
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+id);
        setContentView(R.layout.full_image);
        
		if(isConnectedToInternet()){
			_initTask = new InitTask();
			_initTask.execute(this);
		}
 
/*        // get intent data
        Intent i = getIntent();
 
        // Selected image id
        int position = i.getExtras().getInt("id");
        ImageAdapter imageAdapter = new ImageAdapter(this);
 
        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
        imageView.setImageResource(imageAdapter.mThumbIds[position]);
        imageView.setBackgroundColor(R.drawable.image_bg);*/
    }
    
	protected class InitTask extends AsyncTask<Context, Integer, String>
	{
		
		@Override
		protected void onPreExecute() {
			pd = new ProgressDialog(FullImage.this);
			pd.setTitle("Processing...");
			pd.setMessage("Please wait.");
			pd.setCancelable(false);
			pd.setIndeterminate(true);
			pd.show();
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (pd!=null)
				pd.dismiss();
			
			textView = (TextView) findViewById(R.id.text_view);
			textView.setText(text);
		}
		@Override
		protected String doInBackground(Context... arg0) {
			// TODO Auto-generated method stub
			Document doc = null;
			int count = 0;
			try {
				String url = "http://astrology.horoscope.com/free-weekly-teen-horoscope-"+list[id]+".html";
				System.out.println("XXXXXXXXXXXX"+url);
				doc = Jsoup.connect(url).get();
				System.out.println("XXXXXXXXXXXX"+doc.html());
				/*Document doc1 = Jsoup.parse("<html><body><div/>" + 
			    "<div id=\"navDiv\">" +
			    "<div id=\"navDiv1\">asdasdasd"+
			    "<table id=\"navDiv4\"><tr><td><div id=\"navDiv1000\">asdasdasd </div></td></tr></div>" +
			    " </div>" +
			        "<a href=\"href1\">link1</a>" +
			        "<a href=\"href2\">link2</a><" +
			    "</div></body></html>");*/
				Element newsHeadlines = doc.select("div#textline").first();
				System.out.println("XXXXXXXXXXXX"+list[id]);
				text = newsHeadlines.text();
				System.out.println("XXXXXXXXXXXX"+list[id]+"  ["+text+"]");
				
				/*if(!newsHeadlines.isEmpty()) {
					System.out.println("aaaaaaaaaa"+newsHeadlines.first().text());
					text = newsHeadlines.first().text();
				}*/			

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			

			return "COMPLETE!";
		}


	}
//TODO add this to check connectivity
	public boolean isConnectedToInternet(){
		ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo                 = connectivityManager.getActiveNetworkInfo();

		if (networkInfo != null && networkInfo.isConnected() && networkInfo.isAvailable())
			return true;
		else
			return false;
	}
}