package com.github.east196.playground;

import cn.afterturn.easypoi.cache.manager.POICacheManager;
import cn.afterturn.easypoi.word.WordExportUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ReportExportTest {

    /**
     * 简单导出没有图片和Excel
     */
    @Test
    public void SimpleWordExport() {
        POICacheManager.setFileLoader(new FileLoaderImpl());
        String in = "docx/故障详情导出.docx";
        String out = "D:/simple.docx";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("department", "Easypoi");
        map.put("person", "JueYue");
        map.put("time", new SimpleDateFormat().format(new Date()));
        map.put("me", "JueYue");
        map.put("date", "2015-01-03");
        try {
            XWPFDocument doc = WordExportUtil.exportWord07(in
                    , map);
            FileOutputStream fos = new FileOutputStream(out);
            doc.write(fos);
            fos.close();

            int tableIndex=0;
            int rowIndex=0;
            int colIndex=0;
            String position = getText(doc, tableIndex, rowIndex, colIndex);
            log.debug("1,2,1: {}", position);
            Assertions.assertEquals("", position);

        } catch (Exception e) {
            e.printStackTrace();
        }




    }

    private String getText(XWPFDocument doc, int tableIndex, int rowIndex, int colIndex) {
        return doc.getTableArray(tableIndex).getRow(rowIndex).getCell(colIndex).getText();
    }
}
