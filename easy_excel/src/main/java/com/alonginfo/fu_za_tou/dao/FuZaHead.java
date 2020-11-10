package com.alonginfo.fu_za_tou.dao;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author yufuxin
 * @date 2020/10/15 9:48
 */
@Data
public class FuZaHead {
        @ExcelProperty({"主标题", "字符串标题"})
        private String string;
        @ExcelProperty({"主标题", "日期标题"})
        private Date date;
        @ExcelProperty({"主标题", "数字标题"})
        private Double doubleData;
}
