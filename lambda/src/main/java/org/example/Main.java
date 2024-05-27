package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class MonitoredData{
    TimeZone startTime;
    TimeZone endTime;
    String activity;

    public MonitoredData(TimeZone startTime, TimeZone endTime, String activity) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.activity = activity;
    }

    public TimeZone getStartTime() {
        return startTime;
    }

    public void setStartTime(TimeZone startTime) {
        this.startTime = startTime;
    }

    public TimeZone getEndTime() {
        return endTime;
    }

    public void setEndTime(TimeZone endTime) {
        this.endTime = endTime;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
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
        Stream<String> stream = Files.lines(Paths.get("products.txt"));
        List<Product> productList = stream.map(line -> new Product(line))
                .collect(toList());
        productList.stream()
                .forEach(System.out::println);
    }
}