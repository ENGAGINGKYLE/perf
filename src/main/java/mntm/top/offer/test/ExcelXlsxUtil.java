package mntm.top.offer.test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: sdk-platform
 * @description:
 * @author: yingjun
 * @create: 2020-01-03 16:57
 **/
public class ExcelXlsxUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExcelXlsxUtil.class.getName());

    private final static String excel2003L = ".xls";    //2003- 版本的excel
    private final static String excel2007U = ".xlsx";   //2007+ 版本的excel


    public static <T> List<T> excelToList(InputStream in, String sheetName, Class<T> entityClass,
                                          ImmutableMap<String, Object> fieldMap, String fileName) throws Exception {
        List<T> resultList = Lists.newArrayList();

        try {
            Workbook workbook = getWorkbook(in, fileName);
            Sheet sheet = workbook.getSheet(sheetName);

            //获取工作表的有效行数
            int realRows = sheet.getLastRowNum();
            Row firstRow = sheet.getRow(0);
            int cellNum = firstRow.getLastCellNum();
            String[] excelFieldNames = new String[firstRow.getPhysicalNumberOfCells()];

            //获取Excel中的列名
            for (int i = 0; i < cellNum; i++) {
                excelFieldNames[i] = firstRow.getCell(i).getStringCellValue().trim();
            }

            //判断需要的字段在Excel中是否都存在
            boolean isExist = true;
            List<String> excelFieldList = Arrays.asList(excelFieldNames);
            for (String cnName : fieldMap.keySet()) {
                if (!excelFieldList.contains(cnName)) {
                    isExist = false;
                    break;
                }
            }

            //如果有列名不存在，则抛出异常，提示错误
            if (!isExist) {
                throw new ExcelException("Excel中缺少必要的字段，或字段名称有误");
            }

            //将列名和列号放入Map中,这样通过列名就可以拿到列号
            LinkedHashMap<String, Integer> colMap = new LinkedHashMap<String, Integer>();
            for (int i = 0; i < excelFieldNames.length; i++) {
                colMap.put(excelFieldNames[i], firstRow.getCell(i).getColumnIndex());
            }


            //遍历当前sheet中的所有行
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                T entity = entityClass.newInstance();
                Row row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }
                //给对象中的字段赋值
                for (Map.Entry<String, Object> entry : fieldMap.entrySet()) {
                    //获取中文字段名
                    String cnNormalName = entry.getKey();
                    //获取英文字段名
                    String enNormalName = entry.getValue().toString();
                    //根据中文字段名获取列号
                    int col = colMap.get(cnNormalName);
                    row.getCell(col).setCellType(Cell.CELL_TYPE_STRING);
                    //获取当前单元格中的内容
                    String content = row.getCell(col).getStringCellValue().trim();
                    //给对象赋值
                    setFieldValueByName(enNormalName, content, entity);
                }
                resultList.add(entity);

            }
        } catch (Exception e) {
            throw e;
        }
        return resultList;
    }

    /**
     * 根据字段名给对象的字段赋值
     *
     * @param fieldName  字段名
     * @param fieldValue 字段值
     * @param o          对象
     * @throws Exception 异常
     */
    public static void setFieldValueByName(String fieldName, Object fieldValue, Object o) throws Exception {

        Field field = getFieldByName(fieldName, o.getClass());
        if (field != null) {
            field.setAccessible(true);
            // 获取字段类型
            Class<?> fieldType = field.getType();

            // 根据字段类型给字段赋值
            if (String.class == fieldType) {
                field.set(o, String.valueOf(fieldValue));
            } else if ((Integer.TYPE == fieldType) || (Integer.class == fieldType)) {
                field.set(o, Integer.parseInt(fieldValue.toString()));
            } else if ((Long.TYPE == fieldType) || (Long.class == fieldType)) {
                field.set(o, Long.valueOf(fieldValue.toString()));
            } else if ((Float.TYPE == fieldType) || (Float.class == fieldType)) {
                field.set(o, Float.valueOf(fieldValue.toString()));
            } else if ((Short.TYPE == fieldType) || (Short.class == fieldType)) {
                field.set(o, Short.valueOf(fieldValue.toString()));
            } else if ((Double.TYPE == fieldType) || (Double.class == fieldType)) {
                field.set(o, Double.valueOf(fieldValue.toString()));
            } else if (fieldType == BigDecimal.class) {
                field.set(o,new BigDecimal(fieldValue.toString()));
            } else if (Character.TYPE == fieldType) {
                if ((fieldValue != null) && (fieldValue.toString().length() > 0)) {
                    field.set(o, Character.valueOf(fieldValue.toString().charAt(0)));
                }
            } else if (Date.class == fieldType) {
                if (!fieldValue.toString().isEmpty()) {
                    if (fieldValue.toString().length() > 10) {

                        field.set(o, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fieldValue.toString()));
                    } else {
                        field.set(o, new SimpleDateFormat("yyyy-MM-dd").parse(fieldValue.toString()));
                    }
                }
            } else {
                field.set(o, fieldValue);
            }
        } else {
            throw new ExcelException(o.getClass().getSimpleName() + "类不存在字段名 " + fieldName);
        }
    }

    /**
     * 根据文件后缀，自适应上传文件的版本
     *
     * @param in,fileName
     * @return
     * @throws Exception
     */
    private static Workbook getWorkbook(InputStream in, String fileName) throws Exception {
        Workbook wb;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (excel2003L.equals(fileType)) {
            wb = new HSSFWorkbook(in);
        } else if (excel2007U.equals(fileType)) {
            wb = new XSSFWorkbook(in);
        } else {
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

    /**
     * 对表格中数值进行格式化
     * @param cell
     * @return
     */
    private static Object getCellValue(Cell cell){
        Object value = null;
        DecimalFormat strFormat = new DecimalFormat("0");  //格式化number String字符
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
        DecimalFormat numFormat = new DecimalFormat("0.00");  //格式化数字

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if("General".equals(cell.getCellStyle().getDataFormatString())){
                    value = strFormat.format(cell.getNumericCellValue());
                }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){
                    value = dateFormat.format(cell.getDateCellValue());
                }else{
                    value = numFormat.format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            default:
                break;
        }
        return value;
    }


    /**
     * 根据字段名获取字段
     *
     * @param fieldName 字段名
     * @param clazz     包含该字段的类
     * @return 字段
     */
    public static Field getFieldByName(String fieldName, Class<?> clazz) {
        // 拿到本类的所有字段
        Field[] selfFields = clazz.getDeclaredFields();

        // 如果本类中存在该字段，则返回
        for (Field field : selfFields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }

        // 否则，查看父类中是否存在此字段，如果有则返回
        Class<?> superClazz = clazz.getSuperclass();
        if (superClazz != null && superClazz != Object.class) {
            return getFieldByName(fieldName, superClazz);
        }

        // 如果本类和父类都没有，则返回空
        return null;
    }
}
