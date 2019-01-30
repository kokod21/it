package com.koko.it.controller;

import com.alibaba.druid.util.StringUtils;
import com.koko.it.common.response.ResponseMessage;
import com.koko.it.entity.Blog;
import com.koko.it.entity.Classify;
import com.koko.it.service.BlogService;
import com.koko.it.service.ClassifyService;
import com.koko.it.utils.LogUtil;
import com.koko.it.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AddBlogController {

    @Autowired
    ClassifyService classifyService;
    @Autowired
    BlogService blogService;

    @RequestMapping("/add_blog")
    public String add_blog(HttpServletRequest request, String id){
        if(!StringUtils.isEmpty(id)){
            Blog gp = blogService.findById(Long.parseLong(id)).get();
            if(gp != null){
                request.setAttribute("gp", gp);
            }
        }
        List<Classify> classifies = classifyService.findAll();
        request.setAttribute("classifies", classifies);
        return "add_blog";
    }

    @RequestMapping(value = "/saveBlog", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveBlog(Long id, Long classify_id, String url, String title, String author, String remark){
        try {
            Blog blog;
            if(id == null){
                blog = new Blog();
                blog.setClassify_id(classify_id);
                blog.setUrl(url);
                blog.setTitle(title);
                blog.setAuthor(author);
                blog.setRemark(remark);
                blog.setCreate_time(TimeUtils.todayToString(TimeUtils.TODAY_TIME));
            } else {
                blog = blogService.findById(id).get();
                blog.setClassify_id(classify_id);
                blog.setUrl(url);
                blog.setTitle(title);
                blog.setAuthor(author);
                blog.setRemark(remark);
            }
            Blog newBlog = blogService.save(blog);
            if (newBlog != null) {
                return ResponseMessage.ok(newBlog);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.saveErrorMsg("saveBlog", e);
        }
        return ResponseMessage.fail("保存数据错误");
    }
}
