package com.zs.controller;

import com.zs.config.Code;
import com.zs.comm.PageBean;
import com.zs.model.Activity;
import com.zs.vo.ListQuery;
import com.zs.comm.Respond;
import com.zs.server.ActivityServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ActivityServer activityService;

    @ResponseBody
    @GetMapping("/list")
    public Respond list(ListQuery query) {
        PageBean list = activityService.list(query);
        return Respond.success(list);
    }

    @ResponseBody
    @GetMapping("/info/{id}")
    public Respond get(@PathVariable("id") String id) {
        Activity activity = activityService.get(id);
        if (activity == null) {
            return Respond.error(Code.NO_EXITS);
        }
        return Respond.success(activity);
    }
}
