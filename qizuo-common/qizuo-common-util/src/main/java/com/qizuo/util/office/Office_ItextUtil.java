///*
// * Copyright (c) 2020.
// * author：qizuo
// */
//package com.qizuo.util.office;
//
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.EncodeHintType;
//import com.google.zxing.MultiFormatWriter;
//import com.google.zxing.WriterException;
//import com.google.zxing.client.j2se.MatrixToImageWriter;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
//import com.lowagie.text.*;
//import com.lowagie.text.Image;
//import com.lowagie.text.pdf.*;
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import freemarker.template.TemplateException;
//import org.xhtmlrenderer.pdf.ITextFontResolver;
//import org.xhtmlrenderer.pdf.ITextRenderer;
//import org.xhtmlrenderer.pdf.PDFEncryption;
//
//import javax.servlet.http.HttpServletResponse;
//import java.awt.*;
//import java.io.*;
//import java.net.URLEncoder;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author: fangl
// * @description: office工具类:主要针对于pdf
// * @date: 9:10 2019/2/12
// */
//@Slf4j
//public class Office_ItextUtil {
//    /**
//     * **************************************************************************************************************
//     *
//     * @author: fangl
//     * @description: itext纯java后端导出
//     * @date: 16:21 2019/2/28
//     */
//    /**
//     * 全局设置字体设置
//     */
//    public static BaseFont bfChinese;
//    public static BaseFont heiti;
//    public static Font headfont;
//    public static Font textfont12;
//    public static Font boldtextfont14;
//    public static Font undertextfont14;
//    public static Font textfont16;
//    public static Font textfont14;
//    public static Font heiti18;
//    public static Font heiti14;
//    public static Font huise10;
//    /**
//     * 全局宽度示例
//     */
//    public int maxWidth600 = 600;
//    public int maxWidth520 = 520;
//    public int maxWidth400 = 400;
//    public int maxWidth200 = 200;
//    public int maxWidth150 = 150;
//
//    /** 字体初始化 */
//    static {
//        try {
//            bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//            heiti = BaseFont.createFont("C:\\Windows\\Fonts\\SIMHEI.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//            headfont = new Font(heiti, 23, Font.NORMAL);
//            textfont12 = new Font(bfChinese, 12, Font.NORMAL);
//            undertextfont14 = new Font(bfChinese, 14, Font.NORMAL);
//            undertextfont14.setStyle("text-decoration:underline");
//            textfont14 = new Font(bfChinese, 14, Font.NORMAL);
//            heiti18 = new Font(heiti, 18, Font.NORMAL);
//            heiti14 = new Font(heiti, 14, Font.NORMAL);
//            textfont16 = new Font(bfChinese, 16, Font.NORMAL);
//            textfont16.setStyle("line-height:50px");
//            textfont14.setStyle("line-height:35px");
//            textfont14.setStyle("padding-bottom:10px");
//            boldtextfont14 = new Font(bfChinese, 14, Font.BOLD);
//            huise10 = new Font(bfChinese, 10, Font.NORMAL);
//            huise10.setColor(Color.GRAY);
//            huise10.setStyle(Font.ITALIC);
//        } catch (Exception e) {
//            log.error("异常={}", e.getMessage(), e);
//        }
//    }
//
//    /**
//     * @author: fangl
//     * @description: 主要调用方法
//     * @date: 17:53 2019/2/28
//     */
//    public static void mainPdf(Map<String, Object> dataMap, HttpServletResponse response, String name) {
//        try {
//            //返回信息
//            response.setContentType("application/x-msdownload");
//            //response.setHeader("Content-Disposition", "attachment;filename=" + name);
//            response.setHeader("Content-Disposition", "attachment;filename=" + new String(name.getBytes("gbk"), "iso-8859-1"));
//
//            //导出pdf核心方法调用
//            //1.获取两个对象
//            Document document = getDocument(null, null);
//            PdfWriter pdfWriter = getPdfWriter(document, response.getOutputStream());
//            //2.设置每页样式(事件)
//            pageView(pdfWriter);
//            //3.主要页面生成
//            document_main(null, document);
//            //结束
//            response.flushBuffer();
//        } catch (IOException e) {
//            log.error("异常={}", e.getMessage(), e);
//        } catch (Exception e) {
//            log.error("异常={}", e.getMessage(), e);
//        }
//    }
//
//    /**
//     * @author: fangl
//     * @description: 创建一个Document对象, 并赋予基本信息
//     * @date: 17:45 2019/2/28
//     */
//    public static Document getDocument(Rectangle size, Map<String, Object> map) {
//        Document document = new Document();
//        //纸张大小
//        document.setPageSize(null == size ? PageSize.A4 : size);
//        //margin
//        document.setMargins(30, 30, 30, 30);
//        //其它信息
//        document.addTitle((String) map.get("title"));
//        document.addAuthor((String) map.get("author"));
//        document.addSubject((String) map.get("subject"));
//        //文档关键字信息
//        document.addKeywords((String) map.get("keywords"));
//        //应用程序名称
//        document.addCreator((String) map.get("creator"));
//        return new Document();
//    }
//
//    /**
//     * @author: fangl
//     * @description: 主要页面生成(通过每页单独的PdfPTable)
//     * @date: 18:17 2019/2/28
//     */
//    public static void document_main(List<PdfPTable> pdfPTables, Document document) {
//        try {
//            //生成
//            for (PdfPTable pdfPTable : pdfPTables) {
//                document.add(pdfPTable);
//            }
//            //关闭
//            document.close();
//        } catch (DocumentException e) {
//            log.error("异常={}", e.getMessage(), e);
//        }
//    }
//
//    /**
//     * @param document     pdf主容器
//     * @param outputStream 返回流
//     * @author: fangl
//     * @description: 创建一个PdfWriter对象
//     * @date: 17:45 2019/2/28
//     */
//    public static PdfWriter getPdfWriter(Document document, OutputStream outputStream) {
//        PdfWriter pdfWriter = null;
//        try {
//            //根据返回流和document主体获取其对象
//            pdfWriter = PdfWriter.getInstance(document, outputStream);
//            //设定版本
//            pdfWriter.setPdfVersion(PdfWriter.PDF_VERSION_1_4);
//            //设置加密方式
//            pdfWriter.setEncryption(null, null, PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);
//        } catch (DocumentException e) {
//            log.error("异常={}", e.getMessage(), e);
//        }
//        return pdfWriter;
//    }
//
//    /**
//     * @author: fangl
//     * @description: 设定每页事件
//     * @date: 18:08 2019/2/28
//     */
//    public static void pageView(PdfWriter pdfWriter) {
//        //通过PDF页面事件模式添加页头和页脚信息功能
//        //pdfWriter.setPageEvent(new HeadFootInfoPdfPageEvent());
//        //设定水印
//        String waterImgUrl = "../../pdf/images/yulan.png";
//        //pdfWriter.setPageEvent(new CreateApplyTablePdf.PictureWaterMarkPdfPageEvent(CreateApplyTablePdf.class.getResource("/").toURI().getPath()+ waterImgUrl,needRotation));
//        //二维码
//        //document.add(createBarcode("201723123123", pdfWriter));
//    }
//
//    /**
//     * @author: fangl
//     * @description: 示例
//     * @date: 18:23 2019/2/28
//     */
//    private PdfPTable createApplyTableStep1(Map<String, Object> dataMap) {
//        float[] widthArr = new float[]{5, 3, 1};
//        PdfPTable table1 = Office_ItextUtil.createTable(3, widthArr, maxWidth200, Element.ALIGN_RIGHT);
//        table1.addCell(Office_ItextUtil.createBototmCell("", textfont14, Element.ALIGN_RIGHT, 25, 3, false));
//        table1.addCell(Office_ItextUtil.createBototmCell("", textfont14, Element.ALIGN_RIGHT, 25, 3, false));
//        table1.addCell(Office_ItextUtil.createBototmCell("地区：", textfont14, Element.ALIGN_RIGHT, 25, 1, false));
//        table1.addCell(Office_ItextUtil.createBototmCell("", textfont14, Element.ALIGN_LEFT, 25, 1, true));
//        table1.addCell(Office_ItextUtil.createBototmCell("", textfont14, Element.ALIGN_RIGHT, 25, 1, false));
//        return table1;
//    }
//
//    /**
//     * ***********************************************************************************************************************
//     *
//     * @author: fangl
//     * @description: itext常用工具类
//     * @date: 18:30 2019/2/28
//     * 1.不复杂的表格可以直接用table，复杂的用pdfptable，pdftable相当于table的上一层
//     * 2.Table的Rowspan方法可以合并行，缺点是，每行的高度是根据内容自动调整的．PdfPTable的MinimumHeight方法可以设置行的高度，但是不能合并行．
//     * /
//     * /**
//     * @author: fangl
//     * @description: 创建PDF表格
//     * @date: 21:04 2019/2/28
//     */
//    public static PdfPTable createTable(int colNumber, float[] widthArr, int width, int alignment) {
//        PdfPTable table = new PdfPTable(colNumber);
//        try {
//            if (widthArr != null && widthArr.length == colNumber) {
//                table.setWidths(widthArr);
//            }
//            table.setTotalWidth(width);
//            table.setLockedWidth(true);
//            table.setHorizontalAlignment(alignment);
//            table.getDefaultCell().setBorder(0);
//            table.setKeepTogether(true);
//        } catch (Exception e) {
//            log.error("异常={}", e.getMessage(), e);
//        }
//        return table;
//    }
//
//    /**
//     * 需要固定第rows行，同时最后一行填充整页
//     */
//    public static PdfPTable createExtendLastRowTable(int colNumber, float[] widthArr, int width, int alignment, int row) {
//        PdfPTable table = new PdfPTable(colNumber);
//        try {
//            if (widthArr != null && widthArr.length == colNumber) {
//                table.setWidths(widthArr);
//            }
//            table.setTotalWidth(width);
//            table.setLockedWidth(true);
//            table.setHorizontalAlignment(alignment);
//            table.getDefaultCell().setBorder(1);
//            table.setKeepTogether(true);
//            //表格最后一行填充到页面底部
//            table.setExtendLastRow(true);
//            //固定标题行
//            table.setHeaderRows(row);
//            table.getDefaultCell().setBorder(0);
//        } catch (Exception e) {
//            log.error("异常={}", e.getMessage(), e);
//        }
//        return table;
//    }
//
//    public static Table createBaseTable(int colNumber, float[] widthArr, float width) {
//        Table table = null;
//        try {
//            table = new Table(colNumber);
//            table.setWidth(width);
//            table.setWidths(widthArr);
//            table.setPadding(3);
//        } catch (BadElementException e) {
//            log.error("异常={}", e.getMessage(), e);
//        }
//        return table;
//    }
//
//    /*************************************创建单元格方法*******************************************/
//    /**
//     * 创建带有边框上下居中的单元格
//     */
//    public static PdfPCell createBorderCell(String value, Font font, int align, float height, int colspan) {
//        PdfPCell cell = new PdfPCell();
//        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//        cell.setHorizontalAlignment(align);
//        cell.setColspan(colspan);
//        cell.setPhrase(new Phrase(value, font));
//        cell.setPadding(0.0f);
//        cell.setMinimumHeight(height);
//        cell.setPaddingBottom(5.0f);
//        cell.setPaddingLeft(5f);
//        return cell;
//    }
//
//    /**
//     * 创建没有边框上下居中的单元格
//     */
//    public static PdfPCell createNoBorderCell(String value, Font font, int align, float height, int colspan) {
//        PdfPCell cell = new PdfPCell();
//        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//        cell.setHorizontalAlignment(align);
//        cell.setColspan(colspan);
//        cell.setPhrase(new Phrase(value, font));
//        cell.setPadding(0.0f);
//        cell.setMinimumHeight(height);
//        cell.setBorder(0);
//        return cell;
//    }
//
//    /**
//     * 靠底部对齐，主要是用于下划线
//     */
//    public static PdfPCell createBototmCell(String value, Font font, int align, float height, int colspan, boolean bottomFlag) {
//        PdfPCell cell = new PdfPCell();
//        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
//        cell.setHorizontalAlignment(align);
//        cell.setColspan(colspan);
//        cell.setPhrase(new Phrase(value, font));
//        cell.setPadding(0.0f);
//        cell.setMinimumHeight(height);
//        cell.setBorder(0);
//        cell.setPaddingBottom(5.0f);
//        cell.setPaddingLeft(5f);
//        if (bottomFlag) {
//            cell.setBorderWidthBottom(1f);
//        }
//        return cell;
//    }
//
//    /**
//     * 靠底部对齐，主要是用于下划线
//     */
//    public static PdfPCell createTopCell(String value, Font font, int align, float height, int colspan, boolean borderFlag) {
//        PdfPCell cell = new PdfPCell();
//        cell.setVerticalAlignment(Element.ALIGN_TOP);
//        cell.setHorizontalAlignment(align);
//        cell.setColspan(colspan);
//        cell.setPhrase(new Phrase(value, font));
//        cell.setPadding(0.0f);
//        cell.setMinimumHeight(height);
//        cell.setPaddingBottom(5.0f);
//        cell.setPaddingLeft(5f);
//        if (!borderFlag) {
//            cell.setBorder(0);
//        }
//        return cell;
//    }
//
//    /**
//     * 创建上下左右居中的单元格
//     */
//    public static PdfPCell createCellTitle(String value, Font font, float miniHeight, int colspan) {
//        PdfPCell cell = new PdfPCell();
//        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell.setPhrase(new Phrase(value, font));
//        cell.setMinimumHeight(miniHeight == 0 ? 120 : miniHeight);
//        cell.setBorder(0);
//        cell.setColspan(colspan);
//        return cell;
//    }
//
//    /**
//     * 配合下面这个√号一起用的
//     */
//    public static PdfPCell createBototmCell2(String value, Font font, int align, int colspan, boolean bottomFlag) {
//        PdfPCell cell = new PdfPCell();
//        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
//        cell.setHorizontalAlignment(align);
//        cell.setColspan(colspan);
//        cell.setPhrase(new Phrase(value, font));
//        cell.setPadding(0.0f);
//        //cell.setMinimumHeight(25);
//        cell.setBorder(0);
//        cell.setPaddingBottom(-1.0f);
//        cell.setPaddingTop(-5.0f);
//        cell.setPaddingLeft(5f);
//        cell.setPaddingRight(10f);
//        if (bottomFlag) {
//            cell.setBorderWidthBottom(1f);
//        }
//        return cell;
//    }
//
//    /**
//     * 给√加框子
//     */
//    public static PdfPCell createCellGou(String value, Font font) {
//        PdfPCell cell = new PdfPCell();
//        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell.setPhrase(new Phrase(value, font));
//        cell.setPadding(0.0f);
//        cell.setPaddingLeft(-4f);
//        cell.setMinimumHeight(0);
//        cell.setFixedHeight(8f);
//        cell.setPaddingTop(-20.0f);
//        return cell;
//    }
//
//    public static PdfPCell createTableCell(PdfPTable table) {
//        PdfPCell cell = new PdfPCell();
//        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell.setPadding(0.0f);
//        cell.setMinimumHeight(0);
//        cell.setBorder(0);
//        cell.addElement(table);
//        return cell;
//    }
//
//    public static Cell createBaseCell(String value, Font font, int align, int rowspan, int colspan) throws Exception {
//        Cell cell = new Cell(new Phrase(value, font));
//        cell.setRowspan(rowspan);
//        cell.setColspan(colspan);
//        cell.setVerticalAlignment(Table.ALIGN_MIDDLE);
//        cell.setHorizontalAlignment(align);
//        cell.setLeading(10);
//        cell.setUseAscender(true);
//        //cell.setUseDescender(true);
//        return cell;
//    }
//
//    public static Cell createNoBorderBaseCell(String value, Font font, int align, int rowspan, int colspan) throws Exception {
//        Cell cell = new Cell(new Phrase(value, font));
//        cell.setRowspan(rowspan);
//        cell.setColspan(colspan);
//        cell.setVerticalAlignment(Table.ALIGN_MIDDLE);
//        cell.setHorizontalAlignment(align);
//        cell.setUseAscender(true);
//        cell.setUseDescender(true);
//        cell.setBorder(0);
//        return cell;
//    }
//
//    public static Image createBarcode(String value, PdfWriter pdfWriter, float scaleValue) throws DocumentException {
//
//        Barcode128 code128 = new Barcode128();
//        code128.setCode(value.trim());
//        code128.setCodeType(Barcode128.CODE128);
//        Image code128Image = code128.createImageWithBarcode(pdfWriter.getDirectContent(), null, null);
//        code128Image.setAbsolutePosition(470, 780);
//        code128Image.scalePercent(scaleValue);
//        code128Image.setWidthPercentage(0.50f);
//        return code128Image;
//
//    }
//
//    /**
//     * 根据传入的内容生成二维码
//     *
//     * @param content
//     * @return
//     * @author chenem
//     */
//    public static Image createQRCode(String content, float scaleValue, float var1, float var2) {
//        int width = 100;
//        int height = 100;
//        //定义二维码的参数
//        HashMap map = new HashMap();
//        //设置编码
//        map.put(EncodeHintType.CHARACTER_SET, "utf-8");
//        //设置纠错等级
//        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
//        map.put(EncodeHintType.MARGIN, 2);
//        Image image = null;
//        try {
//            //生成二维码
//            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height);
//            //将BufferedImage转化为com.lowagie.text.Image
//            image = com.lowagie.text.Image.getInstance(MatrixToImageWriter.toBufferedImage(bitMatrix), null);
//        } catch (WriterException e) {
//            log.error("异常={}", e.getMessage(), e);
//        } catch (IOException e) {
//            log.error("异常={}", e.getMessage(), e);
//        } catch (BadElementException e) {
//            log.error("异常={}", e.getMessage(), e);
//        }
//        //设置图片在pdf的位置
//        image.setAbsolutePosition(var1, var2);
//        image.scalePercent(scaleValue);
//        image.setWidthPercentage(0.50f);
//        return image;
//    }
//
//    public static Image getWaterMarkImage(Image waterMarkImage, float xPosition, float yPosition, boolean needRotation) {
//        //坐标
//        waterMarkImage.setAbsolutePosition(xPosition, yPosition);
//        if (needRotation) {
//            //旋转 弧度
//            waterMarkImage.setRotation(-40);
//            //旋转 角度
//            waterMarkImage.setRotationDegrees(30);
//        }
//        //依照比例缩放
//        waterMarkImage.scalePercent(100);
//        return waterMarkImage;
//    }
//
//    /**
//     * 靠底部对齐，主要是用于下划线
//     */
//    public static PdfPCell createText(String value, Font font, int valign, int align, float height) {
//        PdfPCell cell = new PdfPCell();
//        cell.setVerticalAlignment(valign);
//        cell.setHorizontalAlignment(align);
//        cell.setPhrase(new Phrase(value, font));
//        cell.setPadding(0.0f);
//        //cell.setMinimumHeight(height);
//        cell.setBorder(0);
//        cell.setPaddingLeft(15.0f);
//        cell.setPaddingRight(15.0f);
//        cell.setPaddingBottom(5.0f);
//        cell.setPaddingTop(5.0f);
//        //行间距、第一个是间距值，第二个是字体大小倍数
//        cell.setLeading(10.5f, 1f);
//        return cell;
//    }
//
//
//    /** ****************************************************************************************************************************
//     * @author: fangl
//     * @description: itext html导出
//     * @date: 16:22 2019/2/28
//     */
//
//    /** ****************************************************************************************************************************
//     * @author: fangl
//     * @description: freemarker结合flying-saucer-pdf
//     * @date: 16:22 2019/2/28
//     */
//
//    /**
//     * 基本路径
//     */
//    private static String templateBaseDir = "";
//
//    /**
//     * @author: fangl
//     * @description 传入freemarker模板流并有flying进行解析
//     * @date: 16:59 2019/2/28
//     */
//    public static void makePdf(StringBuffer sb, OutputStream outputStream, String filename) {
//        OutputStream os = null;
//        try {
//            //获取渲染对象
//            ITextRenderer renderer = new ITextRenderer();
//
//            //加密对象
//            PDFEncryption pdfencryption = new PDFEncryption();
//            pdfencryption.setUserPassword(null);
//            pdfencryption.setOwnerPassword(null);
//
//            //额外字体添加
//            ITextFontResolver fontResolver = renderer.getFontResolver();
//            fontResolver.addFont(templateBaseDir + "fonts/msyh.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//            fontResolver.addFont(templateBaseDir + "fonts/SIMKAI.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//            fontResolver.addFont(templateBaseDir + "fonts/simsun.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//            fontResolver.addFont(templateBaseDir + "fonts/STXingkai.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//            fontResolver.addFont(templateBaseDir + "fonts/SIMFANG.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//
//            //解决图片的相对路径问题(就是ftl文件中存在的路径)
//            String baseUrl = new File(templateBaseDir + "images/").toURI().toURL().toString();
//            //某些版本需要这样处理图片相对路径
//            //renderer.getSharedContext().setBaseURL(basePath);
//
//            //进行渲染
//            renderer.setDocumentFromString(sb.toString(), baseUrl);
//            renderer.layout();
//
//            //结果输出
//            if (outputStream != null) {
//                //根据已经存在地址进行输出
//                renderer.createPDF(outputStream, true);
//            } else {
//                //固定地址输出
//                String outputFile = templateBaseDir + filename + ".pdf";
//                os = new FileOutputStream(outputFile);
//                renderer.createPDF(os, true);
//            }
//
//            //添加水印
//            // buildPDFWater(outputFile, "ZJT");
//        } catch (Exception e) {
//            log.error("异常={}", e.getMessage(), e);
//        } finally {
//            if (os != null) {
//                try {
//                    os.close();
//                    os = null;
//                } catch (IOException e) {
//                    log.error("异常={}", e.getMessage(), e);
//                }
//            }
//        }
//    }
//
//    /**
//     * @author: fangl
//     * @description: 主调用方法(数据, 模板, 文件名称)
//     * @date: 16:59 2019/2/28
//     */
//    public static void makePdfInMem(HttpServletResponse response, Map<String, Object> dataMap, String templateFile, String filename) {
//        try {
//            //获取基本路径
//            templateBaseDir = Office_ItextUtil.class.getResource("").toURI().toString();
//            //注释掉的是因为jar包读取不到文件，如果读取不到文件就需要这样处理
//            /*if (-1 != templateBaseDir.indexOf("!")) {
//                templateBaseDir = templateBaseDir.substring(templateBaseDir.indexOf("/"), templateBaseDir.indexOf("!"));
//                templateBaseDir = templateBaseDir.substring(0, templateBaseDir.lastIndexOf("/") + 1) + "utils/";
//            } else {
//                templateBaseDir = Office_ItextUtil.class.getClassLoader().getResource("utils/").toURI().getPath();
//            }*/
//
//            //freemarker读取ftl模板
//            DocumentHandler dh = new Office_ItextUtil().new DocumentHandler(templateBaseDir + "ftl/");
//            StringBuffer sb = dh.createDocString(dataMap, templateFile);
//
//            //将freemarker读取流去生成pdf文件
//            Office_ItextUtil.makePdf(sb, response.getOutputStream(), filename);
//
//            //返回信息处理
//            response.setContentType("application/pdf");
//            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
//            response.flushBuffer();
//        } catch (Exception e) {
//            log.error("异常={}", e.getMessage(), e);
//        }
//    }
//
//    /**
//     * @author: fangl
//     * @description: freemarker拦截器类(最终转换的时候过滤)
//     * @date: 16:56 2019/2/28
//     */
//    public class DocumentHandler {
//        /**
//         * 它存储了常用(全局，应用程序级)的设置，定义了想要在所有模板中可用的变量(称为共享变量)。 而且，它会处理 Template 实例的新建和缓存
//         */
//        private Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
//        /**
//         * 编码
//         */
//        private String Encoding = "UTF-8";
//
//        public DocumentHandler() {
//            //输出编码
//            configuration.setOutputEncoding(Encoding);
//        }
//
//        public DocumentHandler(String baseDir) {
//            try {
//                //输出编码
//                configuration.setOutputEncoding(Encoding);
//                //设置基本路径(最终寻找文件的时候会到这个基本路径下进行寻找)
//                configuration.setDirectoryForTemplateLoading(new File(baseDir));
//                //configuration.setClassForTemplateLoading(this.getClass(), "/com/havenliu/document/template");
//            } catch (Exception e) {
//                log.error("异常={}", e.getMessage(), e);
//            }
//        }
//
//        /**
//         * @param dataMap  数据
//         * @param tempPath ftl文件名称
//         * @author: fangl
//         * @description: 将数据和flt模板进行结合，并返回字节流
//         * @date: 17:23 2019/2/28
//         */
//        public StringBuffer createDocString(Object dataMap, String tempPath) {
//            //最终的输出对象
//            StringWriter writerA = new StringWriter();
//            try {
//                //获取转变成流的模板对象(这里的tempPath只是传过来的文件名称，因为前面已经设定过文件前缀了)
//                Template t = configuration.getTemplate(tempPath, Encoding);
//                //结合数据对模板进行转换，并由writerA存储
//                t.process(dataMap, writerA);
//                //返回一个buffer
//                return writerA.getBuffer();
//            } catch (Throwable ex) {
//                return null;
//            } finally {
//                try {
//                    writerA.close();
//                } catch (IOException e) {
//                    log.error("异常={}", e.getMessage(), e);
//                }
//            }
//        }
//
//        /**
//         * @param dataMap  数据
//         * @param tempPath ftl文件名称
//         * @param url      最终输出文档路径
//         * @author: fangl
//         * @description: 直接输出到某个文件中(url是输出地址)
//         * @date: 17:31 2019/2/28
//         */
//        public void createDoc(String url, Object dataMap, String tempPath) {
//            try {
//                //获取模板对象
//                Template t = configuration.getTemplate(tempPath, Encoding);
//                //输出对象装配
//                File outFile = new File(url);
//                Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
//                //最终输出
//                t.process(dataMap, out);
//            } catch (TemplateException e) {
//                log.error("异常={}", e.getMessage(), e);
//            } catch (IOException e) {
//                log.error("异常={}", e.getMessage(), e);
//            }
//        }
//
//    }
//}
