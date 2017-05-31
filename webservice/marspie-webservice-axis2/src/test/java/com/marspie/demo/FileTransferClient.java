package com.marspie.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.OMText;
import org.apache.axiom.soap.SOAP11Constants;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
/**
 * 文件传输客户端
 * @author alex.yao
 *
 */
public class FileTransferClient {

	private static EndpointReference targetEPR = new EndpointReference("http://127.0.0.1:8082/services/FileTrans");

	public static boolean upload(String mailboxnum, short greetingType, File file, String fileType) {
		try {
			OMElement data = buildUploadEnvelope(mailboxnum, greetingType, file, fileType);
			Options options = buildOptions();
			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);
			System.out.println(data);
			OMElement ome = sender.sendReceive(data);
			System.out.println(ome);
			String b = ome.getText();
			return Boolean.parseBoolean(b);
		} catch (Exception e) {

		}
		return false;
	}

	public static InputStream download(String mailboxnum, short greetingType, String FileType) {
		try {
			OMElement data = buildDownloadEnvelope(mailboxnum, greetingType, FileType);
			Options options = buildOptions();
			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);
			System.out.println(data);
			OMElement ome = sender.sendReceive(data);
			System.out.println(ome);
			OMText binaryNode = (OMText) ome.getFirstOMChild();
			DataHandler actualDH = (DataHandler) binaryNode.getDataHandler();
			return actualDH.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static OMElement buildUploadEnvelope(String mailboxnum, short greetingType, File file, String FileType) {
		DataHandler expectedDH;
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://example.org/mtom/data", "x");
		OMElement data = fac.createOMElement("upload", omNs);
		OMElement fileContent = fac.createOMElement("fileContent", omNs);
		FileDataSource dataSource = new FileDataSource(file);
		expectedDH = new DataHandler(dataSource);
		OMText textData = fac.createOMText(expectedDH, true);
		fileContent.addChild(textData);
		OMElement mboxnum = fac.createOMElement("mailboxnum", omNs);
		mboxnum.setText(mailboxnum);
		OMElement gtType = fac.createOMElement("greetingType", omNs);
		gtType.setText(greetingType + "");
		OMElement fileType = fac.createOMElement("fileType", omNs);
		fileType.setText(FileType);

		data.addChild(mboxnum);
		data.addChild(gtType);
		data.addChild(fileType);
		data.addChild(fileContent);
		return data;
	}

	private static OMElement buildDownloadEnvelope(String mailboxnum, short greetingType, String FileType) {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://example.org/mtom/data", "x");
		OMElement data = fac.createOMElement("download", omNs);
		OMElement mboxnum = fac.createOMElement("mailboxnum", omNs);
		mboxnum.setText(mailboxnum);
		OMElement gtType = fac.createOMElement("greetingType", omNs);
		gtType.setText(greetingType + "");
		OMElement fileType = fac.createOMElement("fileType", omNs);
		fileType.setText(FileType);
		data.addChild(mboxnum);
		data.addChild(gtType);
		data.addChild(fileType);
		return data;
	}

	private static Options buildOptions() {
		Options options = new Options();
		options.setSoapVersionURI(SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI);
		options.setTo(targetEPR);
		// enabling MTOM in the client side
		options.setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
		options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
		return options;
	}

	public static void main(String agrs[]) {
//		String file = "‪D:\\PhotoZoom.Pro.7.0.2.7z";
		short gt = 2;
		String mn = "20170531";
		String ft = "txt";
//		boolean rtv = upload(mn, gt, new File(file), ft);
//		System.out.println(rtv);
		InputStream is = download(mn, gt, ft);
		try {
			OutputStream outputStream = new FileOutputStream("xxx.txt");
			int bytesWritten = 0;
			int byteCount = 0;
			byte[] bytes = new byte[1024];
			while ((byteCount = is.read(bytes)) != -1) {
				outputStream.write(bytes, bytesWritten, byteCount);
				bytesWritten += byteCount;
			}
			is.close();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 System.out.println("download finish.");
	}

}
