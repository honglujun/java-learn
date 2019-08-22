package com.java.excel.util;

import lombok.extern.slf4j.Slf4j;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class BaseExcel {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	HttpServletResponse response;

	/**
	 * 根据模板导出Excel
	 * 
	 * @param templateFileName 模板文件名（文件统一放置在工程根目录webapp的templates/excel/目录下）
	 * @param beans            实体对象，对象根为root，如${root.brandName}；如果有多个，要放到map里，对象的根可以自定义
	 * @param exportFileName   导出的文件名
	 */
	@SuppressWarnings("unchecked")
	protected void exportFromXlsTemplate(Object beans, String exportFileName, String templateFileName) {
		String contentType = "application/vnd.ms-excel;charset=utf-8";
		response.setContentType(contentType);
		String temp = DateUtil.toDateString(new Date(), DateUtil.DATE_PATTERN_YYYYMMDDHHMMSS);
		if (templateFileName.contains(".xlsx")) {
			temp = exportFileName.substring(0, exportFileName.indexOf(".xlsx")) + temp + exportFileName.substring(exportFileName.indexOf(".xlsx"), exportFileName.length());
		} else {
			temp = exportFileName.substring(0, exportFileName.indexOf(".xls")) + temp + exportFileName.substring(exportFileName.indexOf(".xls"), exportFileName.length());
		}
		exportFileName = getDownloadFileName(temp, request);
		response.setHeader("Content-Disposition", "attachment;filename=" + exportFileName);
		XLSTransformer transformer = new XLSTransformer();
		Map<String, Object> map = new HashMap<String, Object>();
		if (beans instanceof Map) {
			map = (Map<String, Object>) beans;
		} else {
			map = new HashMap<String, Object>();
			map.put("root", beans);
		}
		String realpath = request.getServletContext().getRealPath("/");
		Workbook workbook;
		try {
			workbook = transformer.transformXLS(new FileInputStream(realpath + "/templates/excel/" + templateFileName), map);
			OutputStream out = response.getOutputStream();
			workbook.write(out);
			out.flush();
			out.close();
		} catch (ParsePropertyException e) {
			log.error("there were any problems in evaluating specified property value from a bean", e);
		} catch (InvalidFormatException e) {
			log.error("格式不正确", e);
		} catch (FileNotFoundException e) {
			log.error("模板文件不存在", e);
		} catch (IOException e) {
			String s = e.getClass().getSimpleName();
			if (!s.equals("ClientAbortException")) {
				log.error("根据模板导出Excel时发生异常", e);
			}
		}
	}

	protected String getDownloadFileName(String filename, HttpServletRequest request) {
		String s = "";
		try {
			if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
				s = URLEncoder.encode(filename, "UTF-8");
			} else {
				s = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
			}
		} catch (UnsupportedEncodingException e) {
			log.error("导出文件文件名转码时出现错误", e);
		}
		return s;
	}
	
}
