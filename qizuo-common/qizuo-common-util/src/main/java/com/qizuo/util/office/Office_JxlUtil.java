///*
// * Copyright (c) 2020.
// * author：qizuo
// */
//package com.qizuo.util.office;
//
//import jxl.Cell;
//import jxl.Sheet;
//import jxl.Workbook;
//import jxl.WorkbookSettings;
//import jxl.format.Alignment;
//import jxl.format.Border;
//import jxl.format.BorderLineStyle;
//import jxl.format.VerticalAlignment;
//import jxl.read.biff.BiffException;
//import jxl.write.*;
//import org.qizuo.cm.modules.base.pojo.BackResultPoJo;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author: fangl
// * @description: office工具类:jxl主要针对excel这一块
// * @date: 9:10 2019/2/12
// */
//@Slf4j
//public class Office_JxlUtil {
//    /**
//     * **************************Workbook*********************************
//     *
//     * @author: fangl
//     * @description: 导入:注jxl不支持xlsx的格式，只支持xls，所以读取的excel的时候要注意了
//     * @date: 12:00 2019/2/27
//     */
//    public BackResultPoJo imp(Workbook workbook, int index) {
//        if (null == workbook) {
//            return new BackResultPoJo(BackResultPoJo.FAILURE, "工作表为空");
//        }
//        try {
//
//            //获得一个工作表对象
//            Sheet sheet = workbook.getSheet(index);
//            //行数
//            int rows = sheet.getRows();
//            //列数
//            int columns = sheet.getColumns();
//            //判断表头和行数是否合格
//            if (rows == 0 && 0 == columns) {
//                return new BackResultPoJo(BackResultPoJo.FAILURE, "列或者行数为0");
//            }
//            //返回数据
//            return imp_readFinal(sheet);
//        } catch (Exception e) {
//            log.error("异常={}", e.getMessage(), e);
//            return new BackResultPoJo(BackResultPoJo.ERROR, "异常");
//        } finally {
//            if (workbook != null) {
//                workbook.close();
//            }
//        }
//    }
//
//    /**
//     * @author: fangl
//     * @description: 最终执行方法
//     * @date: 9:05 2019/2/28
//     */
//    public BackResultPoJo imp_readFinal(Sheet sheet) {
//        //行数
//        int rows = sheet.getRows();
//        //列数
//        int columns = sheet.getColumns();
//
//        //外层返回数据
//        Map<Integer, Map<Integer, Object>> backMap = new HashMap<>();
//
//        // 遍历每行每列的单元格
//        for (int i = 0; i < rows; i++) {
//            //内层返回数据
//            Map<Integer, Object> map = new HashMap<>();
//            for (int j = 0; j < columns; j++) {
//                Cell cell = sheet.getCell(j, i);
//                String result = cell.getContents();
//                //数据装配
//                map.put(j, result);
//            }
//        }
//        //返回数据
//        return new BackResultPoJo(BackResultPoJo.SUCCESS, backMap);
//    }
//
//    /**
//     * @author: fangl
//     * @description: 获取workbook对象
//     * @date: 9:05 2019/2/28
//     */
//    public Workbook imp_getWorkbook() {
//        try {
//            Workbook.getWorkbook(new File(""));
//            Workbook.getWorkbook(new File(""), new WorkbookSettings());
//            Workbook.getWorkbook(new FileInputStream(""));
//            Workbook.getWorkbook(new FileInputStream(""), new WorkbookSettings());
//        } catch (IOException e) {
//            log.error("异常={}", e.getMessage(), e);
//        } catch (BiffException e) {
//            log.error("异常={}", e.getMessage(), e);
//        }
//        return null;
//    }
//
//    /**
//     * **************************WritableWorkbook*********************************
//     *
//     * @author: fangl
//     * @description: 导出
//     * @date: 12:00 2019/2/27
//     */
//    public void exp(WritableWorkbook workbook, Map<Integer, Map<Integer, String>> inMap, int index, int beginRow) {
//        /*response.setCharacterEncoding("utf-8");
//        response.setContentType("application/vnd.ms-excel");
//        fileName = URLEncoder.encode(fileName, "UTF-8");
//        response.addHeader("Content-Disposition", "attachment;filename*=utf-8/'zh_cn/'" + fileName + ".xls");
//        OutputStream os = response.getOutputStream();
//        WritableWorkbook workbook = Workbook.createWorkbook(os);
//        os.close();
//        response.flushBuffer();*/
//
//        try {
//            /** 样式 */
//            Map<String, WritableCellFormat> mapStyle = exp_style();
//            /** 创建excel开始 */
//            //创建sheet(名称、index)(可以有很多sheet)
//            WritableSheet sheet = workbook.createSheet(inMap.get(0).get(0), index);
//            //表头
//            exp_head(sheet, mapStyle, inMap);
//            //表体
//            exp_body(sheet, mapStyle, inMap, beginRow);
//            //最后写入和关闭流
//            workbook.write();
//            workbook.close();
//        } catch (WriteException e) {
//            log.error("异常={}", e.getMessage(), e);
//        } catch (IOException e) {
//            log.error("异常={}", e.getMessage(), e);
//        }
//    }
//
//    /**
//     * @author: fangl
//     * @description: 表头
//     * @date: 10:18 2019/2/28
//     */
//    public void exp_head(WritableSheet sheet, Map<String, WritableCellFormat> mapStyle, Map<Integer, Map<Integer, String>> inMap) {
//        try {
//            //第一头（第一行第一列）
//            Label labelC = new Label(0, 0, inMap.get(0).get(0), mapStyle.get("title"));
//            sheet.addCell(labelC);
//            //单元格合并
//            sheet.mergeCells(0, 0, inMap.get(1).size() - 1, 0);
//            //设置行高
//            sheet.setRowView(0, 700);
//
//            //第二头
//            for (int i = 0; i < inMap.get(1).size(); i++) {
//                sheet.addCell(new Label(i, 1, inMap.get(1).get(i), mapStyle.get("head")));
//                sheet.setColumnView(i, inMap.get(1).size());
//            }
//            //设置第二行行高
//            sheet.setRowView(1, 1000);
//        } catch (WriteException e) {
//            log.error("异常={}", e.getMessage(), e);
//        }
//    }
//
//    /**
//     * @author: fangl
//     * @description: 表身
//     * @date: 10:18 2019/2/28
//     */
//    public void exp_body(WritableSheet sheet, Map<String, WritableCellFormat> mapStyle, Map<Integer, Map<Integer, String>> inMap, int beginRow) {
//        try {
//            //创建单个cell
//            for (int row = beginRow; row < inMap.size(); row++) {
//                //某一行数据取出
//                Map<Integer, String> body_map = inMap.get(row);
//                //循环遍历存储
//                for (int col = 0; col < body_map.size(); col++) {
//                    sheet.addCell(new Label(col, row, body_map.get(col), mapStyle.get("body")));
//                }
//            }
//        } catch (WriteException e) {
//            log.error("异常={}", e.getMessage(), e);
//        }
//    }
//
//    /**
//     * @author: fangl
//     * @description: 样式
//     * @date: 10:26 2019/2/28
//     */
//    public Map<String, WritableCellFormat> exp_style() {
//        //样式container
//        Map<String, WritableCellFormat> mapStyle = new HashMap<>();
//        try {
//            //表头样式
//            WritableFont headFont = new WritableFont(WritableFont.TIMES, 12, WritableFont.BOLD);
//            WritableCellFormat head = new WritableCellFormat(headFont);
//            head.setAlignment(Alignment.CENTRE);
//            head.setVerticalAlignment(VerticalAlignment.CENTRE);
//            head.setBorder(Border.ALL, BorderLineStyle.THIN,
//                    jxl.format.Colour.BLACK);
//            head.setWrap(true);
//            //数据样式
//            WritableFont bodyFont = new WritableFont(WritableFont.TIMES, 11, WritableFont.NO_BOLD);
//            WritableCellFormat body = new WritableCellFormat(bodyFont);
//            body.setAlignment(Alignment.CENTRE);
//            body.setVerticalAlignment(VerticalAlignment.CENTRE);
//            body.setBorder(Border.ALL, BorderLineStyle.THIN,
//                    jxl.format.Colour.BLACK);
//            body.setWrap(true);
//            //标题样式
//            WritableFont tabTitleFont = new WritableFont(WritableFont.TIMES, 16,
//                    WritableFont.BOLD);
//            WritableCellFormat title = new WritableCellFormat(
//                    tabTitleFont);
//            title.setAlignment(Alignment.CENTRE);
//            title.setVerticalAlignment(VerticalAlignment.CENTRE);
//
//            //数据装配
//            mapStyle.put("head", head);
//            mapStyle.put("body", body);
//            mapStyle.put("title", title);
//        } catch (WriteException e) {
//            log.error("异常={}", e.getMessage(), e);
//        }
//        return mapStyle;
//    }
//
//    /**
//     * @author: fangl
//     * @description: 获取workbook对象
//     * @date: 9:05 2019/2/28
//     */
//    public WritableWorkbook exp_getWorkbook() {
//        try {
//            Workbook.createWorkbook(new File(""));
//            Workbook.createWorkbook(new File(""), new WorkbookSettings());
//            Workbook.createWorkbook(new File(""), Workbook.getWorkbook(new File("")));
//            Workbook.createWorkbook(new File(""), Workbook.getWorkbook(new File("")), new WorkbookSettings());
//            Workbook.createWorkbook(new FileOutputStream(""));
//            Workbook.createWorkbook(new FileOutputStream(""), new WorkbookSettings());
//            Workbook.createWorkbook(new FileOutputStream(""), Workbook.getWorkbook(new File("")));
//            Workbook.createWorkbook(new FileOutputStream(""), Workbook.getWorkbook(new File("")), new WorkbookSettings());
//        } catch (IOException e) {
//            log.error("异常={}", e.getMessage(), e);
//        } catch (BiffException e) {
//            log.error("异常={}", e.getMessage(), e);
//        }
//        return null;
//    }
//
//}
