package com.zs.server;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zs.comm.PageBean;
import com.zs.mapper.ActivityMapper;
import com.zs.model.Activity;
import com.zs.vo.ListQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServer {
    @Autowired
    private ActivityMapper activityMapper;

    public PageBean list(ListQuery query) {
        Page page = PageHelper.offsetPage(query.getStart(), query.getPageSize());
        List<Activity> list = activityMapper.list(query);
        PageBean pageBean = new PageBean();
        pageBean.setStart(query.getStart());
        pageBean.setPageSize(query.getPageSize());
        pageBean.setTotal(page.getTotal());
        pageBean.setDates(list);
        return pageBean;
    }

    public Activity get(String id) {
        return activityMapper.findById(id);
    }
}
