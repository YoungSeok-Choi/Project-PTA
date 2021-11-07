package dev.mvc.picture;

import java.io.File;

public class Contents {

    // for paging
    public static int RECORD_PER_PAGE = 3;

    public static int PAGE_PER_BLOCK = 10;
   
    public static String LIST_FILE = "list_by_cateno_search_paging.do";

    // Windows, VMWare, AWS cloud 
    public static synchronized String getUploadDir() {
        String path = "";
        if (File.separator.equals("\\")) {
            // System.out.println("Windows 10");
            path = "C:/kd1/deploy/resort_v1sbm3c/contents/storage/";
            
        } else {
            // System.out.println("Linux");
            path = "/home/ubuntu/deploy/ptoa/contents/storage/";
        }
        
        return path;
    }
    
}