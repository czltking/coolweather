package czlt.androidstu.coolweather.util;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import czlt.androidstu.coolweather.db.City;
import czlt.androidstu.coolweather.db.County;
import czlt.androidstu.coolweather.db.Province;

/**
 * Created by lubul on 2018/12/17.
 */

public class Utility {

    /*****
     * 解析和处理服务器返回的省级数据
     ***/
    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject proObj = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(proObj.getString("name"));
                    province.setProvinceCode(proObj.getInt("id"));
                    province.save();
                }
                return true;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    /*****
     * 解析和处理服务器返回的市级数据
     ***/
    public static boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i < allCities.length(); i++) {
                    JSONObject cityObj = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObj.getString("name"));
                    city.setCityCode(cityObj.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    /*****
     * 解析和处理服务器返回的县级数据
     ***/
    public static boolean handleCountyResponse(String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++) {
                    JSONObject countyObj = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObj.getString("name"));
                    county.setWeatherId(countyObj.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
}
