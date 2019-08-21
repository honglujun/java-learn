package com.java.excel.util;

import com.java.excel.vo.ExcelVOAttribute;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserVo {
    @ExcelVOAttribute(name = "姓名",column = "A")
    private String name;
    @ExcelVOAttribute(name = "密码",column = "B")
    private String passWord;
    @ExcelVOAttribute(name = "年龄",column = "C")
    private int age;
}
