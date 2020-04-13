///*
// * Copyright (c) 2020.
// * author：qizuo
// */
//package com.qizuo.util.http;
//
//
//import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeBodyPart;
//import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeMultipart;
//import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;
//
//import javax.activation.DataHandler;
//import javax.activation.FileDataSource;
//import javax.mail.*;
//import javax.mail.internet.*;
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//
///**
// * @author: fangl
// * @description: 邮件类
// * @date: 9:10 2019/2/12
// */
//public class EmailUtil {
//    /**
//     * 邮箱后缀
//     */
//    public static final String EMAILSUFFIX = "qq";
//    /**
//     * 邮箱用户名
//     */
//    public static final String EMAILUSERNAME = "2837260454@qq.com";
//    /**
//     * 邮箱密码
//     */
//    public static final String EMAILUSERPASSWORD = "543858438";
//    /**
//     * 是否开启debug模式
//     */
//    public static final boolean EMAILDEBUG = true;
//
//    /**
//     * @author: fangl
//     * @description: 发送邮件
//     * @date: 9:06 2019/2/27
//     */
//    public boolean sendEamil(String title, String content, List<MimeBodyPart> mimeBodyParts, Address[] addresses, List<MimeBodyPart> images) {
//        //发送邮箱服务器关联信息
//        Properties prop = new Properties();
//        prop.setProperty("mail.host", "smtp." + EMAILSUFFIX + ".com");
//        prop.setProperty("mail.transport.protocol", "smtp");
//        prop.setProperty("mail.smtp.auth", "true");
//        //使用JavaMail发送邮件的5个步骤
//        //1、创建session(一个发送容器)
//        Session session = Session.getInstance(prop);
//        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
//        session.setDebug(EMAILDEBUG);
//        //2、通过session得到transport对象(容器中的发送控制对象)
//        Transport ts = null;
//        try {
//            ts = session.getTransport();
//            //3、连上邮件服务器(发送邮箱登录名和密码)
//            ts.connect("smtp." + EMAILSUFFIX + ".com", EMAILUSERNAME, EMAILUSERPASSWORD);
//            //4、创建邮件
//            Message message = createMixedMail(session, title, content, mimeBodyParts, addresses, images);
//            //5、发送邮件
//            if (null != message) {
//                ts.sendMessage(message, message.getAllRecipients());
//            } else {
//                return false;
//            }
//            ts.close();
//        } catch (Exception e) {
//            try {
//                ts.close();
//            } catch (MessagingException e1) {
//
//            }
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * @author: fangl
//     * @description: 邮件装配
//     * @date: 9:12 2019/2/27
//     */
//    public MimeMessage createMixedMail(Session session, String title, String content, List<MimeBodyPart> mimeBodyParts, Address[] addresses, List<MimeBodyPart> images) throws Exception {
//        //通过session创建一封基本邮件
//        MimeMessage message = new MimeMessage(session);
//
//        //指定邮件的发件人
//        message.setFrom(new InternetAddress(EMAILUSERNAME));
//
//        //发送地址list装配，这里可以用String
//        message.setRecipients(Message.RecipientType.TO, addresses);
//
//        //邮件标题
//        message.setSubject(title);
//
//        //MimeBodyPart的容器,存在附件的邮件必须用mixed
//        MimeMultipart multipartContainer = new MimeMultipart();
//        multipartContainer.setSubType("mixed");
//
//        //正文
//        MimeBodyPart text = new MimeBodyPart();
//        //正文内容
//        text.setContent(content, "text/html;charset=UTF-8");
//        //代表正文的bodypart塞入到容器中
//        multipartContainer.addBodyPart(text);
//
//        //代表附件的bodypart塞入到容器中
//        if (null != mimeBodyParts && mimeBodyParts.size() > 0) {
//            for (MimeBodyPart mimeBodyPart : mimeBodyParts) {
//                multipartContainer.addBodyPart(mimeBodyPart);
//            }
//        }
//
//        //图片：方式一：正文中有内嵌图片(这种格式必须有related)
//        /*MimeMultipart images = new MimeMultipart();
//        if (null != images && images.size() > 0) {
//            for (MimeBodyPart mimeBodyPart : mimeBodyParts) {
//                images.addBodyPart(mimeBodyPart);
//            }
//        }
//        images.setSubType("related");
//        MimeBodyPart contents = new MimeBodyPart();
//        contents.setContent(images);
//        //代表图片的bodypart塞入到容器中
//        multipartContainer.addBodyPart(contents);*/
//        //图片：方式二：直接塞入multipartContainer
//        if (null != images && images.size() > 0) {
//            for (MimeBodyPart mimeBodyPart : mimeBodyParts) {
//                multipartContainer.addBodyPart(mimeBodyPart);
//            }
//        }
//
//        //将上述附件添加到message内容中的
//        message.setContent(multipartContainer);
//        message.saveChanges();
//
//        //本地保存一下发送邮件主体(备用)
//        //message.writeTo(new FileOutputStream("E:\\MixedMail.eml"));
//
//        //返回创建好的的邮件
//        return message;
//    }
//
//    /**
//     * @author: fangl
//     * @description: 发送地址装配成list
//     * @date: 9:40 2019/2/27
//     */
//    public static Address[] addresses(List<String> sts) {
//        //发送地址装配
//        Address[] addresses = null;
//        try {
//            if (null != sts) {
//                addresses = new Address[sts.size()];
//                for (int i = 0; i < sts.size(); i++) {
//                    //邮件地址
//                    Address address = new InternetAddress(sts.get(i));
//                    //放入到返回容器中
//                    addresses[i] = address;
//                }
//            }
//        } catch (AddressException e) {
//            e.printStackTrace();
//        }
//        return addresses;
//    }
//
//    /**
//     * @author: fangl
//     * @description: 附件地址装配成list
//     * @date: 9:40 2019/2/27
//     */
//    public static List<MimeBodyPart> mimeBodyParts(List<IOPoJo> ioPoJos) {
//        List<MimeBodyPart> mimeBodyParts = new ArrayList<>();
//        try {
//            if (null != ioPoJos) {
//                for (IOPoJo ioPoJo : ioPoJos) {
//                    MimeBodyPart attach = new MimeBodyPart();
//                    //附件路径
//                    DataHandler dh = new DataHandler(new FileDataSource(ioPoJo.getValue()));
//                    attach.setDataHandler(dh);
//                    //附件名称
//                    attach.setFileName(MimeUtility.encodeText(ioPoJo.getKey()));
//                    //放入返回容器中
//                    mimeBodyParts.add(attach);
//                }
//            }
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return mimeBodyParts;
//    }
//
//    /**
//     * @author: fangl
//     * @description: 内嵌图片配成list
//     * @date: 9:40 2019/2/27
//     */
//    public static List<MimeBodyPart> mimeBodyPartsForImage(List<IOPoJo> ioPoJos) {
//        List<MimeBodyPart> mimeBodyParts = new ArrayList<>();
//        try {
//            if (null != ioPoJos) {
//                for (IOPoJo ioPoJo : ioPoJos) {
//                    MimeBodyPart attach = new MimeBodyPart();
//                    //图片路径
//                    DataHandler dh = new DataHandler(new FileDataSource(ioPoJo.getValue()));
//                    attach.setDataHandler(dh);
//                    //正文内容将引用这个id
//                    attach.setContentID(ioPoJo.getKey());
//                    //放入返回容器中
//                    mimeBodyParts.add(attach);
//                }
//            }
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//        return mimeBodyParts;
//    }
//
//}
