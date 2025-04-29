package com.example.demo.service;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import static org.apache.poi.ss.usermodel.Row.MissingCellPolicy.RETURN_BLANK_AS_NULL;

@Service
@RequiredArgsConstructor
public class ComfortService {
    public Integer findMin(String file, Integer n) {
        Set<Integer> data = new TreeSet<>();
        int colIndex = 0;

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Cell cell = row.getCell(colIndex, RETURN_BLANK_AS_NULL);
                if (cell != null) {
                    Integer num = (int) cell.getNumericCellValue();
                    data.add(num);
                }
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка");
        }

        if (n <= 0 || n > data.size()) {
            throw new IllegalArgumentException("Некорректное N");
        } else {
            int count = 0;
            for (Integer nth : data) {
                count++;
                if (count == n) {
                    return nth;
                }
            }
            return null;
        }
    }
}
