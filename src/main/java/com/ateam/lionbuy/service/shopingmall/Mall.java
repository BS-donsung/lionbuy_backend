package com.ateam.lionbuy.service.shopingmall;

import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Mall {
    
    public Object[] mall_parsing(String link) {
        Document document;
        Elements images;
        ArrayList<String> imageList = new ArrayList<String>();
        Object[] pd_list = new Object[3];
        String pdName;
        String price;
        String name = mall_name(link);
        switch(name) {
            case "11번가":
                document = jsoup_conn(link);
                pdName = document.getElementsByTag("title").text();
                price = document.select("div.price_block ul.price_wrap li dl.price dd strong span.value").first().text();
                images = document.select("#productImg img");
                for (Element image : images) {
                    String imageUrl = image.attr("src");
                    imageList.add(imageUrl);
                }
                pd_list[0] = pdName;
                pd_list[1] = price;
                pd_list[2] = imageList;
                return pd_list;
            case "옥션":
                document = jsoup_conn(link);
                pdName = document.getElementsByClass("itemtit").text();
                price = document.getElementsByClass("price_real").text();
                images = document.select(".box__viewer-container .viewer li.on img");
                for (Element image : images) {
                    String imageUrl = image.attr("src");
                    imageList.add(imageUrl);
                }
                pd_list[0] = pdName;
                pd_list[1] = price;
                // pd_list[2] = imageList;
                pd_list[2] = imageList;
                return pd_list;
            case "G마켓":
                document = jsoup_conn(link);
                pdName = document.getElementsByClass("itemtit").text();
                price = document.select("div.price span.price_innerwrap strong.price_real").text();
                images = document.select(".box__viewer-container .viewer li.on img");
                for (Element image : images) {
                    String imageUrl = image.attr("src");
                    imageList.add(imageUrl);
                }
                pd_list[0] = pdName;
                pd_list[1] = price;
                pd_list[2] = imageList;
                return pd_list;
            case "인터파크":
                document = jsoup_conn(link);
                pdName = document.getElementsByTag("title").text().split(" - ")[0];
                price = document.select("span.salePrice em").text();
                images = document.select("div.viewImage div.bx-wrapper div.bx-viewport ul.resized img");
                for (Element image1 : images) {
                    String imageUrl = image1.attr("src");
                    imageList.add(imageUrl);
                }
                pd_list[0] = pdName;
                pd_list[1] = price;
                pd_list[2] = imageList;
                return pd_list;
            case "e마트":
                document = jsoup_conn(link);
                pdName = document.getElementsByClass("cdtl_info_tit_txt").text();
                price = document.select("em.ssg_price").first().text();
                images = document.select("div.cdtl_product_representative_image div.cdtl_item_image img");
                for (Element image1 : images) {
                    String imageUrl = image1.attr("src");
                    imageList.add(imageUrl);
                }
                pd_list[0] = pdName;
                pd_list[1] = price;
                pd_list[2] = imageList;
                return pd_list;
            case "신세계몰":
                document = jsoup_conn(link);
                pdName = document.getElementsByClass("cdtl_info_tit_txt").text();
                price = document.select("em.ssg_price").first().text();
                images = document.select("div.cdtl_product_representative_image div.cdtl_item_image img");
                for (Element image1 : images) {
                    String imageUrl = image1.attr("src");
                    imageList.add(imageUrl);
                }
                pd_list[0] = pdName;
                pd_list[1] = price;
                pd_list[2] = imageList;
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
