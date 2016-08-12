package com.luxoft.wheretogo.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import static java.nio.file.Paths.get;

/**
 * Created by bobbi on 10.08.16.
 */
public class ImageUtils {
    public static String generatePicturePath(String imageDataString, String pathToFolder) {
        //Generating image/file and path to store event data
        if(imageDataString.length()!=0){
            Random rnd = new Random();
            String fileName = generateString(rnd,"qwertyuiop0987654321asdfghjklzxcvbnm",8);
            String path = pathToFolder+fileName;
            //Default path: apache-tomcat -> bin
            File img = new File(path);
            path = img.getPath();
            try {
                Files.write(get(img.getPath()),imageDataString.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return path;
        }
        else return imageDataString;
        //End of generation
    }
    public static String giveMeImage(String picture, boolean fromEvent){
        String imageData = "", pathToImage="", absolutePath="../webapps/ROOT/"; //move to config
        /*if(fromEvent) pathToImage = "resources/images/"; //move to config
        else pathToImage = "resources/images/"; //move to config
        String[] tmp = picture.split("/");
        pathToImage+=tmp[tmp.length-1];
        absolutePath+=pathToImage;*/
        //File image = new File(picture);
        //if(image.exists()){
            //if(!Files.isSymbolicLink(get(absolutePath))){
                try {
                    //Files.createSymbolicLink(get(absolutePath),get(picture));
                    Path p = Paths.get(picture);
                    byte[] arr = Files.readAllBytes(p);
                    imageData = new String(arr);
                    //imageData=pathToImage;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            //}
            //else imageData=pathToImage;
            return imageData;
        //}
        //else return "";
    }
    private static String generateString(Random rng, String characters, int length)
    {
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }
}
