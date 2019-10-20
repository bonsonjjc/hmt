package com.zs.controller;

import com.zs.config.Code;
import com.zs.comm.PageBean;
import com.zs.comm.Respond;
import com.zs.model.Issue;
import com.zs.server.IssueServer;
import com.zs.vo.ListQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/issue")
public class IssueController {
    @Autowired
    private IssueServer issueServer;

    @ResponseBody
    @GetMapping("/list")
    public Respond list(ListQuery query) {
        PageBean list = issueServer.list(query);
        return Respond.success(list);
    }

    @ResponseBody
    @GetMapping("/info/{id}")
    public Respond get(@PathVariable("id") String id) {
        Issue notice = issueServer.get(id);
        if (notice == null) {
            return Respond.error(Code.NO_EXITS);
        }
        return Respond.success(notice);
    }
}
