package lambdasinaction.testjson;

import com.alibaba.fastjson.JSON;

import java.util.*;

public class Test2Json {
    public static void main(String[] args) {
        Map<String,String> map1 = new HashMap(16);
        map1.put("definition","value12");
        map1.put("url","value13");

        Map<String,String> map2 = new HashMap(16);
        map2.put("m1key1","value21");
        map2.put("definition","value22");
        map2.put("url3","value23");
/*
        Map<String,String> map3 = new HashMap(16);
        map3.put("definition","value32");
        map3.put("url","value33");

        Map<String,String> map4 = new HashMap(16);
        map4.put("definition","value42");
        map4.put("url","value43");*/

        List<Map<String,String>> list = new ArrayList();
        list.add(map1);
        list.add(map2);


        CarDetailVideo carDetailVideo = new CarDetailVideo();
        carDetailVideo.setId(1);
        carDetailVideo.setName("test");
        carDetailVideo.setSources(list);

        String s = JSON.toJSONString(carDetailVideo);
        System.out.println(s);
        CarDetailVideo2Json carDetailVideo2Json = JSON.parseObject(s, CarDetailVideo2Json.class);
        System.out.println(carDetailVideo2Json);




        /*String json = "{\\\"duration\\\":\\\"10”\\\",\\\"id\\\":13386,\\\"modelCode\\\":\\\"000233\\\",\\\"name\\\":\\\"mov_bbbB\\\",\\\"signCode\\\":\\\"860c2d7f1c8848b9a1a09c873dba5978\\\",\\\"sort\\\":100,\\\"sources\\\":[{\\\"name\\\":\\\"mov_bbbB\\\",\\\"duration\\\":\\\"10”\\\",\\\"id\\\":\\\"13386\\\",\\\"definition\\\":\\\"原画质\\\",\\\"url\\\":\\\"http://souche-devqa-video-in.oss-cn-hangzhou.aliyuncs.com/material/20170815/mp4/75f280ff86eb35f0aac6e16a9c3f1ab3.mp4\\\"},{\\\"name\\\":\\\"mov_bbbB\\\",\\\"duration\\\":\\\"10”\\\",\\\"id\\\":\\\"13387\\\",\\\"definition\\\":\\\"高画质\\\",\\\"url\\\":\\\"http://souche-devqa-video-out.oss-cn-hangzhou.aliyuncs.com/material/videos/Act-ss-mp4-hd/75f280ff86eb35f0aac6e16a9c3f1ab3/2bbf598c9fe04bf0a8aa8d127faf4f84_75f280ff86eb35f0aac6e16a9c3f1ab3.mp4\\\"},{\\\"name\\\":\\\"mov_bbbB\\\",\\\"duration\\\":\\\"10”\\\",\\\"id\\\":\\\"13389\\\",\\\"definition\\\":\\\"标清\\\",\\\"url\\\":\\\"http://souche-devqa-video-out.oss-cn-hangzhou.aliyuncs.com/material/videos/Act-ss-mp4-sd/75f280ff86eb35f0aac6e16a9c3f1ab3/2bbf598c9fe04bf0a8aa8d127faf4f84_75f280ff86eb35f0aac6e16a9c3f1ab3.mp4\\\"},{\\\"name\\\":\\\"mov_bbbB\\\",\\\"duration\\\":\\\"10”\\\",\\\"id\\\":\\\"13388\\\",\\\"definition\\\":\\\"流畅\\\",\\\"url\\\":\\\"http://souche-devqa-video-out.oss-cn-hangzhou.aliyuncs.com/material/videos/Act-ss-mp4-ld/75f280ff86eb35f0aac6e16a9c3f1ab3/2bbf598c9fe04bf0a8aa8d127faf4f84_75f280ff86eb35f0aac6e16a9c3f1ab3.mp4\\\"}],\\\"thumb\\\":\\\"http://devqa-image.souche.com/20170815/jpeg/27cada43f3175fa47765d2c50b992f89.jpeg\\\",\\\"value\\\":null}";
        CarDetailVideo2Json carDetailVideo2Json1 = JSON.parseObject(json, CarDetailVideo2Json.class);
        System.out.println( JSON.toJSONString(carDetailVideo2Json1));*/

    }
}
