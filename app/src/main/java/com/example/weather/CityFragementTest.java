package com.example.weather;
/**
 * Create By Dengjiao
 * on 2020.11.02
 */

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.weather.base.BaseFragment;
import com.example.weather.bean.TodayBean;
import com.example.weather.bean.WeatherBean;
import com.example.weather.db.DBManager;
import com.google.gson.Gson;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CityFragementTest extends BaseFragment implements View.OnClickListener{
    private boolean flag = true,flag1 = true;
    TextView tempTv,cityTv,dconditionTv,nconditionTv,windTv,tempRangeTv,dateTv;
    ImageView dayIv;
    LinearLayout futureLayout;
    ScrollView outLayout;
    String url1 = "https://restapi.amap.com/v3/weather/weatherInfo?key=007394e485912ad187dd8081e2a7c046&city=";
    String url2 = "&extensions=all&output=json";
    String city;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city_weather, container, false);
        initView(view);
//        可以通过activity传值获取到当前fragment加载的是那个地方的天气情况
        Bundle bundle = getArguments();
        city = bundle.getString("city");
        String url = url1+city+url2;
        //      调用父类获取数据的方法
           loadData(url);
        return view;
    }
    @Override
    public void onSuccess(String result) {
            parseShowData1(result);
            //         更新数据
            int i = DBManager.updateInfoByCity(city, result);
            if (i<=0) {
//            更新数据库失败，说明没有这条城市信息，增加这个城市记录
                DBManager.addCityInfo(city, result);
            }
    }
    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
//        数据库当中查找上一次信息显示在Fragment当中
        String s = DBManager.queryInfoByCity(city);
        if (!TextUtils.isEmpty(s)) {
            parseShowData1(s);
        }

    }
    public void parseShowData1(String result){
        //        使用gson解析数据
        WeatherBean weatherBean = new Gson().fromJson(result, WeatherBean.class);
        WeatherBean.ForecastsDTO forecastsDTO = weatherBean.getForecasts().get(0);
        //        设置TextView
        dateTv.setText("最近更新时间：" + forecastsDTO.getReporttime());
        cityTv.setText(forecastsDTO.getCity());
//        获取未来三天的天气情况，加载到layout当中
        List<WeatherBean.ForecastsDTO.CastsDTO> futureList = forecastsDTO.getCasts();
        futureList.remove(0);
        for (int i = 0; i < futureList.size(); i++) {
            View itemView = LayoutInflater.from(getActivity()).inflate(R.layout.item_main_center, null);
            itemView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            futureLayout.addView(itemView);
            TextView idateTv = itemView.findViewById(R.id.item_center_tv_date);
            TextView iconTv = itemView.findViewById(R.id.item_center_tv_con);
            TextView itemprangeTv = itemView.findViewById(R.id.item_center_tv_temp);
            //          获取对应的位置的天气情况
            WeatherBean.ForecastsDTO.CastsDTO dataBean = futureList.get(i);
            idateTv.setText(dataBean.getDate());
            iconTv.setText(dataBean.getDayweather());
            itemprangeTv.setText(dataBean.getDaytemp());//
//                //  Picasso.get().load(dataBean.getDayPictureUrl()).into(iIv);
        }

    }

    private void initView(View view) {
//        用于初始化控件操作
        tempTv = view.findViewById(R.id.frag_tv_currenttemp);
        cityTv = view.findViewById(R.id.frag_tv_city);
        nconditionTv = view.findViewById(R.id.frag_tv_ncondition);
        windTv = view.findViewById(R.id.frag_tv_wind);
        tempRangeTv = view.findViewById(R.id.frag_tv_temprange);
        dateTv = view.findViewById(R.id.frag_tv_date);
        futureLayout = view.findViewById(R.id.frag_center_layout);
        outLayout = view.findViewById(R.id.out_layout);
//        todIb = view.findViewById(R.id.frag_iv_today);
//        todIb.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        Intent intent = new Intent();
//        switch (v.getId()){
//            case R.id.frag_iv_today:
//                intent.setClass(this, MainActivity.cl);
//                break;
//        }

    }

}
