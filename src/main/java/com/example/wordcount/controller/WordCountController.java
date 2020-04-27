package com.example.wordcount.controller;

import com.example.wordcount.service.WordCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WordCountController {

    @Autowired
    private WordCount wordCount;

    @RequestMapping("/WordCount")
    public String outWordCount() {
        Map<String, Long> map = wordCount.wordCount("D:\\bak\\test");
        StringBuilder s = new StringBuilder();
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            String mapKey = entry.getKey();
            Long mapValue = entry.getValue();
            s.append(mapKey + ":" + mapValue);
            s.append("<br />");
        }
        return s.toString();
    }
}
