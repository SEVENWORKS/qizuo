///*
// * Copyright (c) 2020.
// * author：qizuo
// */
//package com.qizuo.util.office;
//
//import org.apache.poi.hssf.usermodel.*;
//import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.ss.util.CellRangeAddress;
//import org.apache.poi.xssf.usermodel.*;
//import org.qizuo.cm.modules.base.pojo.BackResultPoJo;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.text.DecimalFormat;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author: fangl
// * @description: office工具类:支持如下
// * HSSF提供读写Microsoft Excel XLS格式档案的功能。
// * <p>
// * XSSF提供读写Microsoft Excel OOXML XLSX格式档案的功能。
// * <p>
// * HWPF提供读写Microsoft Word DOC格式档案的功能。
// * <p>
// * HSLF提供读写Microsoft PowerPoint格式档案的功能。
// * <p>
// * HDGF提供读Microsoft Visio格式档案的功能。
// * <p>
// * HPBF提供读Microsoft Publisher格式档案的功能。
// * <p>
// * HSMF提供读Microsoft Outlook格式档案的功能。
// * @date: 9:10 2019/2/12
// */
//public class Office_PoiUtil {
//    /**
//     * ***********************************************************
//     *
//     * @author: fangl
//     * @description: excel导入(InputStream是纯二进制数据, 只有file才是包含文件名称等内容, 由此可以看出MultipartFile和两种关系)
//     * @date: 12:00 2019/2/27
//     */
//    public BackResultPoJo imp(String fileName, InputStream inputStream, int rowBegin, int colBegin) {
//        try {
//            //方式获取一个绝对地址的流
//            /*FileInputStream fis =null;
//            fis = new FileInputStream(filePath);*/
//
//            //获取excel book容器
//            Workbook workbook = imp_endType(fileName, inputStream);
//            if (null == workbook) {
//                return new BackResultPoJo(BackResultPoJo.FAILURE, "后缀不正常");
//            }
//
//            //得到一个工作表，并获取表头、表行等信息
//            //得到单个工作表(一个workbook上面可以存在多个sheet)
//            Sheet sheet = workbook.getSheetAt(0);
//            //获得表头
//            Row rowHead = sheet.getRow(0);
//            int rowHeadNum = rowHead.getPhysicalNumberOfCells();
//            //获得数据的总行数
//            int totalRowNum = sheet.getLastRowNum();
//
//            //判断表头和行数是否合格
//            if (rowHeadNum == 0 && 0 == totalRowNum) {
//                return new BackResultPoJo(BackResultPoJo.FAILURE, "列或者行数为0");
//            }
//            //最终执行方法
//            return imp_readFinal(sheet, rowBegin, colBegin);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new BackResultPoJo(BackResultPoJo.ERROR, "异常");
//        }
//    }
//
//    /**
//     * @author: fangl
//     * @description: 根据后缀名返回一个excel对象
//     * @date: 15:44 2019/2/27
//     */
//    public Workbook imp_endType(String filename, InputStream inputStream) {
//        //判断是否为excel类型文件
//        if (!filename.endsWith(".xls") && !filename.endsWith(".xlsx")) {
//            return null;
//        }
//        //excel book容器
//        Workbook workbook = null;
//        try {
//            if (filename.endsWith(".xls")) {
//                //2003版本的excel，用.xls结尾
//                workbook = new HSSFWorkbook(inputStream);
//            } else {
//                //2007版本的excel，用.xlsx结尾
//                workbook = new XSSFWorkbook(inputStream);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return workbook;
//    }
//
//    /**
//     * 准确获取一个cell中的值
//     *
//     * @param cell 一个单元格的对象
//     * @return 返回该单元格相应的类型的值
//     */
//    public static Object imp_getRightTypeCell(Cell cell) {
//        //null
//        if (null == cell) {
//            return null;
//        }
//        //非null
//        Object object = null;
//        switch (cell.getCellType()) {
//            case Cell.CELL_TYPE_STRING: {
//                object = cell.getStringCellValue();
//                break;
//            }
//            case Cell.CELL_TYPE_NUMERIC: {
//                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
//                Double aDouble = cell.getNumericCellValue();
//                DecimalFormat format = new DecimalFormat("#");
//                object = format.format(aDouble);
//                break;
//            }
//
//            case Cell.CELL_TYPE_FORMULA: {
//                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
//                object = cell.getNumericCellValue();
//                break;
//            }
//
//            case Cell.CELL_TYPE_BLANK: {
//                cell.setCellType(Cell.CELL_TYPE_BLANK);
//                object = cell.getStringCellValue();
//
//                break;
//            }
//            default:
//                break;
//        }
//        return object;
//    }
//
//    /**
//     * @author: fangl
//     * @description: 最终执行方法
//     * @date: 16:12 2019/2/27
//     */
//    public BackResultPoJo imp_readFinal(Sheet sheet, int rowBegin, int colBegin) {
//        //获得数据的总行数
//        int totalRowNum = sheet.getLastRowNum();
//        //外层返回数据
//        Map<Integer, Map<Integer, String>> backMap = new HashMap<>();
//
//        Cell cell;
//        //遍历行
//        for (int i = rowBegin; i <= totalRowNum; i++) {
//            //内层返回数据
//            Map<Integer, String> map = new HashMap<>();
//            //获得第i行对象(从0开始，但是0行是头)
//            Row row = sheet.getRow(i);
//            //遍历例
//            for (int j = colBegin; j < row.getPhysicalNumberOfCells(); j++) {
//                //获取第i行对象中第j列对象
//                cell = row.getCell(j);
//                //数据装配
//                map.put(j, imp_getRightTypeCell(cell).toString());
//            }
//            //数据装配
//            backMap.put(i, map);
//        }
//        return new BackResultPoJo(BackResultPoJo.SUCCESS, backMap);
//    }
//
//    /**
//     * ***********************************************************
//     *
//     * @author: fangl
//     * @description: excel导出
//     * @date: 12:00 2019/2/27
//     */
//    public void exp(Workbook workbook, Map<Integer, Map<Integer, String>> inMap, int index, int beginRow, OutputStream out) {
//        /*String fileName = "Excel-" + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xls";
//        String headStr = "attachment; filename=\"" + fileName + "\"";
//        response = getResponse();
//        response.setContentType("APPLICATION/OCTET-STREAM");
//        response.setHeader("Content-Disposition", headStr);
//        OutputStream out = response.getOutputStream();*/
//        try {
//
//            /** 样式 */
//            Map<String, CellStyle> mapStyle = exp_style(workbook);
//
//            /** 创建excel开始 */
//            //创建sheet(名称、index)(可以有很多sheet)
//            Sheet sheet = workbook.createSheet(inMap.get(0).get(0));
//            //表头
//            exp_head(sheet, mapStyle, inMap);
//            //表体
//            exp_body(sheet, mapStyle, inMap, beginRow);
//            //写入和关闭
//            workbook.write(out);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * @author: fangl
//     * @description: 表格头装配
//     * @date: 11:06 2019/2/28
//     */
//    public void exp_head(Sheet sheet, Map<String, CellStyle> mapStyle, Map<Integer, Map<Integer, String>> inMap) {
//        //第一头
//        Row rowTitle = sheet.createRow(0);
//        Cell cellTiltle = rowTitle.createCell(0);
//        //合并 开始行，结束行，开始列，结束列
//        sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (inMap.get(1).size() - 1)));
//        cellTiltle.setCellValue(inMap.get(0).get(0));
//        rowTitle.setRowStyle(mapStyle.get("title"));
//
//        //第二头
//        Row rowHead = sheet.createRow(2);
//        for (int n = 0; n < inMap.get(1).size(); n++) {
//            //创建列头对应个数的单元格,并设置类型
//            Cell cellRowName = rowHead.createCell(n, Cell.CELL_TYPE_STRING);
//            //设置列头单元格的值
//            cellRowName.setCellValue(inMap.get(1).get(n));
//
//            //设置列头单元格样式
//            cellRowName.setCellStyle(mapStyle.get("head"));
//        }
//    }
//
//    /**
//     * @author: fangl
//     * @description: 表格体装配
//     * @date: 11:07 2019/2/28
//     */
//    public void exp_body(Sheet sheet, Map<String, CellStyle> mapStyle, Map<Integer, Map<Integer, String>> inMap, int beginRow) {
//        //遍历行
//        for (int i = beginRow; i < inMap.size(); i++) {
//            //创建所需的行数
//            Row row = sheet.createRow(beginRow);
//            //某一行数据取出
//            Map<Integer, String> body_map = inMap.get(i);
//            //遍历列
//            for (int j = 0; j < body_map.size(); j++) {
//                //设置单元格的数据类型
//                Cell cell = row.createCell(j, Cell.CELL_TYPE_STRING);
//                //设置单元格的值
//                cell.setCellValue(body_map.get(j));
//                //设置样式
//                cell.setCellStyle(mapStyle.get("body"));
//            }
//        }
//
//        //让列宽随着导出的列长自动适应(主要针对type为String的格子)
//        for (int colNum = 0; colNum < inMap.get(1).size(); colNum++) {
//            //获取当前列字节宽度
//            int columnWidth = sheet.getColumnWidth(colNum) / 256;
//            //遍历行
//            for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
//                //获取当前行
//                Row currentRow = sheet.getRow(rowNum);
//                if (currentRow.getCell(colNum) != null) {
//                    //获取当前格
//                    Cell currentCell = currentRow.getCell(colNum);
//                    //只有String才会进行宽度扩展
//                    if (currentCell.getCellType() == Cell.CELL_TYPE_STRING) {
//                        //当前格子中字符长度
//                        int length = currentCell.getStringCellValue().getBytes().length;
//                        //判断当前格子宽度和字符宽度是否符合
//                        if (columnWidth < length) {
//                            columnWidth = length;
//                        }
//                    }
//                }
//            }
//            //设置宽度
//            if (colNum == 0) {
//                //这个的存在主要是因为第一个大部分为序号，可以不要这个判断
//                sheet.setColumnWidth(colNum, (columnWidth - 2) * 256);
//            } else {
//                sheet.setColumnWidth(colNum, (columnWidth + 4) * 256);
//            }
//        }
//    }
//
//    /**
//     * @author: fangl
//     * @description: 样式
//     * @date: 10:26 2019/2/28
//     */
//    public Map<String, CellStyle> exp_style(Workbook workbook) {
//        //样式container
//        Map<String, CellStyle> mapStyle = new HashMap<>();
//
//        //单元格样式
//        // 设置字体
//        Font fontBody = workbook.createFont();
//        //设置字体大小
//        //font.setFontHeightInPoints((short)10);
//        //字体加粗
//        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//        //设置字体名字
//        fontBody.setFontName("Courier New");
//        //设置样式;
//        CellStyle body = workbook.createCellStyle();
//        //设置底边框;
//        body.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        //设置底边框颜色;
//        body.setBottomBorderColor(HSSFColor.BLACK.index);
//        //设置左边框;
//        body.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        //设置左边框颜色;
//        body.setLeftBorderColor(HSSFColor.BLACK.index);
//        //设置右边框;
//        body.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        //设置右边框颜色;
//        body.setRightBorderColor(HSSFColor.BLACK.index);
//        //设置顶边框;
//        body.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        //设置顶边框颜色;
//        body.setTopBorderColor(HSSFColor.BLACK.index);
//        //在样式用应用设置的字体;
//        body.setFont(fontBody);
//        //设置自动换行;
//        body.setWrapText(false);
//        //设置水平对齐的样式为居中对齐;
//        body.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        //设置垂直对齐的样式为居中对齐;
//        body.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//
//
//        //头样式
//        // 设置字体
//        Font fontTitle = workbook.createFont();
//        //设置字体大小
//        fontTitle.setFontHeightInPoints((short) 11);
//        //字体加粗
//        fontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//        //设置字体名字
//        fontTitle.setFontName("Courier New");
//        //设置样式;
//        CellStyle title = workbook.createCellStyle();
//        //设置底边框;
//        title.setBorderBottom(CellStyle.BORDER_THIN);
//        //设置底边框颜色;
//        title.setBottomBorderColor(HSSFColor.BLACK.index);
//        //设置左边框;
//        title.setBorderLeft(CellStyle.BORDER_THIN);
//        //设置左边框颜色;
//        title.setLeftBorderColor(HSSFColor.BLACK.index);
//        //设置右边框;
//        title.setBorderRight(CellStyle.BORDER_THIN);
//        //设置右边框颜色;
//        title.setRightBorderColor(HSSFColor.BLACK.index);
//        //设置顶边框;
//        title.setBorderTop(CellStyle.BORDER_THIN);
//        //设置顶边框颜色;
//        title.setTopBorderColor(HSSFColor.BLACK.index);
//        //在样式用应用设置的字体;
//        title.setFont(fontTitle);
//        //设置自动换行;
//        title.setWrapText(false);
//        //设置水平对齐的样式为居中对齐;
//        title.setAlignment(CellStyle.ALIGN_CENTER);
//        //设置垂直对齐的样式为居中对齐;
//        title.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
//
//        //数据装配
//        mapStyle.put("head", title);
//        mapStyle.put("body", body);
//        mapStyle.put("title", title);
//
//        return mapStyle;
//    }
//
//    /**
//     * @author: fangl
//     * @description: Workbook获取
//     * @date: 11:07 2019/2/28
//     */
//    public Workbook exp_getWorkbook(int type) {
//        //excel book容器
//        Workbook workbook;
//        if (type == 0) {
//            //2003版本的excel，用.xls结尾
//            workbook = new HSSFWorkbook();
//        } else {
//            //2007版本的excel，用.xlsx结尾
//            workbook = new XSSFWorkbook();
//        }
//        return workbook;
//    }
//
//
//    /** ************************************************************
//     * @author: fangl
//     * @description: word导入
//     * @date: 12:01 2019/2/27
//     */
//
//    /** ************************************************************
//     * @author: fangl
//     * @description: word导出
//     * @date: 12:01 2019/2/27
//     */
//}
