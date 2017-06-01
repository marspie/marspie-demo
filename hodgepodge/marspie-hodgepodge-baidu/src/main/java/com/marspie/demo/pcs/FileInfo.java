package com.marspie.demo.pcs;

/* 对照REST API返回形式，定义文件对象 */  
public class FileInfo  
{  
    private long fsId;  
    private String path;  
    private int ctime;  
    private int mtime;  
    private String md5;  
    private long size;  
    private int isdir;  
      
    public long getFsId()  
    {  
        return fsId;  
    }  
    public void setFsId(long fsId)  
    {  
        this.fsId = fsId;  
    }  
    public String getPath()  
    {  
        return path;  
    }  
    public void setPath(String path)  
    {  
        this.path = path;  
    }  
    public int getCtime()  
    {  
        return ctime;  
    }  
    public void setCtime(int ctime)  
    {  
        this.ctime = ctime;  
    }  
    public int getMtime()  
    {  
        return mtime;  
    }  
    public void setMtime(int mtime)  
    {  
        this.mtime = mtime;  
    }  
    public String getMd5()  
    {  
        return md5;  
    }  
    public void setMd5(String md5)  
    {  
        this.md5 = md5;  
    }  
    public long getSize()  
    {  
        return size;  
    }  
    public void setSize(long size)  
    {  
        this.size = size;  
    }  
    public int getIsdir()  
    {  
        return isdir;  
    }  
    public void setIsdir(int isdir)  
    {  
        this.isdir = isdir;  
    }  
}  