package com.example.alipaydemo.controller.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class Directory {
    private String name;
    private List<Directory> subdirectories;

    public Directory(String name) {
        this.name = name;
        this.subdirectories = new ArrayList<>();
    }

    public void addSubdirectory(Directory directory) {
        subdirectories.add(directory);
    }


}