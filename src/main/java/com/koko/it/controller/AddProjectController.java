package com.koko.it.controller;

import com.alibaba.druid.util.StringUtils;
import com.koko.it.common.response.ResponseMessage;
import com.koko.it.entity.Classify;
import com.koko.it.entity.Project;
import com.koko.it.service.ClassifyService;
import com.koko.it.service.ProjectService;
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
public class AddProjectController {

    private LogUtil logUtil = new LogUtil(AddProjectController.class);

    @Autowired
    ClassifyService classifyService;
    @Autowired
    ProjectService projectService;

    @RequestMapping("/add_project")
    public String add_project(HttpServletRequest request, String id){
        if(!StringUtils.isEmpty(id)){
            Project gp = projectService.findById(Long.parseLong(id)).get();
            if(gp != null){
                request.setAttribute("gp", gp);
            }
        }
        List<Classify> classifies = classifyService.findAll();
        request.setAttribute("classifies", classifies);
        return "add_project";
    }

    @RequestMapping(value = "/saveProject", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveProject(Project project){
        System.out.println("saveProject----------------------");
        try {
            if(project.getId() == null){
                Project gp = projectService.save(project);
                if (gp != null) {
                    return ResponseMessage.ok(gp);
                }
            } else {
                Project oldProject = projectService.findById(project.getId()).get();
                oldProject.setClassifyId(project.getClassifyId());
                oldProject.setUrl(project.getUrl());
                oldProject.setTitle(project.getTitle());
                oldProject.setAuthor(project.getAuthor());
                oldProject.setRemark(project.getRemark());
                Project gp = projectService.save(oldProject);
                if (gp != null) {
                    return ResponseMessage.ok(gp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logUtil.saveErrorMsg("saveProject", e);
        }
        return ResponseMessage.fail("保存数据错误");
    }
}
