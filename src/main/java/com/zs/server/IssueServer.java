package com.zs.server;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zs.comm.PageBean;
import com.zs.mapper.IssueMapper;
import com.zs.model.Issue;
import com.zs.vo.ListQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueServer {
    @Autowired
    private IssueMapper issueMapper;

    public PageBean list(ListQuery query) {
        PageHelper.offsetPage(query.getStart(), query.getPageSize());
        List<Issue> list = issueMapper.list(query);
        PageInfo<Issue> pageInfo = new PageInfo<>(list);
        PageBean pageBean = new PageBean();
        pageBean.setStart(query.getStart());
        pageBean.setPageSize(query.getPageSize());
        pageBean.setTotal(pageInfo.getTotal());
        pageBean.setDates(list);
        return pageBean;
    }

    public Issue get(String id) {
        return issueMapper.findById(id);
    }
}
