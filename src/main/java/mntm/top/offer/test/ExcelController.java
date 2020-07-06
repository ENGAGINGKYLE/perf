package mntm.top.offer.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * className：FeginController
 * description： TODO
 * time：2020-05-29.15:36
 *
 * @author Tank
 * @version 1.0.0
 */
@RestController
@Slf4j
public class ExcelController {

    public static final String filedMapStr = "{\n" +
            "    \"事件名称\": \"name\",\n" +
            "    \"事件Id\": \"identityId\",\n" +
            "    \"平台\": \"platform\",\n" +
            "}";

    @PostMapping("/txt")
    public String testFeign(@RequestParam("file") MultipartFile excelFile) throws Exception {
//
//        JSONObject excelJson = JSON.parseObject(filedMapStr);
//        ImmutableMap<String, Object> excelFiledMap = ImmutableMap.copyOf(excelJson);
//        List<EventInfo> eventInfos = ExcelXlsxUtil.excelToList(excelFile.getInputStream(), "Sheet1", EventInfo.class, excelFiledMap, excelFile.getOriginalFilename());
        read((FileInputStream) excelFile.getInputStream());

        return "testFeign";
    }

    public static List<List<String>> read(FileInputStream inputStream) throws Exception {

        //new一个workbook
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        //创建一个sheet对象，参数为sheet的索引
        XSSFSheet sheet = workbook.getSheetAt(0);
        //new出存放一张表的二维数组
        List<List<String>> allData = new ArrayList<List<String>>();

        for (Row row:sheet) {
            List<String> oneRow = new ArrayList<String>();
            //不读表头
            if(row.getRowNum()==0){
                continue;
            }
            for (Cell cell : row) {
                cell.setCellType(Cell.CELL_TYPE_STRING);
                oneRow.add(cell.getStringCellValue().trim());

                /**
                 * 记录第一行事件 id 为当前事件 id
                 * if(第一列为空){
                 *     取后四列；
                 *     新增事件属性数据并关联当前事件 id；
                 * }else{
                 *     直接取出并修改当前事件 id;
                 *     新增事件数据和事件属性数据并关联当前事件 id；
                 * }
                 *
                 */
            }
            allData.add(oneRow);
        }

        for (int i = 0; i < allData.size(); i++) {
            System.out.println(allData.get(i));
        }
        //关闭workbook
        workbook.close();
        return allData;
    }
}
