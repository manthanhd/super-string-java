package com.manthanhd.utils.superstringreplace;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by manthanhd on 19/03/2016.
 */
public class SuperStringTest {

    @Test
    public void shouldReplaceSingleMarkerWithGivenValue() {
        String testString = "I am the :name:.";
        Map<String, String> testReplaceOptions = new HashMap<>();
        testReplaceOptions.put("name", "duke");

        SuperString testee = new SuperString(testString);
        String replacedString = testee.replace(testReplaceOptions);

        assertThat(replacedString, is(equalTo("I am the duke.")));
    }

    @Test
    public void shouldReplaceTwoMarkersWithGivenTwoValues() throws Exception {
        final String testString = "my name is :name:. welcome to :product:";
        final Map<String, String> testReplaceOptions = new HashMap<>();
        testReplaceOptions.put("name", "manthan");
        testReplaceOptions.put("product", "Super String");

        final SuperString testee = new SuperString(testString);
        final String replacedString = testee.replace(testReplaceOptions);

        assertThat(replacedString, is(equalTo("my name is manthan. welcome to Super String")));
    }

    @Test
    public void shouldReplaceNMarkersWithGivenNValues() throws Exception {
        String randomString = "lorem ipsum ";
        String actualString = "lorem ipsum ";

        final int parameterCount = (int) (Math.random() * 100);
        final Map<String, String> testReplaceOptions = new HashMap<>();
        for (int i = 1; i < parameterCount; i++) {
            randomString += ":" + i + ": ";
            testReplaceOptions.put(i + "", "number " + i);

            actualString += "number " + i + " ";
        }

        randomString += " 0 total count is  " + parameterCount;
        actualString += " 0 total count is  " + parameterCount;

        final SuperString testee = new SuperString(randomString);
        final String replacedString = testee.replace(testReplaceOptions);

        assertThat(replacedString, is(equalTo(actualString)));
    }

    @Test
    public void shouldReplaceOneStringMultipleTimesWithDifferentGivenValues() throws Exception {
        String testString = "my name is :name:.";
        Map<String, String> testReplaceOptionsJohn = new HashMap<>();
        testReplaceOptionsJohn.put("name", "john");

        Map<String, String> testReplaceOptionsDuke = new HashMap<>();
        testReplaceOptionsDuke.put("name", "duke");

        SuperString testee = new SuperString(testString);
        String replacedStringJohn = testee.replace(testReplaceOptionsJohn);
        String replacedStringDuke = testee.replace(testReplaceOptionsDuke);

        assertThat(replacedStringJohn, is(equalTo("my name is john.")));
        assertThat(replacedStringDuke, is(equalTo("my name is duke.")));
    }
}