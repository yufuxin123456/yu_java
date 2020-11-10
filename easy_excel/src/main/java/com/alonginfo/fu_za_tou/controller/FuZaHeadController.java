package com.alonginfo.fu_za_tou.controller;

import com.alibaba.excel.EasyExcel;
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
public class FuZaHeadController {

    @GetMapping("zhidingheadDownload")
    public void download(HttpServletResponse response) throws IOException {
        List<FuZaHead> list = new ArrayList<FuZaHead>();
        for(int i = 0 ; i < 10 ; i ++){
            FuZaHead zhiDingHead = new FuZaHead();
            zhiDingHead.setDate(new Date());
            zhiDingHead.setDoubleData(13.36);
            zhiDingHead.setString("复杂头写入"+i);
            list.add(zhiDingHead);
        }

        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("复杂头写入", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), FuZaHead.class).sheet("sheet数据").doWrite(list);
    }
}
