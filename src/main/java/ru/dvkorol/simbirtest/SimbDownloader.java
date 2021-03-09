package ru.dvkorol.simbirtest;

import ru.dvkorol.simbirtest.models.WordStat;
import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimbDownloader {

    private static Boolean isBody;
    private static Boolean isScript;
    private static Boolean isInTag;

    public static List<WordStat> getStatData(String urlAdr, String directory){

        List<WordStat> wordStats = new ArrayList<>();

        try{
            URL url = new URL(urlAdr);
            String fileName = getDownloadedFileName(url, directory);

            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            Map<String, Integer> wordStatMap = new HashMap<>();
            isBody = false;
            isInTag = false;
            isScript = false;

            while (reader.ready()){
                String line = reader.readLine();
                writer.write(line + "\n");
                parseLine(line, wordStatMap);
            }

            writer.close();
            reader.close();

            wordStats = creatWordStats(wordStatMap);

        }catch (IOException exception){
            return wordStats;
        }

        return wordStats;
    }

    private static List<WordStat> creatWordStats(Map<String, Integer> wordStatMap) {

        List<WordStat> wordStats = new ArrayList<>();

        for (Map.Entry<String, Integer> elem : wordStatMap.entrySet()) {
            WordStat wordStat = new WordStat();
            wordStat.setWord(elem.getKey());
            wordStat.setCount(elem.getValue());

            wordStats.add(wordStat);
        }

        return wordStats;

    }

    private static Boolean isTag(Boolean isTag, String line, String tagName){

        String openTag = "<" + tagName;
        String closedTag = "</" + tagName;

        if (!isTag){
            if (line.contains(openTag)){
                isTag = true;
            }
        }else if(line.contains(closedTag)){
            isTag = false;
        }

        return isTag;
    }

    private static void parseLine(String line, Map<String, Integer> wordStatMap){

        StringBuilder builder = new StringBuilder();

        isBody = isTag(isBody, line, "body");
        isScript = isTag(isScript, line, "script");

        if (isBody && !isScript){
            for (Character i : line.toCharArray()){
                if (i == '<'){
                    isInTag = true;
                }else if (i == '>'){
                    isInTag = false;
                }

                if (!isInTag && i != '>'){
                    builder.append(i);
                }
            }
        }

        String cleanLine = builder.toString().trim().toLowerCase();
        if (cleanLine.isEmpty()){
            return;
        }

        Pattern pattern = Pattern.compile("\\w+", Pattern.UNICODE_CHARACTER_CLASS | Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(cleanLine);
        while (matcher.find()){
            String word = matcher.group();
            if (wordStatMap.containsKey(word)){
                Integer val = wordStatMap.get(word);
                wordStatMap.put(word, ++val);
            }else{
                wordStatMap.put(word, 1);
            }
        }


    }

    private static String getDownloadedFileName(URL url, String directory){
        LocalDateTime now = LocalDateTime.now();

        return directory + "/" + url.getHost() +
                " " + now.toLocalDate() +
                "(" + now.getHour() + "_" + now.getMinute() + "_" + now.getSecond() + ")" +
                ".html";
    }

}
