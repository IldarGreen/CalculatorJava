package com.greenone.installer;


import javafx.application.Application;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.io.File;
import java.nio.file.*;
import java.io.IOException;
import java.nio.file.attribute.BasicFileAttributes;
import java.io.IOException;

public class InstallerApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Select Directory");

            File selectedDirectory = directoryChooser.showDialog(primaryStage);
            Save(selectedDirectory.getAbsolutePath());
            build();
            createShortcut(selectedDirectory.getAbsolutePath(),"SmartCalc");

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        launch();
    }

    public static void Save(String path) throws IOException {
        String projectPath = InstallerApplication.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        projectPath = projectPath.substring(0, projectPath.lastIndexOf("/"));
        projectPath = projectPath.substring(0, projectPath.lastIndexOf("/"));
        projectPath += "/src/main/resources/calc";

        Path sourceDir = Paths.get(projectPath);
        Path destinationDir = Paths.get(path + "/SmartCalc");
        copyDirectory(sourceDir, destinationDir);
    }

    public static void copyDirectory(Path source, Path destination) throws IOException {
        Files.walkFileTree(source, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                Path targetPath = destination.resolve(source.relativize(dir));
                Files.createDirectories(targetPath);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.copy(file, destination.resolve(source.relativize(file)), StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public static void createShortcut(String targetFilePath, String shortcutName) throws IOException {
        targetFilePath += "/SmartCalc/target/smartcalc-1.0-SNAPSHOT-shaded.jar";
        String desktopPath = System.getProperty("user.home") + "/Desktop";

        Path shortcutPath = Paths.get(desktopPath, shortcutName);

        File targetFile = new File(targetFilePath);
        Files.createSymbolicLink(shortcutPath, targetFile.toPath());
    }

    public static void build() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "install");
        processBuilder.redirectErrorStream(true);
        try {
            Process process = processBuilder.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
