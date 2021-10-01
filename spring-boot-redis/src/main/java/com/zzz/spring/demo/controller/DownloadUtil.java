package com.zzz.spring.demo.controller;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 下载工具类
 */
@Slf4j
public class DownloadUtil {

    public static void downloadFile(HttpServletResponse response, File file) {
        DownloadUtil.downloadFile(response, file, "application/x-msdownload", file.getName());
    }

    /**
     * 下载文件到前台
     * @param response
     * @param file
     * @param contentType
     * @param fileName
     */
    public static void downloadFile(HttpServletResponse response, File file, String contentType, String fileName) {
        ServletOutputStream sos = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            response.setCharacterEncoding("utf-8");
            response.setContentType(contentType);
            response.setHeader("Content-Disposition", "attachment;fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");
            sos = response.getOutputStream();
            byte[] bytes = new byte[4096];
            int len = 0;
            while ((len = fis.read(bytes)) != -1) {
                sos.write(bytes, 0, len);
            }
            sos.flush();
        } catch (Exception e) {
            log.error("下载异常", e);
        } finally {
            if (sos != null) {
                try {
                    sos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
