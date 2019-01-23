package com.koko.it.controller;

import com.koko.it.common.response.ResponseMessage;
import com.koko.it.entity.GithubProject;
import com.koko.it.service.GithubProjectService;
import com.koko.it.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainGithubController {

    @Autowired
    GithubProjectService githubProjectService;

    @RequestMapping("/main_github")
    public String main_github(){
        return "main_github";
    }

    @RequestMapping(value = "/getGithubList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage getGithubList(int page, int limit, String keyword, String date) {
        long count = githubProjectService.count();
        List<GithubProject> blogList = githubProjectService.findAll(
                PageRequest.of(page-1, limit, Sort.by(Sort.Order.desc("id")))).getContent();
        return ResponseMessage.ok(count,blogList);
    }

    @RequestMapping(value = "/deleteGithub", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage deleteGithub(Long g_id){
        try {
            GithubProject gp = githubProjectService.findById(g_id).get();
            if(gp != null){
                githubProjectService.delete(gp);
                return ResponseMessage.ok(gp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.saveErrorMsg("deleteGithub", e);
        }
        return ResponseMessage.fail("删除失败，请重试");
    }

}
