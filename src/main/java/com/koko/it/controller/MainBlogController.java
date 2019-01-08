package com.koko.it.controller;

import com.koko.it.common.response.ResponseMessage;
import com.koko.it.entity.Blog;
import com.koko.it.service.BlogService;
import com.koko.it.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainBlogController {
    @Autowired
    BlogService blogService;

    @RequestMapping("/main_blog")
    public String main_blog(){
        return "main_blog";
    }

    @RequestMapping(value = "/getBlogList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage getBlogList(int page, int limit, String keyword, String date) {
        long count = blogService.count();
        List<Blog> blogList = blogService.findAll(PageRequest.of(page-1, limit)).getContent();
        return ResponseMessage.ok(count, blogList);
    }

    @RequestMapping(value = "/deleteBlog", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage deleteBlog(Long id){
        try {
            Blog blog = blogService.findById(id).get();
            if(blog != null){
                blogService.delete(blog);
                return ResponseMessage.ok(blog);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.saveErrorMsg("deleteBlog", e);
        }
        return ResponseMessage.fail("删除失败，请重试");
    }

}
