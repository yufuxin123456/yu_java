package com.alonginfo.jian_dan.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alonginfo.jian_dan.model.Student;


public class Student01Listener extends AnalysisEventListener<Student> {
    @Override
    public void invoke(Student student, AnalysisContext analysisContext) {
        System.out.println(student);
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("所有数据解析完成了 都会来调用");
    }
}
