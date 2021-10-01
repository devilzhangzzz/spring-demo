package com.zzz.spring.demo.config;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.Data;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

/**
 * freemarker   DOC文档导出视图类
 *
 * @version : 2021年7月1日
 */
@Data
public class FreeMarkView extends AbstractView {
    final String DOCTYPE = "doc";
    final String XLSXTYPE = "xls";

    /**
     * 模板名称
     */
    String templateName;

    /**
     * 文件名
     */
    String fileName;

    /**
     * 文件后缀
     */
    String filePostfix;

    /**
     * true:导出导入模板,false:导出正常模板
     */
    boolean isImport;

    public FreeMarkView(String templateName, String fileName, String filePostfix) {
        this.templateName = templateName;
        this.fileName = fileName;
        this.filePostfix = filePostfix;
        this.isImport = false;
    }


    /**
     * 构造函数
     *
     * @param templateName 模板名称
     * @param fileName     文件名
     * @param isImport     true:导出导入模板,false:导出正常模板
     */
    public FreeMarkView(String templateName, String fileName, String filePostfix, Boolean isImport) {
        this.templateName = templateName;
        this.fileName = fileName;
        this.filePostfix = filePostfix;
        this.isImport = isImport;
    }

    /**
     * 导出文件处理
     *
     * @param model    数据
     * @param request  请求
     * @param response 返回值
     * @throws Exception
     */
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        // 获取模板文件的文件流
        String templateName = this.templateName;
        //文件类型
        String fileType = this.filePostfix;
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
        //解决空值问题
        configuration.setClassicCompatible(true);
        // 模板存放路径
        configuration.setClassForTemplateLoading(this.getClass(), "/static/exportTemplate");
        // 获取模板文件
        Template template = configuration.getTemplate(templateName);
        // 设置返回结果
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + URLEncoder.encode(this.fileName + "." +this.filePostfix, "UTF-8"));
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);

        response.setContentType(getContentTypeByPostfix(fileType));
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();

        // 导出文件
        if (ObjectUtil.isNotEmpty(template)) {
            try {
                template.process(model, writer);
                writer.flush();
            } catch (TemplateException e) {
                e.printStackTrace();
            } finally {
                writer.close();
            }
        }
    }

    private String getContentTypeByPostfix(String postfix) {
        String contentType = null;
        if (StrUtil.isNotBlank(postfix)) {
            switch (postfix) {
                case DOCTYPE:
                    contentType = "application/msword;charset=utf-8";
                    break;
                case XLSXTYPE:
                    contentType = "application/vnd.ms-excel;charset=utf-8";
                    break;
                default:
                    contentType = "text/html;charset=utf-8";
                    break;
            }
        }
        return contentType;
    }
}