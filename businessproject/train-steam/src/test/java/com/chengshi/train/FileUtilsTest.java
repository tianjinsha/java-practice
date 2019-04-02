package com.chengshi.train;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chengshi.trai.domain.Track;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

@Slf4j
public class FileUtilsTest {

    @Test
    public void test1() throws IOException {
        File file = ResourceUtils.getFile("classpath:track1.json");
        String s = FileUtils.readFileToString(file, Charset.forName("utf-8"));
        System.out.println(s);
        JSONObject jsonObject = JSONObject.parseObject(s);
        Track track1 = JSONObject.parseObject(s, Track.class);
        Track track = JSON.toJavaObject(jsonObject, Track.class);
        System.out.println(track);
        System.out.println(track1);
    }


    @Test
    public void test2() throws IOException {
        File file = ResourceUtils.getFile("classpath:data.json");
        String s = FileUtils.readFileToString(file, Charset.forName("utf-8"));
        System.out.println(s);
        List<Track> tracks = JSONObject.parseArray(s, Track.class);
        System.out.println(tracks);
        String s1 = JSON.toJSONString(tracks);
        System.out.println(s1);
    }

}
