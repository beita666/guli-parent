package com.atguigu.eduservice.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestExcel {

    public static void main(String[] args) {

        String fileName = "G:\\write.xlsx";

       // EasyExcel.write(fileName,DemoDate.class).sheet("学生列表").doWrite(getList());


        EasyExcel.read(fileName,DemoDate.class,new ExcelListener()).sheet().doRead();
    }


    private static List<DemoDate> getList(){

        List<DemoDate> list = new ArrayList<>();

        for (int i=0; i<10; i++){
            DemoDate demoDate =new DemoDate();
            demoDate.setSno(i);
            demoDate.setSname("xiaoming"+i);
            list.add(demoDate);
        }

        return list;
    }
}
