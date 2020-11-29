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

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CityWeatherFragment extends BaseFragment implements View.OnClickListener{
    TextView tempTv,cityTv,dconditionTv,nconditionTv,windTv,tempRangeTv,dateTv;
    ImageView dayIv;
    ImageButton todIb;
    LinearLayout futureLayout;
    ScrollView outLayout;
    String url1 = "https://restapi.amap.com/v3/weather/weatherInfo?key=007394e485912ad187dd8081e2a7c046&city=";
    String url2 = "&extensions=all&output=json";
    String url3 = "&extensions=base&output=json";
    String city;


    public void loadData(String path, CommonCallback<String> callback) {
        RequestParams params = new RequestParams(path);
        x.http().get(params,callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city_weather, container, false);
        initView(view);
//        可以通过activity传值获取到当前fragment加载的是那个地方的天气情况
        Bundle bundle = getArguments();
        city = bundle.getString("city");
        String url = url1+city+url2;
       String todayurl = url1+city+url3;
        //      调用父类获取数据的方法
            loadData(todayurl, new CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    parseShowData(result);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });

        loadData(url);
        return view;
    }
    @Override
    public void onSuccess(String result) {
//        解析并展示数据
            parseShowData1(result);
            //         更新数据
            int i = DBManager.updateInfoByCity(city, result);
            if (i<=0) {
//            更新数据库失败，说明没有这条城市信息，增加这个城市记录
                DBManager.addCityInfo(city,result);
            }


    }


    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
//        数据库当中查找上一次信息显示在Fragment当中
        String s = DBManager.queryInfoByCity(city);
        if (!TextUtils.isEmpty(s)) {
            parseShowData(s);
        }

    }
    private void parseShowData(String result) {

        //        获取今日天气情况
        TodayBean todayBean = new Gson().fromJson(result, TodayBean.class);
        TodayBean.LivesDTO livesDTO = todayBean.getLives().get(0);
        dateTv.setText("更新时间：" + livesDTO.getReporttime());
        cityTv.setText(livesDTO.getCity());
        windTv.setText(livesDTO.getWinddirection() + "风" + livesDTO.getWindpower() + "级");
        tempRangeTv.setText(livesDTO.getTemperature() + "°C");
        nconditionTv.setText("天气情况：" + livesDTO.getWeather());
        tempTv.setText(livesDTO.getTemperature() + "°C");

    }
    private void parseShowData1(String result){

        //        使用gson解析数据
        WeatherBean weatherBean = new Gson().fromJson(result, WeatherBean.class);
        WeatherBean.ForecastsDTO forecastsDTO = weatherBean.getForecasts().get(0);
        //        设置TextView
        dateTv.setText("更新时间：" + forecastsDTO.getReporttime());
        cityTv.setText(forecastsDTO.getCity());
            //获取今天和未来三天的天气
            List<WeatherBean.ForecastsDTO.CastsDTO> futureList = forecastsDTO.getCasts();
           // futureList.remove(0);
            for (int i = 0; i < futureList.size(); i++) {
                View itemView = LayoutInflater.from(getActivity()).inflate(R.layout.item_main_center, null);
                itemView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                futureLayout.addView(itemView);
                TextView idateTv = itemView.findViewById(R.id.item_center_tv_date);
                TextView iconTv = itemView.findViewById(R.id.item_center_tv_con);
                TextView itemprangeTv = itemView.findViewById(R.id.item_center_tv_temp);
                //          获取对应的位置的天气情况
                WeatherBean.ForecastsDTO.CastsDTO dataBean = futureList.get(i);
                idateTv.setText(dataBean.getDate()+" ｜ 周"+change(dataBean.getWeek()));
                iconTv.setText(dataBean.getDayweather());
                itemprangeTv.setText(dataBean.getNighttemp()+"°C~"+dataBean.getDaytemp()+"°C");//
//                //  Picasso.get().load(dataBean.getDayPictureUrl()).into(iIv);
            }

    }

    private String change(String week){
        switch (week){
            case "1":
                week = "一";break;
            case "2":
                week = "二";break;
            case "3":
                week = "三";break;
            case "4":
                week = "四";break;
            case "5":
                week = "五";break;
            case "6":
                week = "六";break;
            case "7":
                week = "七";break;
        }
        return week;
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
