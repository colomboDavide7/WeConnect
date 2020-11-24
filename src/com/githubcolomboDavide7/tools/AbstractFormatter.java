package com.githubcolomboDavide7.tools;

import com.githubcolomboDavide7.connection.*;

import java.util.*;

public class AbstractFormatter {

    private static final String fieldSeparator = ",";
    private static final String keyvalueSeparator = "=";

    public static String formatConnectionInfo(Map<ConnectionInfo, String> info){
        StringBuilder formatted = new StringBuilder();
        Map<ConnectionInfo, String> ordered = reorderValues(info);
        int maxPos = getMaxOrderPosition(ordered);
        for(ConnectionInfo i : ordered.keySet()) {
            formatted.append(i).append(keyvalueSeparator).append(info.get(i));
            if(i.order == maxPos)
                break;
            formatted.append(fieldSeparator);
        }
        return formatted.toString();
    }

    private static Map<ConnectionInfo, String> reorderValues(Map<ConnectionInfo, String> toReorder){
        Map<ConnectionInfo, String> ordered = new HashMap<>(toReorder.size());
        int currentPos = 1;
        while(currentPos <= toReorder.size()) {
            for(ConnectionInfo i : toReorder.keySet())
                if(i.order == currentPos)
                    ordered.put(i, toReorder.get(i));
            currentPos++;
        }
        return ordered;
    }

    private static int getMaxOrderPosition(Map<ConnectionInfo, String> info){
        int maxPos = 1;
        for(ConnectionInfo i : info.keySet())
            if(i.order > maxPos)
                maxPos = i.order;
        return maxPos;
    }

}
