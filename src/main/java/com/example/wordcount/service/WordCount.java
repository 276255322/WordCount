package com.example.wordcount.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文本文件统计功能类
 */
@Service
public class WordCount {

    /**
     * 单词统计
     *
     * @param dirPath 文件目录
     * @return
     */
    public Map<String, Long> wordCount(String dirPath) {
        Map<String, Long> map = new HashMap<>();
        Pattern expression = Pattern.compile("[a-zA-Z]+");
        List<String> fileList = new ArrayList<>();
        getFileList(fileList, dirPath, ".txt");
        for (int i = 0; i < fileList.size(); i++) {
            String filePath = fileList.get(i);
            String fileContent = null;
            try {
                fileContent = readFile(filePath);
            } catch (IOException ex) {
            }
            String lowers = fileContent.toLowerCase();
            Matcher matcher = expression.matcher(lowers);
            String word = null;
            while (matcher.find()) {
                word = matcher.group();
                if (map.containsKey(word)) {
                    map.put(word, map.get(word) + 1);
                } else {
                    map.put(word, (long) 1);
                }
            }
        }
        return map;
    }

    /**
     * 遍历文件夹取所有文件路径
     *
     * @param fileList  文件路径列表
     * @param dirPath  文件夹路径
     * @param fileType 文件类型
     * @return
     */
    private void getFileList(List<String> fileList, String dirPath, String fileType) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) {
                    getFileList(fileList, files[i].getAbsolutePath(), fileType);
                } else if (fileName.endsWith(fileType)) {
                    String strFileName = files[i].getAbsolutePath();
                    fileList.add(strFileName);
                } else {
                    continue;
                }
            }
        }
    }

    /**
     * 读取文件
     *
     * @param filePath 文件路径
     * @return
     * @throws IOException
     */
    private String readFile(String filePath) throws IOException {
        FileReader fis = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fis);
        StringBuffer sb = new StringBuffer();
        String it = br.readLine();
        while (it != null) {
            sb.append(it);
            sb.append(" ");
            it = br.readLine();
        }
        return sb.toString();
    }

}
