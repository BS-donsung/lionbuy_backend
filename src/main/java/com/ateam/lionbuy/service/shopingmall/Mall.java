package com.ateam.lionbuy.service.shopingmall;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Mall {
    
    public String[] mall_parsing(String link) {
        Document document;
        String[] pd_list = new String[2];
        String pd_name;
        String price;
        String name = mall_name(link);
        switch(name) {
            case "11번가":
                document = jsoup_conn(link);
                pd_name = document.getElementsByTag("title").text();
                price = document.select("span.value").first().text();
                pd_list[0] = pd_name;
                pd_list[1] = price;
                return pd_list;
            case "옥션":
                document = jsoup_conn(link);
                pd_name = document.getElementsByClass("itemtit").text();
                price = document.getElementsByClass("price_real").text();
                pd_list[0] = pd_name;
                pd_list[1] = price;
                return pd_list;
            case "G마켓":
                document = jsoup_conn(link);
                pd_name = document.getElementsByClass("itemtit").text();
                price = document.getElementsByClass("price_real").text();
                pd_list[0] = pd_name;
                pd_list[1] = price;
                return pd_list;
            case "인터파크":
                document = jsoup_conn(link); 
                pd_name = document.getElementsByTag("title").text().split(" - ")[0];
                // price = document.select("span.salePrice.em").text();
                pd_list[0] = pd_name;
                // pd_list[1] = price;
                return pd_list;
            case "e마트":
                document = jsoup_conn(link);
                pd_name = document.getElementsByClass("cdtl_info_tit_name").text();
                price = document.select("em.ssg_price").first().text();
                pd_list[0] = pd_name;
                pd_list[1] = price;
                return pd_list;
            case "신세계몰":
                document = jsoup_conn(link);
                pd_name = document.getElementsByClass("cdtl_info_tit_name").text();
                price = document.select("em.ssg_price").first().text();
                pd_list[0] = pd_name;
                pd_list[1] = price;
                return pd_list;
        }
        return null;
    }

    String mall_name(String link) {
        if(link.contains("11st.co.kr")) {
            return "11번가";
        }else if(link.contains("auction.co.kr")) {
            return "옥션";
        }else if(link.contains("gmarket.co.kr")) {
            return "G마켓";
        }else if(link.contains("shopping.interpark.com")) {
            return "인터파크";
        }else if(link.contains("emart.ssg.com")) {
            return "e마트";
        }else if(link.contains("shinsegaemall.ssg.com")) {
            return "신세계몰";
        }else {
            return null;
        }
    }

    Document jsoup_conn(String link) {
        String url = link;
        Connection conn = Jsoup.connect(url);

        try {
            Document document = conn.get();
            return document;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
