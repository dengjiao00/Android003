package com.example.weather.city_manager;
/**
 * Create By Dengjiao
 * on 2020.11.02
 */
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.weather.MainActivity;
import com.example.weather.R;
import com.example.weather.base.BaseActivity;
import com.example.weather.bean.WeatherBean;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SearchCityActivity extends BaseActivity implements View.OnClickListener{
    EditText searchEt;
    ImageView submitIv;
    GridView searchGv;
    private static final String TAG = "SearchCityActivity";
    private Spinner province_spinner;
    private Spinner city_spinner;
    private Spinner county_spinner;
    // 从json中获取的省份天气信息列表
    private JSONArray provinceList;
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city);
        searchEt = findViewById(R.id.search_et);
        submitIv = findViewById(R.id.search_iv_submit);
        searchGv = findViewById(R.id.search_gv);
        submitIv.setOnClickListener(this);
        init();
//        设置适配器
        adapter = new ArrayAdapter<>(this, R.layout.item_hotcity, hotCitys);
        searchGv.setAdapter(adapter);
        setListener();
    }
    /* 设置监听事件*/
    private void setListener() {
        searchGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                city = hotCitys[position];
                String url = url1+city+url2;
                loadData(url);
            }
        });
    }



    /**
     * 初始化操作：
     * 1. 加载城市信息
     * 2. 实例化各个spinner并为它们添加选中事件
     */
    private void init() {
        province_spinner = (Spinner) this.findViewById(R.id.province_spinner);
        city_spinner = (Spinner) this.findViewById(R.id.city_spinner);
        county_spinner = (Spinner) this.findViewById(R.id.county_spinner);
        //========================================================================

        try {
            // 获取json字符串
            String jsonString = getJsonStringFromFile("province.json");
            // 解析json字符串获取所以省级别的天气信息
            provinceList = new JSONObject(jsonString).getJSONArray("districts").getJSONObject(0)
                    .getJSONArray("districts");
            loadProvinceSpinner();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        // 添加省级spinner点击事件
        province_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                try {
                    loadCitySpinner();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @SuppressLint("ShowToast")
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // 该方法调用的时机：https://blog.csdn.net/u012702547/article/details/50598673
                Toast.makeText(SearchCityActivity.this, "什么都没有选中哦！", Toast.LENGTH_SHORT);
            }
        });

        // 添加市级spinner点击事件
        city_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    loadCountySpinner();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @SuppressLint("ShowToast")
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(SearchCityActivity.this, "什么都没有选中哦！", Toast.LENGTH_SHORT);
            }
        });

        // 添加县级spinner点击事件
        county_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    loadCountySpinner();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @SuppressLint("ShowToast")
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(SearchCityActivity.this, "什么都没有选中哦！", Toast.LENGTH_SHORT);
            }
        });
        county_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str = (String)parent.getSelectedItem();
                searchEt.setText(str);
                Toast.makeText(SearchCityActivity.this, "选中："+parent.getSelectedItem(), Toast.LENGTH_SHORT);
            }
            @SuppressLint("ShowToast")
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(SearchCityActivity.this, "什么都没有选中哦！", Toast.LENGTH_SHORT);
            }
        });

    }

    /**
     * 解析城市信息对应的json文件
     *
     * @param fileName 待解析的json文件的文件名
     * @return json文件内容转换后的json字符串
     * @throws IOException IO异常
     */
    public String getJsonStringFromFile(String fileName) throws IOException {
        InputStreamReader isr = new InputStreamReader(getAssets().open(fileName), "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = br.readLine()) != null) {
            builder.append(line);
        }
        br.close();
        isr.close();
        return builder.toString();
    }


    /**
     * 加载省份spinner
     *
     * @throws JSONException json编码异常
     */
    private void loadProvinceSpinner() throws JSONException {
        // 获取所有省份名称列表
        List<String> provinceNameList = new ArrayList<>();
        for (int i = 0; i < provinceList.length(); i++) {
            provinceNameList.add(provinceList.getJSONObject(i).getString("name"));
        }
        // 设置Adapter
        ArrayAdapter<String> adapterProvince = new ArrayAdapter<String>(SearchCityActivity.this, R.layout.item_spinner, R.id.putxt, provinceNameList);
        province_spinner.setAdapter(adapterProvince);

    }

    /**
     * 加载市级spinner
     *
     * @throws JSONException json编码异常
     */
    private void loadCitySpinner() throws JSONException {
        int provinceSelectItemPosition = province_spinner.getSelectedItemPosition();
        JSONArray cityList = provinceList.getJSONObject(provinceSelectItemPosition).getJSONArray("districts");
        List<String> cityNameList = new ArrayList<>();
        for (int i = 0; i < cityList.length(); i++) {
            cityNameList.add(cityList.getJSONObject(i).getString("name"));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(SearchCityActivity.this, R.layout.item_spinner, R.id.putxt, cityNameList);
        city_spinner.setAdapter(adapter);
    }

    /**
     * 加载县级spinner
     *
     * @throws JSONException json编码异常
     */
    private void loadCountySpinner() throws JSONException {
        int provinceSelectItemPosition = province_spinner.getSelectedItemPosition();
        int citySelectItemPosition = city_spinner.getSelectedItemPosition();
        JSONArray countyList = provinceList.getJSONObject(provinceSelectItemPosition).getJSONArray("districts")
                .getJSONObject(citySelectItemPosition).getJSONArray("districts");
        List<String> countyNameList = new ArrayList<>();
        for (int i = 0; i < countyList.length(); i++) {
            countyNameList.add(countyList.getJSONObject(i).getString("name"));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SearchCityActivity.this, R.layout.item_spinner, R.id.putxt, countyNameList);
        county_spinner.setAdapter(adapter);


    }



    String[]hotCitys = {"北京","上海","广州","深圳","珠海","佛山","南京","苏州","厦门","长沙","成都","福州",
            "杭州","武汉","青岛","西安","沈阳","重庆","天津","三亚","呼和浩特","昆明","长沙","兰州","桂林","太原","海口","澳门","石家庄","拉萨"};
    private ArrayAdapter<String> adapter;
    String url1 = "https://restapi.amap.com/v3/weather/weatherInfo?key=007394e485912ad187dd8081e2a7c046&city=";
    String url2 = "&extensions=all&output=json";
    String city;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_iv_submit:

                city = searchEt.getText().toString();
                if (!TextUtils.isEmpty(city)) {
//                      判断是否能够找到这个城市
                        String url = url1+city;
                        loadData(url);
                }else {
                    Toast.makeText(this,"查询城市不能为空！",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onSuccess(String result) {
        WeatherBean weatherBean = new Gson().fromJson(result, WeatherBean.class);
        if (!"0".equals(weatherBean.getCount())) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("city", city);
            startActivity(intent);
        } else {
            Toast.makeText(this, "暂时未查询到此城市天气信息！", Toast.LENGTH_SHORT).show();
        }
    }
}
