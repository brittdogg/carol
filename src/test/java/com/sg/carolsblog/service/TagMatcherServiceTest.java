/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.service;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author trevor
 */
public class TagMatcherServiceTest {
    
    private TagMatcherService service = new TagMatcherService();
    
    public TagMatcherServiceTest() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void testTagHasLettersNumbersTrue() {
        String testString = "#A46 this is a test #Ab56gh string with some hashtags"
                + "thrown in for #ab45 good #z6_5 measure.";
        List<String> tags = service.getHashTags(testString);
        assertTrue(tags.contains("A46"));
        assertTrue(tags.contains("Ab56gh"));
        assertTrue(tags.contains("ab45"));
        assertTrue(tags.contains("z6_5"));
    }
    
    @Test
    public void testTagStartsWithNumbersFalse() {
        String testString = "#46a this is a test #56 string with some hashtags"
                + "thrown in for #4_5 good #6_A5 measure.";
        List<String> tags = service.getHashTags(testString);
        assertTrue(tags.size() == 0);
    }
    
    @Test
    public void testTagHasPuncuationFalse() {
        String testString = "#A'46 this is a test #Ab5-6gh string with some hashtags"
                + "thrown in for #!ab45 good #z6_'5 measure.";
        List<String> tags = service.getHashTags(testString);
        assertTrue(tags.contains("A"));
        assertTrue(tags.contains("Ab5"));
        assertFalse(tags.contains("!ab45"));
        assertTrue(tags.contains("z6_"));
    }
    
    @Test
    public void testTagHasUnderscoresTrue() {
        String testString = "#__A46 this is a test #Ab5_6gh string with some hashtags"
                + "thrown in for #ab45__ good #_z6_5_ measure.";
        List<String> tags = service.getHashTags(testString);
        assertTrue(tags.contains("__A46"));
        assertTrue(tags.contains("Ab5_6gh"));
        assertTrue(tags.contains("ab45__"));
        assertTrue(tags.contains("_z6_5_"));
    }
    
    @Test
    public void testReplaceTagsWithUrls() {
        String testString = "#A'46 this is a test #Ab5-6gh string with some hashtags "
                + "thrown in for #!ab45 good #z6_'5 measure.";
        String withUrls = service.replaceTagsWithUrls(testString);
        String expectedString = "<a href=\"/blog/tag/A\">#A</a>'46 this is a "
                + "test <a href=\"/blog/tag/Ab5\">#Ab5</a>-6gh string "
                + "with some hashtags thrown in for #!ab45 good "
                + "<a href=\"/blog/tag/z6_\">#z6_</a>'5 measure.";
        assertTrue(expectedString.equals(withUrls));
    }
    
}
