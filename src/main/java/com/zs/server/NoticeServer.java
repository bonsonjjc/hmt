package com.zs.server;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zs.comm.PageBean;
import com.zs.mapper.NoticeMapper;
import com.zs.model.Notice;
import com.zs.vo.ListQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NoticeServer {
    @Autowired
    private NoticeMapper noticeMapper;

    public PageBean list(ListQuery query) {
        Page<Object> result = PageHelper.offsetPage(query.getStart(), query.getPageSize());
        List<Notice> page = noticeMapper.list();
        PageBean pageBean = new PageBean();
        pageBean.setStart(query.getStart());
        pageBean.setPageSize(query.getPageSize());
        pageBean.setTotal(result.getTotal());
        pageBean.setDates(page);
        return pageBean;
    }

    public Notice get(String id) {
        return noticeMapper.findById(id);
    }
}
