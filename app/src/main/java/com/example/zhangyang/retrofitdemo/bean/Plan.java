package com.example.zhangyang.retrofitdemo.bean;

import java.util.List;

/**
 * Created by zhangyang on 2017/9/4.
 */

public class Plan {
    /**
     * status : 1
     * info : OK
     * times : 0
     * data : {"place":["阿根廷","北海道","尼泊尔","新西兰","日本","东京","冰岛","夏威夷","西班牙","台湾","冲绳","摩洛哥"],"hotel_city":[{"keyword":"京都的特惠酒店","city_id":"65","city_name":"京都","mark":"热门","url":"https://hotel.qyer.com/goto.php?url=http%3A%2F%2Fwww.booking.com%2Fsearchresults.html%3Faid%3D355989%26si%3Dai%252Cco%252Cci%252Cre%252Cdi%26label%3Dguid_16390be9-637b-b680-3c02-cb7ad38164a1_datetime_15045941765337_cityid_65_aaid_355989-search-hotkeyword_device_867389021584965%26lang%3Dzh-cn%26ifl%3D%26ss%3D%25E4%25BA%25AC%25E9%2583%25BD"},{"keyword":"巴黎的特惠酒店","city_id":"20","city_name":"巴黎","mark":"热门","url":"https://hotel.qyer.com/goto.php?url=http%3A%2F%2Fwww.booking.com%2Fsearchresults.html%3Faid%3D355989%26si%3Dai%252Cco%252Cci%252Cre%252Cdi%26label%3Dguid_16390be9-637b-b680-3c02-cb7ad38164a1_datetime_15045941767160_cityid_20_aaid_355989-search-hotkeyword_device_867389021584965%26lang%3Dzh-cn%26ifl%3D%26ss%3D%25E5%25B7%25B4%25E9%25BB%258E"}],"zk":[{"keyword":"自驾租车","city_id":"","city_name":"","mark":"","url":"https://m.qyer.com/z/car/?utm_source=c03729731-m&utm_medium=partner&utm_campaign=entry&utm_term=hot_search"}]}
     */

    private List<String> place;
    private List<HotelCityBean> hotel_city;
    private List<ZkBean> zk;

    public List<String> getPlace() {
        return place;
    }

    public void setPlace(List<String> place) {
        this.place = place;
    }

    public List<HotelCityBean> getHotel_city() {
        return hotel_city;
    }

    public void setHotel_city(List<HotelCityBean> hotel_city) {
        this.hotel_city = hotel_city;
    }

    public List<ZkBean> getZk() {
        return zk;
    }

    public void setZk(List<ZkBean> zk) {
        this.zk = zk;
    }

    public static class HotelCityBean {
        /**
         * keyword : 京都的特惠酒店
         * city_id : 65
         * city_name : 京都
         * mark : 热门
         * url : https://hotel.qyer.com/goto.php?url=http%3A%2F%2Fwww.booking.com%2Fsearchresults.html%3Faid%3D355989%26si%3Dai%252Cco%252Cci%252Cre%252Cdi%26label%3Dguid_16390be9-637b-b680-3c02-cb7ad38164a1_datetime_15045941765337_cityid_65_aaid_355989-search-hotkeyword_device_867389021584965%26lang%3Dzh-cn%26ifl%3D%26ss%3D%25E4%25BA%25AC%25E9%2583%25BD
         */

        private String keyword;
        private String city_id;
        private String city_name;
        private String mark;
        private String url;

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class ZkBean {
        /**
         * keyword : 自驾租车
         * city_id :
         * city_name :
         * mark :
         * url : https://m.qyer.com/z/car/?utm_source=c03729731-m&utm_medium=partner&utm_campaign=entry&utm_term=hot_search
         */

        private String keyword;
        private String city_id;
        private String city_name;
        private String mark;
        private String url;

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
