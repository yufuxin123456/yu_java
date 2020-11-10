package com.alonginfo.duo_sheet.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alonginfo.duo_sheet.dao.DuoSheet;
import com.alonginfo.fu_za_tou.dao.FuZaHead;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yufuxin
 * @date 2020/10/15 9:47
 */
@RestController
@RequestMapping("/zhidinghead")
public class DuoSheetController {

    /**
     * 如果写到同一个sheet
    * @Author: yufuxin
    * @DateTime: 2020/10/15 10:29
    * @Params:  * @param response
    * @Return
    */
    @GetMapping("zhidingheadDownload1")
    public void download1(HttpServletResponse response) throws IOException {
        // 方法1 如果写到同一个sheet
        String fileName =  "重复多次写入(写到单个或者多个Sheet)"  + ".xlsx";
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = EasyExcel.write(fileName, DuoSheet.class).build();
        // 这里注意 如果同一个sheet只要创建一次
        WriteSheet writeSheet = EasyExcel.writerSheet("重复多次写入(写到单个或者多个Sheet)").build();

        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来
        for (int i = 0; i < 5; i++) {
            // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
            List<DuoSheet> data = new ArrayList<>();
            for(int i1 = 0 ; i1 < 10 ; i1 ++){
                DuoSheet duoSheet = new DuoSheet();
                duoSheet.setDate(new Date());
                duoSheet.setDoubleData(13.36);
                duoSheet.setString("复杂头写入"+i);
                data.add(duoSheet);
            }
//            excelWriter.write(data, writeSheet);
            EasyExcel.write(response.getOutputStream(), FuZaHead.class).sheet("sheet数据").doWrite(data);
        }



    }

    /**
     * 如果写到不同的sheet 同一个对象
     * @Author: yufuxin
     * @DateTime: 2020/10/15 10:29
     * @Params:  * @param response
     * @Return
     */
    @GetMapping("zhidingheadDownload2")
    public void download2(HttpServletResponse response) throws IOException {
        // 方法2 如果写到不同的sheet 同一个对象
        String fileName =  "重复多次写入(写到单个或者多个Sheet)"  + ".xlsx";
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = EasyExcel.write(fileName, DuoSheet.class).build();
        // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
        for (int i = 0; i < 5; i++) {
            // 每次都要创建writeSheet 这里注意必须指定sheetNo
            WriteSheet writeSheet = EasyExcel.writerSheet("重复多次写入(写到单个或者多个Sheet)").build();
            // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
            List<DuoSheet> data = new ArrayList<>();
            for(int i1 = 0 ; i1 < 10 ; i1 ++){
                DuoSheet duoSheet = new DuoSheet();
                duoSheet.setDate(new Date());
                duoSheet.setDoubleData(13.36);
                duoSheet.setString("复杂头写入"+i);
                data.add(duoSheet);
            }
            excelWriter.write(data, writeSheet);
        }
        // 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }

    @GetMapping("zhidingheadDownload3")
    public void download3(HttpServletResponse response) throws IOException {

        String fileName = TestFileUtil.getPath() + "repeatedWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = EasyExcel.write(fileName, DuoSheet.class).build();
        // 这里注意 如果同一个sheet只要创建一次
        WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
        // 方法3 如果写到不同的sheet 不同的对象
        fileName = "123"+".xlsx";
        // 这里 指定文件
        excelWriter = EasyExcel.write(fileName).build();
        // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
        for (int i = 0; i < 5; i++) {
            // 每次都要创建writeSheet 这里注意必须指定sheetNo。这里注意DemoData.class 可以每次都变，我这里为了方便 所以用的同一个class 实际上可以一直变
            writeSheet = EasyExcel.writerSheet(i, "模板").head(DuoSheet.class).build();
            // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
            List<DuoSheet> data = new ArrayList<>();
            for (int i1 = 0; i1 < 5; i1++) {
                DuoSheet duoSheet = new DuoSheet();
                duoSheet.setDate(new Date());
                duoSheet.setDoubleData(13.36);
                duoSheet.setString("复杂头写入"+i);
                data.add(duoSheet);
            }
            excelWriter.write(data, writeSheet);
        }
        // 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }
    @GetMapping("zhidingheadDownload4")
    public void noModleWrite(HttpServletResponse response) throws IOException {

        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("Excel表格名称", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(fileName).head(head()).sheet("模板").doWrite(dataList());
        EasyExcel.write(response.getOutputStream()).head(head()).sheet("sheet数据").doWrite(dataList());
    }

    private List<List<String>> head() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<String>();
        head0.add("字符串" + System.currentTimeMillis());
        List<String> head1 = new ArrayList<String>();
        head1.add("数字" + System.currentTimeMillis());
        List<String> head2 = new ArrayList<String>();
        head2.add("日期" + System.currentTimeMillis());
        list.add(head0);
        list.add(head1);
        list.add(head2);
        return list;
    }

    private List<List<Object>> dataList() {
        List<List<Object>> list = new ArrayList<List<Object>>();
        for (int i = 0; i < 10; i++) {
            List<Object> data = new ArrayList<Object>();
            data.add("字符串" + i);
            data.add(new Date());
            data.add(0.56);
            list.add(data);
        }
        return list;
    }


    public void repeatedWrite() {
        // 方法1 如果写到同一个sheet
        String fileName = TestFileUtil.getPath() + "repeatedWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = EasyExcel.write(fileName, DuoSheet.class).build();
        // 这里注意 如果同一个sheet只要创建一次
        WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
        // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来
        for (int i = 0; i < 5; i++) {
            // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
            List<DuoSheet> data = null;
            excelWriter.write(data, writeSheet);
        }
        // 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();

        // 方法2 如果写到不同的sheet 同一个对象
        fileName = TestFileUtil.getPath() + "repeatedWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 指定文件
        excelWriter = EasyExcel.write(fileName, DuoSheet.class).build();
        // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
        for (int i = 0; i < 5; i++) {
            // 每次都要创建writeSheet 这里注意必须指定sheetNo
            writeSheet = EasyExcel.writerSheet(i, "模板").build();
            // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
            List<DuoSheet> data = null;
            excelWriter.write(data, writeSheet);
        }
        // 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();

        // 方法3 如果写到不同的sheet 不同的对象
        fileName = TestFileUtil.getPath() + "repeatedWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 指定文件
        excelWriter = EasyExcel.write(fileName).build();
        // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
        for (int i = 0; i < 5; i++) {
            // 每次都要创建writeSheet 这里注意必须指定sheetNo。这里注意DemoData.class 可以每次都变，我这里为了方便 所以用的同一个class 实际上可以一直变
            writeSheet = EasyExcel.writerSheet(i, "模板").head(DuoSheet.class).build();
            // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
            List<DuoSheet> data = null;
            excelWriter.write(data, writeSheet);
        }
        // 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }

}
