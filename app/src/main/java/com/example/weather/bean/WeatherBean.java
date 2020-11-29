package com.example.weather.bean;
/**
 * Create By Dengjiao
 * on 2020.11.02
 */
import java.util.List;

//@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherBean {
    /**
     * status : 1
     * count : 1
     * info : OK
     * infocode : 10000
     * forecasts : [{"city":"北京市","adcode":"110000","province":"北京","reporttime":"2020-11-14 11:30:45","casts":[{"date":"2020-11-14","week":"6","dayweather":"多云","nightweather":"多云","daytemp":"14","nighttemp":"4","daywind":"东南","nightwind":"东南","daypower":"≤3","nightpower":"≤3"},{"date":"2020-11-15","week":"7","dayweather":"多云","nightweather":"多云","daytemp":"17","nighttemp":"5","daywind":"西南","nightwind":"西南","daypower":"≤3","nightpower":"≤3"},{"date":"2020-11-16","week":"1","dayweather":"多云","nightweather":"阴","daytemp":"14","nighttemp":"7","daywind":"东北","nightwind":"东北","daypower":"≤3","nightpower":"≤3"},{"date":"2020-11-17","week":"2","dayweather":"阴","nightweather":"小雨","daytemp":"13","nighttemp":"9","daywind":"北","nightwind":"北","daypower":"≤3","nightpower":"≤3"}]}]
     */

  //  @com.fasterxml.jackson.annotation.JsonProperty("status")
    private String status;
 //   @com.fasterxml.jackson.annotation.JsonProperty("count")
    private String count;
  //  @com.fasterxml.jackson.annotation.JsonProperty("info")
    private String info;
  //  @com.fasterxml.jackson.annotation.JsonProperty("infocode")
    private String infocode;
   // @com.fasterxml.jackson.annotation.JsonProperty("forecasts")
    private List<ForecastsDTO> forecasts;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfocode() {
        return infocode;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }

    public List<ForecastsDTO> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<ForecastsDTO> forecasts) {
        this.forecasts = forecasts;
    }



    //  @com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
    public static class ForecastsDTO  {
        /**
         * city : 北京市
         * adcode : 110000
         * province : 北京
         * reporttime : 2020-11-14 11:30:45
         * casts : [{"date":"2020-11-14","week":"6","dayweather":"多云","nightweather":"多云","daytemp":"14","nighttemp":"4","daywind":"东南","nightwind":"东南","daypower":"≤3","nightpower":"≤3"},{"date":"2020-11-15","week":"7","dayweather":"多云","nightweather":"多云","daytemp":"17","nighttemp":"5","daywind":"西南","nightwind":"西南","daypower":"≤3","nightpower":"≤3"},{"date":"2020-11-16","week":"1","dayweather":"多云","nightweather":"阴","daytemp":"14","nighttemp":"7","daywind":"东北","nightwind":"东北","daypower":"≤3","nightpower":"≤3"},{"date":"2020-11-17","week":"2","dayweather":"阴","nightweather":"小雨","daytemp":"13","nighttemp":"9","daywind":"北","nightwind":"北","daypower":"≤3","nightpower":"≤3"}]
         */

    //    @com.fasterxml.jackson.annotation.JsonProperty("city")
        private String city;
   //   @com.fasterxml.jackson.annotation.JsonProperty("adcode")
        private String adcode;
    //    @com.fasterxml.jackson.annotation.JsonProperty("province")
        private String province;
     //   @com.fasterxml.jackson.annotation.JsonProperty("reporttime")
        private String reporttime;
      //  @com.fasterxml.jackson.annotation.JsonProperty("casts")
        private List<CastsDTO> casts;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAdcode() {
            return adcode;
        }

        public void setAdcode(String adcode) {
            this.adcode = adcode;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getReporttime() {
            return reporttime;
        }

        public void setReporttime(String reporttime) {
            this.reporttime = reporttime;
        }

        public List<CastsDTO> getCasts() {
            return casts;
        }

        public void setCasts(List<CastsDTO> casts) {
            this.casts = casts;
        }

     //   @com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
        public static class CastsDTO  {
            /**
             * date : 2020-11-14
             * week : 6
             * dayweather : 多云
             * nightweather : 多云
             * daytemp : 14
             * nighttemp : 4
             * daywind : 东南
             * nightwind : 东南
             * daypower : ≤3
             * nightpower : ≤3
             */

      //      @com.fasterxml.jackson.annotation.JsonProperty("date")
            private String date;
       //     @com.fasterxml.jackson.annotation.JsonProperty("week")
            private String week;
       //     @com.fasterxml.jackson.annotation.JsonProperty("dayweather")
            private String dayweather;
       //     @com.fasterxml.jackson.annotation.JsonProperty("nightweather")
            private String nightweather;
       //     @com.fasterxml.jackson.annotation.JsonProperty("daytemp")
            private String daytemp;
       //     @com.fasterxml.jackson.annotation.JsonProperty("nighttemp")
            private String nighttemp;
       //     @com.fasterxml.jackson.annotation.JsonProperty("daywind")
            private String daywind;
      //      @com.fasterxml.jackson.annotation.JsonProperty("nightwind")
            private String nightwind;
      //     @com.fasterxml.jackson.annotation.JsonProperty("daypower")
            private String daypower;
       //     @com.fasterxml.jackson.annotation.JsonProperty("nightpower")
            private String nightpower;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getDayweather() {
                return dayweather;
            }

            public void setDayweather(String dayweather) {
                this.dayweather = dayweather;
            }

            public String getNightweather() {
                return nightweather;
            }

            public void setNightweather(String nightweather) {
                this.nightweather = nightweather;
            }

            public String getDaytemp() {
                return daytemp;
            }

            public void setDaytemp(String daytemp) {
                this.daytemp = daytemp;
            }

            public String getNighttemp() {
                return nighttemp;
            }

            public void setNighttemp(String nighttemp) {
                this.nighttemp = nighttemp;
            }

            public String getDaywind() {
                return daywind;
            }

            public void setDaywind(String daywind) {
                this.daywind = daywind;
            }

            public String getNightwind() {
                return nightwind;
            }

            public void setNightwind(String nightwind) {
                this.nightwind = nightwind;
            }

            public String getDaypower() {
                return daypower;
            }

            public void setDaypower(String daypower) {
                this.daypower = daypower;
            }

            public String getNightpower() {
                return nightpower;
            }

            public void setNightpower(String nightpower) {
                this.nightpower = nightpower;
            }
        }
    }
}
