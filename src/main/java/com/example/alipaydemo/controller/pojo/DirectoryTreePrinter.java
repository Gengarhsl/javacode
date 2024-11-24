package com.example.alipaydemo.controller.pojo;

public class DirectoryTreePrinter {
    public static void printDirectoryTree(Directory directory, String indent) {
        System.out.println(indent + directory.getName());
        for (Directory subdirectory : directory.getSubdirectories()) {
            printDirectoryTree(subdirectory, indent + "  ");
        }
    }

    public static void main(String[] args) {
        // 创建目录树
        Directory root = new Directory("root");
        Directory folder1 = new Directory("folder1");
        Directory folder2 = new Directory("folder2");
        Directory subfolder1 = new Directory("subfolder1");
        Directory subfolder2 = new Directory("subfolder2");
        Directory subfolder3 = new Directory("subfolder3");

        subfolder1.addSubdirectory(subfolder3);
        folder1.addSubdirectory(subfolder1);
        folder2.addSubdirectory(subfolder2);
        root.addSubdirectory(folder1);
        root.addSubdirectory(folder2);

        // 打印目录树
        printDirectoryTree(root, "");
    }
}