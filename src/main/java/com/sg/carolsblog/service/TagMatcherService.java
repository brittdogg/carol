/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.service;

import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static jdk.nashorn.tools.ShellFunctions.input;

/**
 *
 * @author trevor
 */
public class TagMatcherService {
    
//    private final String hashTagRegEx = "(?<=^|\\s)#([A-Za-z_]+\\w*)";
    private final String hashTagRegEx = "(?<=^#|\\s#)([A-Za-z_]+\\w*)";
    
    public List<String> getHashTags(String input) {
        List<String> hashTags = new ArrayList<>();
        Pattern pattern = Pattern.compile(hashTagRegEx);
        Matcher m = pattern.matcher(input);
        while (m.find()) {
            hashTags.add(m.group(1));
        }
        return hashTags;
    }
    
    public String replaceTagsWithUrls(String input) {
        List<String> hashTags = getHashTags(input);
        String outPut = null;
        String regEx;
        for(String tag : hashTags) {
            regEx = "(?<=^|\\s)#" + tag + "(?!\\w)";
            input = input.replaceAll(regEx, "<a href=\"/blog/tag/" + tag + "\">#" + tag + "</a>");
        }
        return input;
    }
}
