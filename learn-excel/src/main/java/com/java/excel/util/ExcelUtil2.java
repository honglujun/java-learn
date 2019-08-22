package com.java.excel.util;

import com.java.excel.vo.ExcelVOAttribute;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.poi.ss.usermodel.CellType.STRING;

public class ExcelUtil2<T> {

//    Class<T> clazz;
//
//    public ExcelUtil2(Class<T> clazz) {
//        this.clazz = clazz;
//    }

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
     * @param inStr    输入流
     * @param fileType excel的类型.xls或者.xlsx
     * @return
     * @throws Exception
     */
    public static Workbook getWorkbook(InputStream inStr, String fileType) throws Exception {
        Workbook wb;
        if (EXCEL_XLS.equals(fileType)) {
            //2003-
            wb = new HSSFWorkbook(inStr);
        } else if (EXCEL_XLSX.equals(fileType)) {
            //2007+
            wb = new XSSFWorkbook(inStr);
        } else {
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

    /**
     * 导入
     *
     * @param sheetName
     * @param input
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> importExcel(String sheetName, InputStream input, Class<T> clazz) {
        List<T> list = new ArrayList<T>();
        try {
            // 获取工作簿
            Workbook workbook = getWorkbook(input, EXCEL_XLSX);
            Sheet sheet = workbook.getSheet(sheetName);
            if (!sheetName.trim().equals("")) {
                // 如果指定sheet名,则取指定sheet中的内容.
                sheet = workbook.getSheet(sheetName);
            }
            if (sheet == null) {
                // 如果传入的sheet名不存在则默认指向第1个sheet.
                sheet = workbook.getSheetAt(0);
            }
            int rows = sheet.getPhysicalNumberOfRows();
            // 有数据时才处理
            if (rows > 0) {
                // 得到类的所有field.
                Field[] allFields = clazz.getDeclaredFields();
                // 定义一个map用于存放列的序号和field.
                Map<Integer, Field> fieldsMap = new HashMap<>();
                for (Field field : allFields) {
                    // 将有注解的field存放到map中.
                    if (field.isAnnotationPresent(ExcelVOAttribute.class)) {
                        ExcelVOAttribute attr = field
                                .getAnnotation(ExcelVOAttribute.class);
                        // 获得列号
                        int col = getExcelCol(attr.column());
                        // System.out.println(col + "====" + field.getName());
                        // 设置类的私有字段属性可访问.
                        field.setAccessible(true);
                        fieldsMap.put(col, field);
                    }
                }
                // 从第2行开始取数据,默认第一行是表头.
                for (int i = 1; i < rows; i++) {
                    Row row = sheet.getRow(i);
                    int cellNum = row.getPhysicalNumberOfCells();
                    T entity = null;
                    for (int j = 0; j < cellNum; j++) {
                        Cell cell = row.getCell(j);
                        if (cell == null) {
                            continue;
                        }
                        String value = "";
                        CellType cellType = cell.getCellType();
                        switch (cellType) {
                            // 数字
                            case NUMERIC:
                                //如果为时间格式的内容
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    //注：format格式 yyyy-MM-dd hh:mm:ss 中小时为12小时制，若要24小时制，则把小h变为H即可，yyyy-MM-dd HH:mm:ss
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                                    value = sdf.format(HSSFDateUtil.getJavaDate(cell.
                                            getNumericCellValue())).toString();
                                    break;
                                } else {
                                    value = new DecimalFormat("0").format(cell.getNumericCellValue());
                                }
                                break;
                            // 字符串
                            case STRING:
                                value = cell.getStringCellValue();
                                break;
                            // Boolean
                            case BOOLEAN:
                                value = cell.getBooleanCellValue() + "";
                                break;
                            // 公式
                            case FORMULA:
                                value = cell.getCellFormula() + "";
                                break;
                            // 空值
                            case BLANK:
                                value = "";
                                break;
                            // 故障
                            case ERROR:
                                value = "非法字符";
                                break;
                            default:
                                value = "未知类型";
                                break;
                        }
                        System.out.println(value);
                        if (value.equals("")) {
                            continue;
                        }
                        // 如果不存在实例则新建.
                        entity = (entity == null ? clazz.newInstance() : entity);
                        // System.out.println(cells[j].getContents());
                        // 从map中得到对应列的field.
                        Field field = fieldsMap.get(j);
                        // 取得类型,并根据对象类型设置值.
                        Class<?> fieldType = field.getType();
                        if (String.class == fieldType) {
                            field.set(entity, String.valueOf(value));
                        } else if ((Integer.TYPE == fieldType)
                                || (Integer.class == fieldType)) {
                            field.set(entity, Integer.parseInt(value));
                        } else if ((Long.TYPE == fieldType)
                                || (Long.class == fieldType)) {
                            field.set(entity, Long.valueOf(value));
                        } else if ((Float.TYPE == fieldType)
                                || (Float.class == fieldType)) {
                            field.set(entity, Float.valueOf(value));
                        } else if ((Short.TYPE == fieldType)
                                || (Short.class == fieldType)) {
                            field.set(entity, Short.valueOf(value));
                        } else if ((Double.TYPE == fieldType)
                                || (Double.class == fieldType)) {
                            field.set(entity, Double.valueOf(value));
                        } else if (Character.TYPE == fieldType) {
                            if ((value != null) && (value.length() > 0)) {
                                field.set(entity, Character
                                        .valueOf(value.charAt(0)));
                            }
                        }
                    }
                    if (entity != null) {
                        list.add(entity);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 对list数据源将其里面的数据导入到excel表单
     *
     * @param sheetName 工作表的名称
     * @param sheetSize 每个sheet中数据的行数,此数值必须小于65536
     * @param fileName  excel名称
     */
    public static <T> boolean exportExcel(HttpServletResponse response, List<T> list, String sheetName, int sheetSize, String fileName, Class<T> clazz) {
        // 得到所有定义字段
        Field[] allFields = clazz.getDeclaredFields();
        List<Field> fields = new ArrayList<Field>();
        // 得到所有field并存放到一个list中.
        for (Field field : allFields) {
            if (field.isAnnotationPresent(ExcelVOAttribute.class)) {
                fields.add(field);
            }
        }
        // 产生工作薄对象.xlsx格式的
        // 若要导出.xls格式的用 HSSFWorkbook workbook = new HSSFWorkbook();其他的类也要相应的改动
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        workbook.setCompressTempFiles(true);
        // excel2003中每个sheet中最多有65536行,为避免产生错误所以加这个逻辑.
        if (sheetSize > 65536 || sheetSize < 1) {
            sheetSize = 65536;
        }
        // 取出一共有多少个sheet.
        double sheetNo = Math.ceil(list.size() / sheetSize);
        for (int index = 0; index <= sheetNo; index++) {
            // 产生工作表对象
            SXSSFSheet sheet = workbook.createSheet();
            if (sheetNo == 0) {
                workbook.setSheetName(index, sheetName);
            } else {
                // 设置工作表的名称.
                workbook.setSheetName(index, sheetName + index);
            }
            SXSSFRow row;
            // 产生单元格
            SXSSFCell cell;
            // 产生一行
            row = sheet.createRow(0);
            // 写入各个字段的列头名称
            for (int i = 0; i < fields.size(); i++) {
                Field field = fields.get(i);
                ExcelVOAttribute attr = field.getAnnotation(ExcelVOAttribute.class);
                // 获得列号
                int col = getExcelCol(attr.column());
                // 创建列
                cell = row.createCell(col);
                // 设置列中写入内容为String类型
                cell.setCellType(STRING);
                // 写入列名
                cell.setCellValue(attr.name());
                // 如果设置了提示信息则鼠标放上去提示.
                if (!attr.prompt().trim().equals("")) {
                    // 这里默认设了2-101列提示.
                    setHSSFPrompt(sheet, "", attr.prompt(), 1, 100, col, col);
                }
                // 如果设置了combo属性则本列只能选择不能输入
                if (attr.combo().length > 0) {
                    // 这里默认设了2-101列只能选择不能输入.
                    setHSSFValidation(sheet, attr.combo(), 1, 100, col, col);
                }
            }
            int startNo = index * sheetSize;
            int endNo = Math.min(startNo + sheetSize, list.size());
            // 写入各条记录,每条记录对应excel表中的一行
            for (int i = startNo; i < endNo; i++) {
                row = sheet.createRow(i + 1 - startNo);
                // 得到导出对象.
                T vo = (T) list.get(i);
                for (int j = 0; j < fields.size(); j++) {
                    // 获得field.
                    Field field = fields.get(j);
                    // 设置实体类私有属性可访问
                    field.setAccessible(true);
                    ExcelVOAttribute attr = field
                            .getAnnotation(ExcelVOAttribute.class);
                    try {
                        // 根据ExcelVOAttribute中设置情况决定是否导出,有些情况需要保持为空,希望用户填写这一列.
                        if (attr.isExport()) {
                            // 创建cell
                            cell = row.createCell(getExcelCol(attr.column()));
                            cell.setCellType(STRING);
                            // 如果数据存在就填入,不存在填入空格.
                            cell.setCellValue(field.get(vo) == null ? "" : String.valueOf(field.get(vo)));
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        try {
            setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            workbook.write(os);
            os.flush();
            os.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Output is closed ");
            return false;
        }
    }

    /**
     * 将EXCEL中A,B,C,D,E列映射成0,1,2,3
     *
     * @param col
     */
    public static int getExcelCol(String col) {
        col = col.toUpperCase();
        // 从-1开始计算,字母重1开始运算。这种总数下来算数正好相同。
        int count = -1;
        char[] cs = col.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            count += (cs[i] - 64) * Math.pow(26, cs.length - 1 - i);
        }
        return count;
    }

    /**
     * 设置单元格上提示
     *
     * @param sheet         要设置的sheet.
     * @param promptTitle   标题
     * @param promptContent 内容
     * @param firstRow      开始行
     * @param endRow        结束行
     * @param firstCol      开始列
     * @param endCol        结束列
     * @return 设置好的sheet.
     */
    public static SXSSFSheet setHSSFPrompt(SXSSFSheet sheet, String promptTitle,
                                           String promptContent, int firstRow, int endRow, int firstCol,
                                           int endCol) {
        // 构造constraint对象
        DVConstraint constraint = DVConstraint
                .createCustomFormulaConstraint("DD1");
        // 四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow,
                endRow, firstCol, endCol);
        // 数据有效性对象
        HSSFDataValidation data_validation_view = new HSSFDataValidation(
                regions, constraint);
        data_validation_view.createPromptBox(promptTitle, promptContent);
        sheet.addValidationData(data_validation_view);
        return sheet;
    }

    /**
     * 设置某些列的值只能输入预制的数据,显示下拉框.
     *
     * @param sheet    要设置的sheet.
     * @param textlist 下拉框显示的内容
     * @param firstRow 开始行
     * @param endRow   结束行
     * @param firstCol 开始列
     * @param endCol   结束列
     * @return 设置好的sheet.
     */
    public static SXSSFSheet setHSSFValidation(SXSSFSheet sheet,
                                               String[] textlist, int firstRow, int endRow, int firstCol,
                                               int endCol) {
        // 加载下拉列表内容
        DVConstraint constraint = DVConstraint
                .createExplicitListConstraint(textlist);
        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow,
                endRow, firstCol, endCol);
        // 数据有效性对象
        HSSFDataValidation data_validation_list = new HSSFDataValidation(
                regions, constraint);
        sheet.addValidationData(data_validation_list);
        return sheet;
    }

    /**
     * 发送响应流方法
     *
     * @param response
     * @param fileName
     */
    public static void setResponseHeader(HttpServletResponse response, String fileName) {
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
