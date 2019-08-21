package com.java.excel.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.apache.poi.ss.usermodel.CellType.BLANK;

public class ExcelUtil {

    /**
     * 2003- 版本的excel
     **/
    private static final String EXCEL_XLS = ".xls";

    /**
     * 2007+ 版本的excel
     **/
    private static final String EXCEL_XLSX = ".xlsx";

    /**
     * 获取--Excel工作薄
     *
     * @param inStr,fileName
     * @return
     * @throws Exception
     */
    public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook wb;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (EXCEL_XLS.equals(fileType)) {
            wb = new HSSFWorkbook(inStr);  //2003-
        } else if (EXCEL_XLSX.equals(fileType)) {
            wb = new XSSFWorkbook(inStr);  //2007+
        } else {
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

    /**
     * 描述：获取工作薄里的第一页
     *
     * @param work
     * @return
     * @throws Exception
     */
    public Sheet getFirstSheetByWorkbook(Workbook work) throws Exception {
        if (null == work) {
            throw new Exception("Excel工作簿内容为空！");
        }
        return work.getSheetAt(0);
    }

    /**
     * 描述：获取工作薄里的所有页
     *
     * @param work
     * @return
     * @throws Exception
     */
    public List<Sheet> getListSheetByWorkbook(Workbook work) throws Exception {
        if (null == work) {
            throw new Exception("Excel工作簿内容为空！");
        }
        List<Sheet> list = new ArrayList<>();
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            list.add(work.getSheetAt(i));
        }
        return list;
    }

    /**
     * 获取--数据行不包含数据行
     *
     * @param sheet
     * @return
     */
    public List<Row> getListRowBySheet(Sheet sheet) {
        if (null == sheet) {
            return null;
        }
        List<Row> list = new ArrayList<>();
        for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null || row.getFirstCellNum() == i) {
                continue;
            }
            if (checkRow(row)) {
                list.add(row);
            }
        }
        return list;
    }

    /**
     * 检查:行是否为空
     *
     * @param row
     * @return
     */
    private boolean checkRow(Row row) {
        for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if (cell != null && cell.getCellType() != BLANK) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取--指定页中所有单元格数据
     *
     * @param sheet
     * @return
     * @throws Exception
     */
    public List<CellVO> getListCellBySheet(Sheet sheet) {
        if (null == sheet) {
            return null;
        }
        Row row = null;      //行数，行数据
        Cell cell = null;    //列数, 单元格
        // 遍历所有的数据
        List<CellVO> list = new ArrayList<>();
        for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
            row = sheet.getRow(j);
            if (row == null || row.getFirstCellNum() == j) {
                continue;
            }
            //遍历所有的列
            for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                cell = row.getCell(y);
                String value = this.getValue(cell);

                // 组装单元格数据
                CellVO cellVO = newCellVO();
                cellVO.setRankNum(y);
                cellVO.setRowNum(j);
                cellVO.setValue(value);
                list.add(cellVO);
            }
        }
        return list;
    }

    /**
     * 获取--指定行单元格数据。
     *
     * @param row
     * @return
     */
    public List<CellVO> getListCellByRow(Row row, int headerSize) {
        if (null == row) {
            return null;
        }
        List<CellVO> list = new ArrayList<>();
        //遍历所有的列
        for (int y = row.getFirstCellNum(); y < headerSize; y++) {
            Cell cell = row.getCell(y);
            String value = this.getValue(cell);
            // 组装单元格数据
            CellVO cellVO = newCellVO();
            cellVO.setRankNum(y);
            cellVO.setRowNum(row.getRowNum());
            cellVO.setValue(value);
            list.add(cellVO);
        }
        return list;
    }

    /**
     * 获取--Excel指定页里的标头信息
     *
     * @param sheet
     * @return
     */
    public List<String> getHeaderBySheet(Sheet sheet) {
        if (sheet == null) {
            return null;
        }
        List<String> listHeader = new ArrayList<>();
        // 标头信息的一般为Excel中的第一行数据
        Row row = sheet.getRow(0);
        if (null == row) {
            return null;
        }
        for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
            Cell cell = row.getCell(y);
            String value = this.getValue(cell);
            listHeader.add(value);
        }
        return listHeader;
    }

    /**
     * 描述：单元格数值进行格式化
     *
     * @param cell
     * @return
     */
    public String getValue(Cell cell) {
        String value = "";
        if (null == cell) {
            return value;
        }
        switch (cell.getCellType()) {
            //数值型
            case NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    //如果是date类型则 ，获取该cell的date值
                    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    value = format.format(date);
                } else { // 纯数字
                    BigDecimal big = new BigDecimal(cell.getNumericCellValue());
                    value = big.toString();
                    //解决1234.0  去掉后面的.0
                    if (null != value && !"".equals(value.trim())) {
                        String[] item = value.split("[.]");
                        if (1 < item.length && "0".equals(item[1])) {
                            value = item[0];
                        }
                    }
                }
                break;
            //字符串类型
            case STRING:
                value = cell.getStringCellValue().toString();
                break;
            // 公式类型
            case FORMULA:
                //读公式计算值
                value = String.valueOf(cell.getNumericCellValue());
                if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串
                    value = cell.getStringCellValue().toString();
                }
                break;
            // 布尔类型
            case BOOLEAN:
                value = " " + cell.getBooleanCellValue();
                break;
            default:
                value = cell.getStringCellValue().toString();
        }
        if ("null".endsWith(value.trim())) {
            value = "";
        }
        return value;
    }

    /**
     * 获取-指定文件流第一页中的所有单元格数据转变为对象信息后返回
     *
     * @param file
     * @return
     */
    public List<Map<String, CellVO>> getDataByByMultipartFile(MultipartFile file, List<String> excelHeaders) throws Exception {
        String fileName = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        Workbook workbook = getWorkbook(inputStream, fileName);
        Sheet sheet = getFirstSheetByWorkbook(workbook);
        List<String> headerList = getHeaderBySheet(sheet);
        // 检查文件抬头与规定抬头是否符合
        if (null == headerList || headerList.isEmpty()) {
            throw new Exception("Excel文件的抬头内容为空，请检查文件内容是否正确");
        }
        if (!checkExcelHeader(headerList, excelHeaders)) {
            throw new Exception("Excel文件的抬头内容与规定的抬头内容不符，请检查文件内容是否正确");
        }
        return getDataBySheet(sheet);
    }

    private boolean checkExcelHeader(List<String> headerList, List<String> excelHeaders) {
        if (headerList.size() != excelHeaders.size()) {
            return false;
        }
        Collections.sort(headerList);
        Collections.sort(excelHeaders);
        for (int i = 0; i < headerList.size(); i++) {
            if (!headerList.get(i).equals(excelHeaders.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取-将指定页的所有单元格数据组装为对象信息
     *
     * @param sheet
     * @return
     */
    public List<Map<String, CellVO>> getDataBySheet(Sheet sheet) throws Exception {
        // 获取标头数据
        List<String> headers = this.getHeaderBySheet(sheet);
        if (null == headers || headers.isEmpty()) {
            return null;
        }
        // 获取有效数据行
        List<Row> listRow = this.getListRowBySheet(sheet);
        if (null == listRow || listRow.isEmpty()) {
            return null;
        }
        // 循环处理：获取行中所有单元格数据
        List<Map<String, CellVO>> list = new ArrayList<>();
        for (Row row : listRow) {
            Map<String, CellVO> map = new HashMap<>();
            List<CellVO> listCellVO = this.getListCellByRow(row, headers.size());
            if (listCellVO.size() != headers.size()) {
                throw new Exception("Excel文件格式错误，请检查文件内容是否正确");
            }
            for (int i = 0; i < headers.size(); i++) {
                if (StringUtils.isBlank(headers.get(i))) {
                    continue;
                }
                map.put(headers.get(i), listCellVO.get(i));
            }
            list.add(map);
        }
        return list;
    }

    public class CellVO {
        // 单元格里的值
        private String value;

        // 所在行数
        private Integer rowNum;

        // 所在列数
        private Integer rankNum;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Integer getRowNum() {
            return rowNum;
        }

        public void setRowNum(Integer rowNum) {
            this.rowNum = rowNum;
        }

        public Integer getRankNum() {
            return rankNum;
        }

        public void setRankNum(Integer rankNum) {
            this.rankNum = rankNum;
        }
    }

    public CellVO newCellVO() {
        return new CellVO();
    }

    /**
     * 导出--excel表格
     *
     * @param response
     * @param fileName
     * @param sheetName
     * @param header
     * @param arryList
     */
    public void excelExport(HttpServletResponse response, String fileName, String sheetName, String[] header, List<List<String>> arryList) {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet hssfSheet = wb.createSheet(sheetName);
        for (int i = 0; i < header.length; i++) {
            hssfSheet.setColumnWidth(i, (int) ((20 + 0.72) * 256)); //宽度设置
        }
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = hssfSheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.BLUE_GREY.getIndex()); // 设置一个背景填充色
        style.setFillPattern(FillPatternType.forInt(FillPatternType.SOLID_FOREGROUND.getCode()));
        for (int i = 0; i < header.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(header[i]);
            cell.setCellStyle(style);
        }
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到
        for (int i = 0; i < arryList.size(); i++) {
            row = hssfSheet.createRow(i + 1);
            List list = arryList.get(i);
            for (int j = 0; j < list.size(); j++) {
                row.createCell(j).setCellValue(Objects.toString(list.get(j)));
            }
        }
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送响应流方法
     *
     * @param response
     * @param fileName
     */
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
