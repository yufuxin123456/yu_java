package com.alonginfo.jian_dan.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @ExcelProperty("编号")
    private String id;
 
    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("年龄")
    private Integer age;

    @ExcelProperty("电话")
    private String telephone;

    @ExcelProperty("邮箱")
    private String email;
 
    @ExcelProperty("生日")
    private Date brithday;

}
