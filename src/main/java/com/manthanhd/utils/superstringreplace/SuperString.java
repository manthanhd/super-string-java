package com.manthanhd.utils.superstringreplace;

import java.util.Map;
import java.util.Stack;

/**
 * Created by manthanhd on 18/03/2016.
 */
public class SuperString {
    public static final char DELIM = ':';

    private final String originalString;
    private final Stack<Marker> markerStack = new Stack<>();

    public SuperString(final String originalString) {
        this.originalString = originalString;
        generateTokens();
    }

    private void generateTokens() {
        StringBuilder markerBuilder = new StringBuilder();
        boolean recording = false;
        int markerStartIndex = -1;
        for (int i = 0; i < originalString.length(); i++) {
            char currentChar = originalString.charAt(i);

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
    }

    public String replace(Map<String, String> replaceValues) {
        final StringBuilder newString = new StringBuilder(originalString);
        for (int i = markerStack.size() - 1; i >= 0; i--) {
            final Marker marker = markerStack.get(i);
            if (replaceValues.containsKey(marker.getMarker())) {
                newString.replace(marker.getIndex(), marker.getIndex() + marker.getMarker().length() + 2, replaceValues.get(marker.getMarker()));
            }
        }

        return newString.toString();
    }

    private static class Marker {
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
