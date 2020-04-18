package com.example.regionapi;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class RegionTourActivity {
    public class RegionActivity extends AppCompatActivity {
        private XmlPullParserFactory factory;
        private XmlPullParser parser;
        private ListView listView;
        private RegionTourAdaptor myRegionTourAdaptor;
        //Adapter와 listview를 연결
        private ArrayList<RegionTour> list_regionTourArrayList;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //Unexpected cast to listview
            listView = (ListView) findViewById(R.id.regiontour_textview);
            list_regionTourArrayList = new ArrayList<RegionTour>(); //RegionTour으로 된 배열 할당

            //xml을 파싱해서 list_regionArrayList에 추가..
            try {
                //루프 돌면서 xml 파싱. 파싱해서 Region객체를 만들어 list_regionArrayList에 추가한다.
                set_parser();
                parsing_loop();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //myRegionTourAdapter 객체 생성.
            myRegionTourAdaptor = new
                    RegionTourAdaptor(RegionActivity.this, list_regionTourArrayList);
//listview에 setAdapter을 통해 myReionTourAdaptor연결
            android.R.layout.simple_list_item_single_choice, list_regionTourArrayList);
            listView.setAdapter(myRegionTourAdaptor);

        }

        public void set_parser() throws IOException, XmlPullParserException {
            factory = XmlPullParserFactory.newInstance();
            parser = factory.newPullParser();
            int position=4;// ** 여기에 RegionActivity에서 선택한 지역구의 코드 가져오기. **
            String path = "http://api.visitkorea.or.kr/openapi/service/rest/EngService/areaCode?" +
                    "serviceKey=bywYnmxO22GvVcjVqX51LynXlyzrFTtMzTRM88W6rKGfO8VQtw6HycfhvB9ci6s9nH1fPDCOWQnabI9Htz7WDg%3D%3D" +
                    "&numOfRows=10&pageSize=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&areaCode=1"+
                    "&sigunguCode="+position;

            URL url = new URL(path);
            InputStream is = url.openStream();
            parser.setInput(new InputStreamReader(is, "UTF-8"));


        }

        public void parsing_loop() throws XmlPullParserException, IOException {
            final int STEP_NONE = 0;
            final int STEP_CONTENTID = 1;
            final int STEP_TITLE = 2;
            final int STEP_IMAGE = 3;
            final int STEP_ADDRESS=4;

            int step = STEP_NONE;
            String contentID = null;
            String title = null;
            String image = null;
            String address = null;

            int eventType = parser.getEventType();
//xml 형식:
// <item>
//<addr1>87, Heojun-ro, Gangseo-gu, Seoul</addr1>
//<areacode>1</areacode>
//<cat1>A02</cat1>
//<cat2>A0206</cat2>
//<cat3>A02060100</cat3>
//<contentid>268209</contentid>
//<contenttypeid>78</contenttypeid>
//<createdtime>20051007112611</createdtime>
//<firstimage>
//http://tong.visitkorea.or.kr/cms/resource/06/1570606_image2_1.jpg
//</firstimage>
//<firstimage2>
//http://tong.visitkorea.or.kr/cms/resource/06/1570606_image3_1.jpg
//</firstimage2>
//<mapx>126.8510492715</mapx>
//<mapy>37.5678379253</mapy>
//<masterid>130552</masterid>
//<mlevel>6</mlevel>
//<modifiedtime>20190318211454</modifiedtime>
//<readcount>13571</readcount>
//<sigungucode>4</sigungucode>
//<tel>+82-2-3661-8686</tel>
//<title>Heojun Museum (허준박물관)</title>
//<zipcode>07525</zipcode>
//</item>
//</items>


            // XML 파일의 시작.
            eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_DOCUMENT) {
                    parser.next();
                    eventType = parser.getEventType();
                }
                if (eventType == XmlPullParser.START_TAG) {
                    String startTag = parser.getName();
                    if (startTag.equals("addr1")) {
                        step = STEP_ADDRESS;
                    } else if (startTag.equals("contentid")) {
                        step = STEP_CONTENTID;
                    } else if (startTag.equals("firstimage")) {
                        step = STEP_IMAGE;
                    } else if (startTag.equals("title")) {
                        step = STEP_TITLE;
                    } else {
                        step = STEP_NONE;
                    }
                } else if (eventType == XmlPullParser.TEXT && step != STEP_NONE) {
                    String text = parser.getText();
                    if (step == STEP_CONTENTID) {
                        contentID = text;
                    } else if (step == STEP_ADDRESS) {
                       address = text;
                    } else if (step == STEP_IMAGE) {
                        image = text;
                    }else if (step == STEP_TITLE) {
                        title = text;
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    String endTag = parser.getName();
                    if (endTag.equals("item")) {

                        list_regionTourArrayList.add(
                                new RegionTour(contentID, title, image,address));
                        step = STEP_NONE;
                    }
                } else {

                }

                eventType = parser.next();
            }
        }

    }}

