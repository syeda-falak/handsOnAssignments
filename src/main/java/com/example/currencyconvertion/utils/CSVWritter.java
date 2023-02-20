package com.example.currencyconvertion.utils;

import com.example.currencyconvertion.dto.ExcelDto;
import com.example.currencyconvertion.dto.RestCallResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.stat.internal.EntityStatisticsImpl;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Date;

@Component
public class CSVWritter {
    private static final Logger logger= LoggerFactory.getLogger(CSVWritter.class);

    public boolean makeEntry(String jsonObject) throws Exception {
        ObjectMapper objectMapper=new ObjectMapper();
        RestCallResponseDto restCallResponseDto=objectMapper.readValue(jsonObject,RestCallResponseDto.class);
        ExcelDto excelDto=new ExcelDto();
        excelDto.setCreateTs(new Date());
        excelDto.setRate(restCallResponseDto.getInfo().getRate());
        excelDto.setBaseCurrency(restCallResponseDto.getQuery().getFrom());
        excelDto.setConversionCurrency(restCallResponseDto.getQuery().getTo());
        System.out.println(restCallResponseDto);

        File file=new File("currencyOutputFile.xlsx");
        FileInputStream fip ;
        Workbook workbook;
        Sheet spreadsheet;

        if(!file.exists()){
            workbook=new XSSFWorkbook();
            spreadsheet=workbook.createSheet("currencyOutputFile.xlsx");
            int rowCount=0;
            //Creating new row from the next row count
            Row row1 = spreadsheet.createRow(rowCount++);
            CellStyle dateCellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
            row1.createCell(0).setCellValue("BASE CURRENCY");
            row1.createCell(1).setCellValue("CONVERSION_CURRENCY");
            row1.createCell(2).setCellValue("RATE");
            row1.createCell(3).setCellValue("CREATE_TS");

            Row row2=spreadsheet.createRow(rowCount++);
            row2.createCell(0).setCellValue(excelDto.getBaseCurrency());
            row2.createCell(1).setCellValue(excelDto.getConversionCurrency());
            row2.createCell(2).setCellValue(excelDto.getRate());
            Cell date=row2.createCell(3);
            date.setCellStyle(dateCellStyle);
            date.setCellValue(excelDto.getCreateTs());


        }
        else{
            fip= new FileInputStream(file);
            workbook= WorkbookFactory.create(fip);
            spreadsheet=workbook.getSheetAt(0);
            int rowCount=spreadsheet.getLastRowNum();
            //Creating new row from the next row count
            Row row = spreadsheet.createRow(++rowCount);
            CellStyle dateCellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
            row.createCell(0).setCellValue(excelDto.getBaseCurrency());
            row.createCell(1).setCellValue(excelDto.getConversionCurrency());
            row.createCell(2).setCellValue(excelDto.getRate());
            Cell date=row.createCell(3);
            date.setCellStyle(dateCellStyle);
            date.setCellValue(excelDto.getCreateTs());
            fip.close();

        }
        FileOutputStream os = new FileOutputStream(file);
        workbook.write(os);
        os.close();
        logger.info("File Uploaded successfully");
        return true;
    }
}
