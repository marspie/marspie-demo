package com.marspie.demo.pcs;

import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.net.URL;  
import java.net.URLConnection;  
import java.util.ArrayList;  
  
import com.google.gson.Gson;  
import com.google.gson.JsonObject;  
import com.google.gson.JsonParser;  
  
public class PcsUtil  
{      
    public static String ACCESS_TOKEN = "";  
    public static URL url = null;  
     
    /** 
     * 获取配合信息 
     * @throws IOException 
     */  
    public static void getPCSInfo() throws IOException  
    {  
        URL u = new URL("https://pcs.baidu.com/rest/2.0/pcs/quota?method=info&access_token="+ ACCESS_TOKEN);    
        URLConnection conn = u.openConnection();// 打开网页链接    
          
        // 获取用户云盘信息    
        String cloudJson = getJsonString(conn).toString();    
          
        // 通过gson解析成对象  
//        CloudInfo cloudInfo = new Gson().fromJson(cloudJson, CloudInfo.class);    
//        System.out.println("云盘信息："+cloudInfo);    
    }  
      
    /** 
     * 获取指定目录元数据信息，URL地址中与配额quota不同，此处为file 
     * @param path 
     * @throws IOException 
     */  
    public static void getDirMeta(String path) throws IOException  
    {  
        URL u = new URL("https://pcs.baidu.com/rest/2.0/pcs/file?method=meta&access_token="  
                   + ACCESS_TOKEN + "&path=" + path);    
        URLConnection conn = u.openConnection();// 打开网页链接    
        String meta = getJsonString(conn).toString();    
        System.out.println("目录信息："+ meta);   
          
    }  
      
    /** 
     * 获取path路径下的文件包括子目录，逐级递归下级目录 
     * @param path    指定文件路径 ,PCS目录文件结构顶级为固定/apps,下一级为开通PCS API时指定的home目录, 
     *                如我申请时设置的为myhome, 则获取该目录下的所有文件, path为 /apps/myhome 
     * @param fileList   为输出参数, 文件添加到该list中，这里用到了递归 
     * @throws IOException 
     */  
    public static void getDirFile(String path, ArrayList<FileInfo> fileList) throws IOException  
    {  
        URL u = new URL("https://pcs.baidu.com/rest/2.0/pcs/file?method=list&access_token="  
                   + ACCESS_TOKEN + "&path=" + path);    
        URLConnection conn = u.openConnection();// 打开网页链接    
        String str = getJsonString(conn).toString();   
   
        JsonParser parser = new JsonParser();  
        JsonObject jsonObject = (JsonObject) parser.parse(str);  
          
        FileInfo[] files=new Gson().fromJson(jsonObject.getAsJsonArray("list").toString(),  
                FileInfo[].class);  
          
        for(FileInfo file: files)  
        {  
            if(file.getIsdir() == 1)  
            {     
                /* 如果是目录，则进行递归查找*/  
                getDirFile(file.getPath(), fileList);  
            }  
            fileList.add(file);  
            System.out.println("文件路径："+ file.getPath());   
              
        }  
    }  
      
    private static StringBuffer getJsonString(URLConnection conn) throws IOException  
    {    
        InputStreamReader isr = new InputStreamReader(conn.getInputStream(),"utf-8");    
        BufferedReader br = new BufferedReader(isr);    
        StringBuffer sb = new StringBuffer();   
        String line = null;   
        try  
        {  
            while ((line = br.readLine()) != null)  
            {    
                sb.append(line);    
            }    
        }  
        catch (IOException e)  
        {    
            System.out.println("read io error.");  
            e.printStackTrace();    
            br.close();  
            isr.close();   
        }        
        return sb;    
    }    
}  
