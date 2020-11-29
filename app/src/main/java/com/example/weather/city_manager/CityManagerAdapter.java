package com.example.weather.city_manager;
/**
 * Create By Dengjiao
 * on 2020.11.02
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.weather.R;
import com.example.weather.bean.WeatherBean;
import com.example.weather.db.DatabaseBean;
import com.google.gson.Gson;

import java.util.List;

public class CityManagerAdapter extends BaseAdapter{
    Context context;
    List<DatabaseBean>mDatas;

    public CityManagerAdapter(Context context, List<DatabaseBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_city_manager,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        DatabaseBean bean = mDatas.get(position);
        holder.cityTv.setText(bean.getCity());
        WeatherBean weatherBean = new Gson().fromJson(bean.getContent(), WeatherBean.class);
//        获取今日天气情况
        WeatherBean.ForecastsDTO.CastsDTO dataBean = weatherBean.getForecasts().get(0).getCasts().get(0);
        holder.dconTv.setText("白天天气:"+dataBean.getDayweather());
        holder.nconTv.setText("夜晚天气:"+dataBean.getNightweather());
        String[] split = dataBean.getDate().split("：");
        //String todayTemp = split[1].replace(")", "");
        String todayTemp = split[0];
        holder.windTv.setText(dataBean.getDaywind()+"风");

        holder.tempRangeTv.setText(dataBean.getNighttemp()+"°C~"+dataBean.getDaytemp()+"°C");
        return convertView;
    }

    class ViewHolder{
        TextView cityTv,dconTv,nconTv,currentTempTv,windTv,tempRangeTv;
        public ViewHolder(View itemView){
            cityTv = itemView.findViewById(R.id.item_city_tv_city);
            dconTv = itemView.findViewById(R.id.item_city_tv_dcondition);
            nconTv = itemView.findViewById(R.id.item_city_tv_ncondition);
            currentTempTv = itemView.findViewById(R.id.item_city_tv_temp);
            windTv = itemView.findViewById(R.id.item_city_wind);
            tempRangeTv = itemView.findViewById(R.id.item_city_temprange);

        }
    }
}
