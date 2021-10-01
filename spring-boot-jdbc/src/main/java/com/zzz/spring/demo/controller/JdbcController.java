package com.zzz.spring.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * TODO
 *
 * @author zhangzhizhong
 */
@RestController
@RequestMapping("jdbc")
public class JdbcController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("queryForList")
    public Object queryForList(String sql) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.queryForList(sql);
    }
}
