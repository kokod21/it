package com.koko.it.controller;

import com.alibaba.druid.util.StringUtils;
import com.koko.it.common.response.ResponseMessage;
import com.koko.it.entity.Classify;
import com.koko.it.entity.GithubProject;
import com.koko.it.service.ClassifyService;
import com.koko.it.service.GithubProjectService;
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
public class AddGithubController {

    @Autowired
    ClassifyService classifyService;
    @Autowired
    GithubProjectService githubService;

    @RequestMapping("/add_github")
    public String add_github(HttpServletRequest request, String id){
        if(!StringUtils.isEmpty(id)){
            GithubProject gp = githubService.findById(Long.parseLong(id)).get();
            if(gp != null){
                request.setAttribute("gp", gp);
            }
        }
        List<Classify> classifies = classifyService.findAll();
        request.setAttribute("classifies", classifies);
        return "add_github";
    }

    @RequestMapping(value = "/saveGithub", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveGithub(Long id, Long classify_id, String url, String title, String author, String remark){
        System.out.println("saveGithub----------------------");
        try {
            GithubProject githubProject = new GithubProject();
            if(id != null){
                githubProject.setId(id);
            }
            githubProject.setClassify_id(classify_id);
            githubProject.setUrl(url);
            githubProject.setTitle(title);
            githubProject.setAuthor(author);
            githubProject.setRemark(remark);
            githubProject.setCreate_time(TimeUtils.todayToString(TimeUtils.TODAY_TIME));
            GithubProject gp = githubService.save(githubProject);
            if (gp != null) {
                return ResponseMessage.ok(gp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.saveErrorMsg("saveGithub", e);
        }
        return ResponseMessage.fail("保存数据错误");
    }
}
