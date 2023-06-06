package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        String directoryPath = args[0];
        String saveToPath = args[1];
        try {
            List<File> imageFiles = getImagesFromDirectory(directoryPath);

            // Testowanie różnych rozmiarów puli wątków
            int[] threadPoolSizes = {1, 2, 4, 8, 12};
            for (int threadPoolSize : threadPoolSizes) {
                processImagesInParallel(imageFiles, threadPoolSize, saveToPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<File> getImagesFromDirectory(String directoryPath) throws IOException {
        try (Stream<File> filesStream = Files.list(new File(directoryPath).toPath())
                .map(java.nio.file.Path::toFile)
                .filter(file -> file.getName().endsWith(".jpg") || file.getName().endsWith(".png"))) {
            return filesStream.collect(Collectors.toList());
        }
    }

    private static void processImagesInParallel(List<File> imageFiles, int threadPoolSize, String saveToPath) {
        long startTime = System.nanoTime();
        try(ForkJoinPool forkJoinPool = new ForkJoinPool(threadPoolSize)) {
            imageFiles.parallelStream()
                    .forEach(file -> {
                        try {
                            forkJoinPool.submit(() -> {
                                try {
                                    System.out.println("Przetwarzam obrazek: " + file.getName());
                                    BufferedImage original = ImageIO.read(file);
                                    BufferedImage modified = modifyImage(original);
                                    String outputFilePath = getOutputFilePath(file, saveToPath);
                                    ImageIO.write(modified, "jpg", new File(outputFilePath));
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }).get();
                        } catch (InterruptedException | ExecutionException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }

        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("Przetwarzanie z pulą " + threadPoolSize + " wątków zakończone. Czas: " + estimatedTime);
    }

    private static String getOutputFilePath(File inputFile, String saveToPath) {
        String outputFileName = inputFile.getName();
        return saveToPath + File.separator + outputFileName;
    }

    private static BufferedImage modifyImage(BufferedImage original) throws IOException {
        BufferedImage image = new BufferedImage(original.getWidth(),
                original.getHeight(),
                original.getType());
        for(int y = 0; y < original.getHeight(); y++) {
            for(int x = 0; x < original.getWidth(); x++) {
                int rgb = original.getRGB(x, y);
                Color color = new Color(rgb);
                int red = color.getRed();
                int blue = color.getBlue();
                int green = color.getGreen();
                int sum = red + blue + green;
                Color outColor = new Color(sum/3, sum/3, sum/3);
                int outRgb = outColor.getRGB();
                image.setRGB(x, y, outRgb);
            }
        }
        return image;
    }
}
