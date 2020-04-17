package com.example.regionapi;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.AdapterView.OnItemClickListener;
import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class RegionActivity extends AppCompatActivity {
    private XmlPullParserFactory factory;
    private XmlPullParser parser;
    private RegionAdaptor adapter;
    ListView listView;
    RegionAdaptor myRegionAdaptor;
    //Adapter와 listview를 연결
    ArrayList<Region> list_regionArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //listView ?? RadioButton?
        listView = (ListView) findViewById(R.id.my_listview);
        list_regionArrayList = new ArrayList<Region>();


//Adapter 객체 생성.
        myRegionAdaptor = new
                RegionAdaptor(RegionActivity.this, list_regionArrayList);
//listview객체에 setAdapter을 통해 Adaptor연결
        android.R.layout.simple_list_item_single_choice, list_regionArrayList);
        listView.setAdapter(myRegionAdaptor);
        try {
            //루프 돌면서 xml 파싱.
            set_parser();
            parsing_loop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //listView에서 클릭 했을 때 색 변화(하나 선택)
        listView.setOnItemClickListener(new OnItemClickListener() {


        @Override
        public void onItemClick(AdapterView arg0, View view, int position,
                                long itemId) {
// 아이템을 클릭했을 때 position을 파악
//선택된 것을 하이라이트

            CheckedTextView textView = (CheckedTextView) view;
            for (int i = 0; i < listView.getCount(); i++) {
                textView = (CheckedTextView) listView.getChildAt(i);
                if (textView != null) {
                    textView.setTextColor(Color.WHITE);
                }

            }
            listView.invalidate();
            textView = (CheckedTextView) view;
            if (textView != null) {
                textView.setTextColor(Color.BLUE);
            }

        }
        });


}
public void set_parser()throws IOException,XmlPullParserException{
        factory=XmlPullParserFactory.newInstance();
        parser=factory.newPullParser();
        String path="http://api.visitkorea.or.kr/openapi/service/rest/EngService/areaCode?"+
        "serviceKey=bywYnmxO22GvVcjVqX51LynXlyzrFTtMzTRM88W6rKGfO8VQtw6HycfhvB9ci6s9nH1fPDCOWQnabI9Htz7WDg%3D%3D"+
        "&numOfRows=10&pageSize=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&areaCode=1";

        URL url=new URL(path);
        InputStream is=url.openStream();
        parser.setInput(new InputStreamReader(is,"UTF-8"));


        }

public void parsing_loop()throws XmlPullParserException,IOException{
final int STEP_NONE=0;
final int STEP_CODE=1;
final int STEP_NAME=2;
final int STEP_RNUM=3;

        int step=STEP_NONE;
        String code=null;
        String name=null;
        String rnum=null;

        int eventType=parser.getEventType();
//xml 형식:
//<item>
//<code>1</code>
//<name>서울</name>
//<rnum>1</rnum>
//</item>
        //xml 파싱할때 START_DOCUMENT의 위치..?<body>부터 시작..?
        // XML 파일의 시작.
        eventType=parser.getEventType();
        while(eventType!=XmlPullParser.END_DOCUMENT){
        if(eventType==XmlPullParser.START_DOCUMENT){
        parser.next();
        eventType=parser.getEventType();
        }
        if(eventType==XmlPullParser.START_TAG){
        String startTag=parser.getName();
        if(startTag.equals("code")){
        step=STEP_CODE;
        }else if(startTag.equals("name")){
        step=STEP_NAME;
        }else if(startTag.equals("rnum")){
        step=STEP_RNUM;
        }else{
        step=STEP_NONE;
        }
        }else if(eventType==XmlPullParser.TEXT&&step!=STEP_NONE){
        String text=parser.getText();
        if(step==STEP_CODE){
        code=text;
        }else if(step==STEP_NAME){
        name=text;
        }else if(step==STEP_RNUM){
        rnum=text;
        }
        }else if(eventType==XmlPullParser.END_TAG){
        String endTag=parser.getName();
        if(endTag.equals("item")){

        list_regionArrayList.add(
        new Region(code,name,rnum));
        step=STEP_NONE;
        }
        }else{

        }

        eventType=parser.next();
        }
        }
        }