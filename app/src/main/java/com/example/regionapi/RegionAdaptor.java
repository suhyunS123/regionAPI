package com.example.regionapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class RegionAdaptor extends BaseAdapter {

    Context context;
    ArrayList<Region> list_regionArrayList;

    TextView name_textView;

    public RegionAdaptor(Context context, ArrayList<Region> list_regionArrayList)
    {
        this.context=context;
        this.list_regionArrayList=list_regionArrayList;
    }
    @Override
    public int getCount() {
        return this.list_regionArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        //현재 어떤 아이템인지 알려주는 부분. list_regionArrayList에 저장된 객체중 position에 해당되는
        //것을 가져올 것.
        return list_regionArrayList.get(position);
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

            name_textView = (TextView) convertView.findViewById(R.id.name_textview);
            //**************
            name_textView.setText(list_regionArrayList.get(position).getName());

        }
        return convertView;
    }

    public void addRegion(String code, String name, String rnum) {
       Region Ritem = new Region();

        Ritem.setCode(code);
        Ritem.setName(name);
        Ritem.setRnum(rnum);
        list_regionArrayList.add(Ritem);
    }

    public void removeAll() {
        list_regionArrayList.removeAll(list_regionArrayList);
    }


}
