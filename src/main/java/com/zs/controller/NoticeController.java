package com.zs.controller;

import com.zs.config.Code;
import com.zs.comm.PageBean;
import com.zs.comm.Respond;
import com.zs.model.Notice;
import com.zs.server.NoticeServer;
import com.zs.vo.ListQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeServer noticeServer;

    @ResponseBody
    @GetMapping("/list")
    public Respond list(ListQuery query) {
        PageBean list = noticeServer.list(query);
        return Respond.success(list);
    }

    @ResponseBody
    @GetMapping("/info/{id}")
    public Respond get(@PathVariable("id") String id) {
        Notice notice = noticeServer.get(id);
        if (notice == null) {
            return Respond.error(Code.NO_EXITS);
        }
        return Respond.success(notice);
    }
}
