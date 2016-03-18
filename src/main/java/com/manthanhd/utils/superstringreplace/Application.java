package com.manthanhd.utils.superstringreplace;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by manthanhd on 18/03/2016.
 */
public class Application {

    public static final char DELIM = ':';

    public static void main(String[] args) {
        final String superStringString = "one two three :afterThree: five :afterFiveIs:";
        final Map<String, String> superStringReplaceValues = new HashMap<>();
        superStringReplaceValues.put("afterThree", "four");
        superStringReplaceValues.put("afterFiveIs", "six");

        long superStringReplaceTime = System.nanoTime();
        final SuperString superString = new SuperString(superStringString);
        final String superStringReplacedString = superString.replace(superStringReplaceValues);
        superStringReplaceTime = System.nanoTime() - superStringReplaceTime;

        System.out.println(superStringString);
        System.out.println(superStringReplacedString);
        System.out.println("Super string replace: " + superStringReplaceTime + " nano seconds");
        System.out.println();

        final Application app = new Application();
        final String replaceStringString = "one two three :afterThree: five :afterFiveIs:";
        final Map<String, String> replaceStringReplaceValues = new HashMap<>();
        replaceStringReplaceValues.put("afterThree", "four");
        replaceStringReplaceValues.put("afterFiveIs", "six");

        long replaceStringTime = System.nanoTime();
        final String replacedString = app.replaceString(replaceStringString, replaceStringReplaceValues);
        replaceStringTime = System.nanoTime() - replaceStringTime;

        System.out.println(replaceStringString);
        System.out.println(replacedString);
        System.out.println("One-method super string replace: " + replaceStringTime);

        // Comparing this with printf

        long stringFormatTime = System.nanoTime();
        final String stringFormatString = "one two three %s five %s";
        final String stringFormatReplacedString = String.format(stringFormatString, "three", "six");
        stringFormatTime = System.nanoTime() - stringFormatTime;

        System.out.println(stringFormatString);
        System.out.println(stringFormatReplacedString);
        System.out.println("Usual Java String.format replace: " + stringFormatTime);
    }

    public String replaceString(final String parameterizedString, final Map<String, String> replaceValues) {
        final Stack<Marker> markerStack = new Stack<>();
        final StringBuilder newString = new StringBuilder(parameterizedString);

        boolean recording = false;
        int markerStartIndex = -1;
        StringBuilder markerBuilder = new StringBuilder();
        for (int i = 0; i < newString.length(); i++) {
            char currentChar = newString.charAt(i);

            if (DELIM == currentChar) {
                if (!recording) {
                    recording = true;
                    markerStartIndex = i;
                    markerBuilder = new StringBuilder();
                } else {
                    recording = false;
                    markerStack.push(new Marker(markerStartIndex, markerBuilder.toString()));
                }
                continue;
            }

            if (recording) {
                markerBuilder.append(currentChar);
            }
        }

        for (int i = markerStack.size() - 1; i >= 0; i--) {
            // reverse order
            Marker marker = markerStack.get(i);
            if (replaceValues.containsKey(marker.getMarker())) {
                newString.replace(marker.getIndex(), marker.getIndex() + marker.getMarker().length() + 2, replaceValues.get(marker.getMarker()));
            }
        }

        return newString.toString();
    }

    private class Marker {
        private int index;
        private String marker;

        public Marker(int index, String marker) {
            this.index = index;
            this.marker = marker;
        }

        public int getIndex() {
            return index;
        }

        public String getMarker() {
            return marker;
        }
    }
}
