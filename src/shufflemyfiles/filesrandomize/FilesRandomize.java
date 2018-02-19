/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shufflemyfiles.filesrandomize;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

/**
 *
 * @author Goran
 */
public class FilesRandomize {

    public static final String PREFIX_SEPARATOR = ".";
    /**
     * Contains all the files to be shuffled.
     */
    private File[] listOfFiles;
    /**
     * Contains all prefixes for the shuffle files.
     */
    private int[] listOfNumbers;
    
    private final Random rnd;

    public FilesRandomize() {
        rnd = new Random();
    }

    /**
     * Reads all files from current file directory. 
     * Name of the FileSorter.jar is excluded.
     * @param dir
     * @return number of files to be shuffled 
     */
    public int loadFiles(File dir) {
        if(dir == null) {
            return -1;
        }
        listOfFiles = dir.listFiles((pathName) -> {
            return pathName.isFile();
        });
        return listOfFiles.length;
    }
    
    public int getNumberOfFIles() {
        return listOfFiles.length;
    }

    /**
     * First creates listOfnumbers as listOfFiles.length size.
     * Then filles created listOfNumbers with numbers from 1 to length.
     * Then each field in listOfNumbers swaps value from 
     * random.nexInt(current index + 1).
     * The list is shuffled after.
     */
    public void generateRandomNumerList() {
        if (listOfFiles != null && listOfFiles.length > 1) {
            rnd.setSeed(System.currentTimeMillis());
            listOfNumbers = new int[listOfFiles.length];
            for (int i = 0; i < listOfNumbers.length; i++) {
                listOfNumbers[i] = i + 1;
            }
            for (int i = listOfNumbers.length - 1; i > 0; i--) {
                int j = rnd.nextInt(i + 1);
                if (i != j) {
                    swap(i, j);
                }
            }
        }
    }
    /**
     * Swaps list elements at indexes i and j.
     * 
     * @param i list index
     * @param j list index
     */
    private void swap(int i, int j) {
        int pom;
        pom = listOfNumbers[i];
        listOfNumbers[i] = listOfNumbers[j];
        listOfNumbers[j] = pom;
    }

    /**
     * Changes file name of each file in listOfFiles as
     * listOfNumbers[i] + '.' + listOfFiles[i].getName()
     * Numbers are with leading zero.
     * @throws java.io.IOException
     */
    public void mergeLists() throws IOException {
        String format = "%0" + (listOfFiles.length + "").length() + "d";
        StringBuilder newName = new StringBuilder(47);
        for (int i = 0; i < listOfFiles.length; i++) {
            newName.append(String.format(format, listOfNumbers[i])).append(PREFIX_SEPARATOR).append(trimPrefixNumber(listOfFiles[i].getName()));
            Path source = listOfFiles[i].toPath();
            Files.move(source, source.resolveSibling(newName.toString()));
            newName.setLength(0);
        }
    }
    
    /**
     * Removes all leading numbers and dots from file name if there are any.
     * 
     * @param name File name from listOfFiles
     * @return Trimmed file name.
     */
    private String trimPrefixNumber(String name) {
        return name.replaceFirst("^[0-9[\\.]]+", "");
    }

}
