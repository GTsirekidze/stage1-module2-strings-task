package com.epam.mjc;

import java.util.*;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {

        List<String> ans = new LinkedList<>();
        //delimiters.add(" ");
        ans.add(source);
        for (int i=0;i<delimiters.size();i++){
            List<String> currentAray = new ArrayList<>();
            for (String an : ans) {
                currentAray.addAll(Arrays.stream(an.split(delimiters.stream().toList().get(i))).toList());
            }
            ans = currentAray;
        }

        return ans.stream().filter(x -> !x.equals("")).toList();

    }
}
