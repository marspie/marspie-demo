package com.marspie.demo.webdev;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

import com.googlecode.sardine.DavResource;
import com.googlecode.sardine.Sardine;
import com.googlecode.sardine.SardineFactory;
import com.googlecode.sardine.util.SardineException;  
  
public class TestSardine {  
  
    /** 
     * @param args 
     * @throws IOException 
     */  
    public static void main(String[] args) throws IOException {  
        Sardine sardine = SardineFactory.begin("marspie@126.com", "ab2uhy44yxb3y383");  
          
        if (sardine.exists("https://dav.jianguoyun.com/dav/")) {  
            System.out.println("/content/dam folder exists");  
        }  
          
        sardine.createDirectory("https://dav.jianguoyun.com/dav/testfolder/");  
          
        InputStream fis = new FileInputStream(Paths.get("d:/sk.jpg").toFile());  
        sardine.put("https://dav.jianguoyun.com/dav/testfolder/sk.jpg", fis);  
          
        List<DavResource> resources = sardine.getResources("https://dav.jianguoyun.com/dav/testfolder/");  
        FileOutputStream fos = null;
        for (DavResource res : resources) {
			if(res.getName() == null || "".equals(res.getName().trim()))
				continue;
			
			fis = sardine.getInputStream(res.getAbsoluteUrl());
			fos = new FileOutputStream("d:/down" + res.getName());
			byte[] b = new byte[1024];
			while ((fis.read(b)) != -1) {
				fos.write(b);
			}
			fis.close();
			fos.close();
			System.out.println(res.getName()); // calls the .toString() method.
		}
    }  
}  