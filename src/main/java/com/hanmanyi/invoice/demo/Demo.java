package com.hanmanyi.invoice.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hanmanyi.invoice.db.entity.Invoice;
import com.hanmanyi.invoice.service.InvoiceData;
import com.hanmanyi.invoice.service.InvoiceExtractor;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Demo {

    /**
     * 文件路径
     */
    public static String file_path = "C:\\Users\\叶立\\Desktop\\待整理发票\\";

    /**
     * 文件路径
     */
    public static String format_file_path = "format_file_path\\";

    /**
     * @param file
     * @return
     * @throws Exception
     */
    public static String getFormatFileName(File file) throws Exception {
        String fileName = file.getAbsolutePath();

        InvoiceExtractor invoiceExtractor = new InvoiceExtractor();
        InvoiceData data = invoiceExtractor.extract(fileName);

        Invoice invoice = data.getInvoice();//识别内容
        JSONObject json = (JSONObject) JSONObject.toJSON(invoice);
//		System.out.println("解析结果：");
//		System.out.println(json);

        Map dto = (Map) JSON.parse(json.toJSONString());
//		System.out.println(dto.toString());
        String formatFileName = dto.get("fpdm") + "-" + dto.get("fphm") + "-" + String.valueOf(dto.get("kprq")).
                replace("年", "").replace("月", "").replace("日", "");
//		System.out.println(formatFileName);
//		System.out.println("--------");
        return formatFileName;

    }

    public static void createNewFile(File file, String newName) throws Exception {
//        String newPath = file_path + format_file_path;
//        File newDir = new File(newPath);
//        if (!newDir.exists()) {
//            newDir.mkdir();
//        }
        File dest = new File(newName);

        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(file);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            if(input != null){
                input.close();
            }
            if(output != null){
                output.close();
            }
        }

    }


    public static void main(String[] args) {
        InvoiceExtractor invoiceExtractor = new InvoiceExtractor();
        String filename = "C:\\Users\\叶立\\Desktop\\待整理发票\\【T3出行-11.09元-1个行程】高德打车电子发票.pdf";
        try {
            File dir = new File(file_path);
            if (dir.isDirectory()) {
                File[] files = dir.listFiles();
                File newDir = new File(file_path + format_file_path);
                if (!newDir.exists()) {
                    newDir.mkdir();
                }

                System.out.println("==================");
                for (int i = 0; i < files.length; i++) {
//					String file_name = files[i].getAbsolutePath();
                    if(files[i].isDirectory()){
                        continue;
                    }

                    String formatName = getFormatFileName(files[i]);
                    System.out.println(formatName);
                    String newName = file_path + format_file_path + "\\" + formatName + ".pdf";
//                    files[i].renameTo(new File(newName)); //移动并命名
                    createNewFile(files[i], newName);   //copy 新名字


                }
                System.out.println("==================");

            }
//
//			InvoiceData data= invoiceExtractor.extract(filename);
//
//			Invoice invoice = data.getInvoice();//识别内容
//			JSONObject json=(JSONObject) JSONObject.toJSON(invoice);
//			System.out.println("解析结果：");
//			System.out.println(json);

//			Map dto = (Map) JSON.parse(json.toJSONString());
//			System.out.println(dto.toString());
//			System.out.println(dto.get("fpdm")+"-"+dto.get("fphm") + "-" +String.valueOf(dto.get("kprq")).
//					replace("年","").replace("月","").replace("日",""));
//			System.out.println("--------");

            //货物明细
//			if(data.getDetail() != null){
//				for(Object detail : data.getDetail()){
//					if(data.getType().equals("00")){
//						//普通发票
//						Detail d = (Detail)detail;
//						JSONObject obj = (JSONObject) JSONObject.toJSON(d);
////						System.out.println(obj);
////						Map dto = (Map) JSON.parse(obj.toJSONString());
////						System.out.println(dto.toString());
////						dto.size();
//
////						System.out.println(dto.get("fpdm")+"-"+dto.get("fphm"));
//					}else{
//						//通行费发票
//						Detail1 d1 = (Detail1)detail;
//						System.out.println((JSONObject) JSONObject.toJSON(d1));
//					}
//				}
//			}

            Map<String, Object> info = new HashMap<String, Object>();
            System.out.println("属性含义：");
            info.put("nsrsbh2", "纳税人识别号（销售方）");
            info.put("fh", "复核");
            info.put("nsrsbh1", "纳税人识别号（购买方）");
            info.put("title", "标题");
            info.put("type", "发票类型");
            info.put("hjje", "合计金额");
            info.put("mmq", "密码区");
            info.put("file", "文件路径");
            info.put("kprq", "开票日期");
            info.put("khhjzh1", "开户行及账号（购买方）");
            info.put("mc1", "名称（购买方）");
            info.put("khhjzh2", "开户行及账号（销售方）");
            info.put("skr", "收款人");
            info.put("mc2", "名称（销售方）");
            info.put("dzdh2", "地址、电话（销售方）");
            info.put("fpdm", "发票代码");
            info.put("hjse", "合计税额");
            info.put("jshjxx", "价税合计（小写）");
            info.put("kpr", "开票人");
            info.put("uploadTime", "解析时间");
            info.put("dzdh1", "地址、电话（购买方）");
            info.put("jshjdx", "价税合计（大写）");
            info.put("jqbh", "机器编号");
            info.put("fphm", "发票号码");
            info.put("jym", "校验码");
            System.out.println(info);
            System.out.println("解析完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
