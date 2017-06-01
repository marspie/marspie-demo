package com.marspie.demo;

import java.io.IOException;
import java.util.ArrayList;

import com.marspie.demo.pcs.FileInfo;
import com.marspie.demo.pcs.PcsUtil;

public class PcsTest {

	public static void main(String[] args) throws IOException {
		ArrayList<FileInfo> fileList = new ArrayList<FileInfo>();  
        PcsUtil.getDirFile("/apps/lgodh", fileList);  
        System.out.println("文件个数："+ fileList.size());  
	}

}
