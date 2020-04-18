package com.example.regionapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RegionTourAdaptor extends BaseAdapter {
    Context context;
    ArrayList<RegionTour> list_regionTourArrayList;

    TextView regiontour_textView;

    public RegionTourAdaptor(Context context, ArrayList<RegionTour> list_regionTourArrayList)
    {
        this.context=context;
        this.list_regionTourArrayList=list_regionTourArrayList;
    }
    @Override
    public int getCount() {
        return this.list_regionTourArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        //현재 어떤 아이템인지 알려주는 부분. list_regionArrayList에 저장된 객체중 position에 해당되는
        //것을 가져올 것.
        return list_regionTourArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        //position의 위치를 알려준다.
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //아이템과 xml을 연결하여 화면에 표시.
        // listview는 Activity가 아니므로 contextfmf 생성자를 이용하여 받은것.
        //convertView에 listview.xml을 불러옴.


        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_region,null);

            regiontour_textView = (TextView) convertView.findViewById(R.id.regiontour_textview);
            //***************이상함
           regiontour_textView.setText(list_regionTourArrayList.get(position).getTitle());

        }
        return convertView;
    }

    public void addRegion(String contentID, String title, String image, String address) {
        RegionTour Ritem = new RegionTour();

        Ritem.setContentID(contentID);
        Ritem.setTitle(title);
        Ritem.setImage(image);
        Ritem.setAdrress(address);
        list_regionTourArrayList.add(Ritem);
    }

    public void removeAll() {
        list_regionTourArrayList.removeAll(list_regionTourArrayList);
    }

}
