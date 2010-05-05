package com.github.bjornno.kanban.controller;

import java.util.UUID;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;

@Controller
@RequestMapping("/rest")
@Transactional
public class KanbanController {
    private JdbcTemplate jdbcTemplate;

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    @ResponseBody
//    public String getAllResourceIds() {
//        List<String> allIds = jdbcTemplate.queryForList("select id from resources", String.class);
//        String result = "";
//        for (int i = 0; i < allIds.size(); i++) {
//            result += allIds.get(i) +"\n";
//        }
//        return result;
//    }

    @Required
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)    
    @Transactional
    public String postKanban(@RequestBody String text) {
        System.out.println("text from client: " + text);
        jdbcTemplate.update("update kanban set text = "+"'" + text +"' where id = 1");
        return "redirect:/kanban";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String getKanban() {
        String result = null;
        result = jdbcTemplate.queryForObject("select text from kanban where id = 1", String.class);
        if (result == null || result.length() == 0) {
            result = "ikke noe resultat";
        }
        return result;
    }
}
