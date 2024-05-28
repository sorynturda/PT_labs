package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toList;

class MonitoredData {
    LocalDateTime startTime;
    LocalDateTime endTime;
    String activity;

    public MonitoredData(LocalDateTime startTime, LocalDateTime endTime, String activity) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.activity = activity;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void LocalDateTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "MonitoredData{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", activity='" + activity + '\'' +
                '}';
    }
}

class Product {
    private String name;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                '}';
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        //regex ([0-9-: ]+)\t+([0-9-: ]+)\t+([\D]+$)
        Pattern pattern = Pattern.compile("([0-9-: ]+)\\t+([0-9-: ]+)\\t+(\\D+$)");
        Stream<String> stream = Files.lines(Paths.get("Activities.txt"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String s = "2011-11-28 20:21:15		2011-11-29 02:06:00		Spare_Time/TV ";
        List<MonitoredData> monitoredDataList = stream.map(line ->
        {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find())
                return new MonitoredData(LocalDateTime.parse(matcher.group(1).trim(), formatter),
                        LocalDateTime.parse(matcher.group(2).trim(), formatter), matcher.group(3).trim());
            return null;
        }).toList();
        System.out.println(
                monitoredDataList.stream().filter(entry -> Duration.between(entry.getStartTime(), entry.getEndTime()).toMinutes() < 5).mapToInt(i->1).sum()
        );

    }
}