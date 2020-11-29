package com.githubcolomboDavide7.tools;

import com.githubcolomboDavide7.connection.*;

import java.util.*;

public class AbstractFormatter {

    private static final String fieldSeparator = ",";
    private static final String keyvalueSeparator = "=";

    public static String formatConnectionInfo(Map<ConnectionInfo, String> info){
        StringBuilder formatted = new StringBuilder();
        int maxPos = getMaxOrderPosition(info);
        int currentPos = 1;
        while(currentPos <= maxPos) {
            for(ConnectionInfo i : info.keySet()) {
                if(i.order == currentPos) {
                    formatted.append(i).append(keyvalueSeparator).append(info.get(i));
                    if(i.order == maxPos)
                        break;
                    formatted.append(fieldSeparator);
                }
            }
            currentPos++;
        }
        return formatted.toString();
    }

    private static int getMaxOrderPosition(Map<ConnectionInfo, String> info){
        int maxPos = 1;
        for(ConnectionInfo i : info.keySet())
            if(i.order > maxPos)
                maxPos = i.order;
        return maxPos;
    }

    public static Map<ConnectionInfo, String> parseRecord(String line){
        String[] keyValuePairs = line.split(fieldSeparator);
        Map<ConnectionInfo, String> converted = new HashMap<>(keyValuePairs.length);
        for(String s : keyValuePairs)
            appendKeyValue(converted, s);
        return converted;
    }

    private static void appendKeyValue(Map<ConnectionInfo, String> map, String keyValue){
        String key = keyValue.split(keyvalueSeparator)[0];
        String value = keyValue.split(keyvalueSeparator)[1];
        map.put(ConnectionInfo.valueOf(key), value);
    }

}
