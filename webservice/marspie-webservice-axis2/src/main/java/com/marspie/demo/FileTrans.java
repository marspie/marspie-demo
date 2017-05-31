package com.marspie.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

//import org.apache.axiom.attachments.utils.IOUtils;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.OMText;
import org.apache.axis2.AxisFault;
import org.springframework.stereotype.Component;

/**
 * 文件传输
 * @author alex.yao
 *
 */
@Component
public class FileTrans {

	public static final String TMP_PATH = "d:/tmp";

	public OMElement upload(OMElement element) throws Exception {
		OMElement _fileContent = null;
		OMElement _mailboxnum = null;
		OMElement _greetingType = null;
		OMElement _fileType = null;
		System.out.println(element);
		for (Iterator _iterator = element.getChildElements(); _iterator.hasNext();) {
			OMElement _ele = (OMElement) _iterator.next();
			if (_ele.getLocalName().equalsIgnoreCase("fileContent")) {
				_fileContent = _ele;
			}
			if (_ele.getLocalName().equalsIgnoreCase("mailboxnum")) {
				_mailboxnum = _ele;
			}
			if (_ele.getLocalName().equalsIgnoreCase("greetingType")) {
				_greetingType = _ele;
			}
			if (_ele.getLocalName().equalsIgnoreCase("fileType")) {
				_fileType = _ele;
			}
		}

		if (_fileContent == null || _mailboxnum == null || _greetingType == null || _fileType == null) {
			throw new AxisFault("Either Image or FileName is null");
		}

		OMText binaryNode = (OMText) _fileContent.getFirstOMChild();

		String mboxNum = _mailboxnum.getText();
		String greetingType = _greetingType.getText();
		String fileType = _fileType.getText();

		String greetingstoreDir = TMP_PATH + "/" + mboxNum;
		File dir = new File(greetingstoreDir);
		if (!dir.exists()) {
			dir.mkdir();
		}
		String filePath = greetingstoreDir + "/" + greetingType + "." + fileType;
		File greetingFile = new File(filePath);
		if (greetingFile.exists()) {
			greetingFile.delete();
			greetingFile = new File(filePath);
		}

		// Extracting the data and saving
		DataHandler actualDH;
		actualDH = (DataHandler) binaryNode.getDataHandler();

		FileOutputStream imageOutStream = new FileOutputStream(greetingFile);
		InputStream is = actualDH.getInputStream();
//		imageOutStream.write(IOUtils.getStreamAsByteArray(is));
		// setting response
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace ns = fac.createOMNamespace("http://example.org/mtom/data", "x");
		OMElement ele = fac.createOMElement("response", ns);
		ele.setText("true");

		return ele;
	}

	public OMElement download(OMElement element) throws Exception {
		System.out.println(element);
		OMElement _mailboxnum = null;
		OMElement _greetingType = null;
		OMElement _fileType = null;
		for (Iterator _iterator = element.getChildElements(); _iterator.hasNext();) {
			OMElement _ele = (OMElement) _iterator.next();
			if (_ele.getLocalName().equalsIgnoreCase("mailboxnum")) {
				_mailboxnum = _ele;
			}
			if (_ele.getLocalName().equalsIgnoreCase("greetingType")) {
				_greetingType = _ele;
			}
			if (_ele.getLocalName().equalsIgnoreCase("fileType")) {
				_fileType = _ele;
			}
		}
		String mboxNum = _mailboxnum.getText();
		String greetingType = _greetingType.getText();
		String fileType = _fileType.getText();
		String filePath = TMP_PATH + "/" + mboxNum + "/" + greetingType + "." + fileType;
		FileDataSource dataSource = new FileDataSource(filePath);
		DataHandler expectedDH = new DataHandler(dataSource);

		OMFactory fac = OMAbstractFactory.getOMFactory();

		OMNamespace ns = fac.createOMNamespace("http://example.org/mtom/data", "x");
		OMText textData = fac.createOMText(expectedDH, true);
		OMElement ele = fac.createOMElement("response", ns);
		ele.addChild(textData);
		return ele;
	}

	public DataHandler downloadFile(OMElement element) {
		try{ 
			OMElement _mailboxnum = null;
			OMElement _greetingType = null;
			OMElement _fileType = null;
			for (Iterator _iterator = element.getChildElements(); _iterator.hasNext();) {
				OMElement _ele = (OMElement) _iterator.next();
				if (_ele.getLocalName().equalsIgnoreCase("mailboxnum")) {
					_mailboxnum = _ele;
				}
				if (_ele.getLocalName().equalsIgnoreCase("greetingType")) {
					_greetingType = _ele;
				}
				if (_ele.getLocalName().equalsIgnoreCase("fileType")) {
					_fileType = _ele;
				}
			}
			String mboxNum = _mailboxnum.getText();
			String greetingType = _greetingType.getText();
			String fileType = _fileType.getText();
			String filePath = TMP_PATH + "/" + mboxNum + "/" + greetingType + "." + fileType;
			System.err.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			return new DataHandler(new FileDataSource(new File(filePath)) {
				public String getContentType() {
					return "application/octet-stream";
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		}
		return null;
	}
}
