package com.java.excel.controller;

import com.java.excel.util.ExcelUtil2;
import com.java.excel.util.User;
import com.java.excel.util.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/excel")
public class ExcelController {
    /**
     * 将user表导出到Excel表
     *
     * @return
     */
    @GetMapping(value = "/exportExcel")
    @ResponseBody
    public String exportExcel(HttpServletResponse response) {
        // 初始化数据--应该从后台数据库中查询，本地测试就写死数据
//        List<User> userList = userService.listUser();
        List<UserVo> userVOList = new ArrayList<UserVo>();
        //将 userList 拷贝到 userVOList 中
        for (int i = 0; i < 3; i++) {
            UserVo userVO = new UserVo();
            userVO.setAge(i);
            userVO.setName("张三");
            userVO.setPassWord("123");
            userVOList.add(userVO);
        }
        // 导出
        ExcelUtil2.exportExcel(response, userVOList, "用户信息", 65536, "输出.xlsx",UserVo.class);
        System.out.println("----执行完毕----------");
        return "success";
    }

    @GetMapping(value = "/importExcel")
    @ResponseBody
    public String importExcel() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("d:/demo2.xlsx");
            // 创建excel工具类，返回Excel中的数据
            // 导入
            List<UserVo> userVOList = ExcelUtil2.importExcel("用户信息", fis,UserVo.class);
            System.out.println(userVOList);
            //将userVOList 转成 userList
            List<User> userList = new ArrayList<User>();
            for (int i = 0; i < userVOList.size(); i++) {
                User user = new User();
                UserVo userVO = userVOList.get(i);
                BeanUtils.copyProperties(userVO, user);
                userList.add(user);
                System.out.println(user);
            }
            //插入数据库中
//            userService.insertUserBatch(userList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
