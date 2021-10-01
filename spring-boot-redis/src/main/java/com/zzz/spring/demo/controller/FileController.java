package com.zzz.spring.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * TODO
 *
 * @author zhangzhizhong
 */
@RestController
@RequestMapping("file")
public class FileController {


    @GetMapping("download1")
    public void download1(HttpServletResponse response) {
        DownloadUtil.downloadFile(response, new File("F:\\项目\\花都网格\\网格数据清洗\\三人小组提交数据0830.gdb(1)\\docker postgre启动命令.txt"),
                "application/x-msdownload",
                "启动命令.txt");
    }

    @PostMapping("download2")
    public void download2(HttpServletResponse response) {
        DownloadUtil.downloadFile(response, new File("F:\\项目\\花都网格\\网格数据清洗\\三人小组提交数据0830.gdb(1)\\docker postgre启动命令.txt"),
                "application/x-msdownload",
                "启动命令.txt");
    }


    @RequestMapping("download3")
    public void download3(HttpServletResponse response) throws UnsupportedEncodingException {
        String fileName = "工作人员导入结果.txt";
        ServletOutputStream sos = null;
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment;fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");
            sos = response.getOutputStream();
            sos.write("我是张治忠".getBytes(StandardCharsets.UTF_8));
            sos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sos != null) {
                try {
                    sos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
