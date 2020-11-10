package com.alonginfo.jian_dan.controller;

import com.alibaba.excel.EasyExcel;import com.alonginfo.jian_dan.listener.Student01Listener;
import com.alonginfo.jian_dan.model.Student;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("excel")
public class EsayController {

    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        List<Student> list = new ArrayList<Student>();
        for(int i = 0 ; i < 10 ; i ++){
            Student student = new Student();
            student.setId("stu" + i);
            student.setName("wang" + i);
            student.setAge( 18 + i );
            student.setTelephone("1361234" + i);
            student.setEmail("wang" + i + "@czxy.com");
            student.setBrithday(new Date());
            list.add(student);
        }

        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("Excel表格名称", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), Student.class).sheet("sheet数据").doWrite(list);
    }

    /**
     * 文件上传
     * <p>1. 创建excel对应的实体对象 参照{@link Student}
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link}
     * <p>3. 直接读即可
     */
    @PostMapping("upload")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), Student.class, new Student01Listener()).sheet().doRead();
        return "success";
    }

}
