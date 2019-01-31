package com.koko.it.controller;

import com.koko.it.common.response.ResponseMessage;
import com.koko.it.entity.Classify;
import com.koko.it.entity.Project;
import com.koko.it.service.ClassifyService;
import com.koko.it.service.ProjectService;
import com.koko.it.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainProjectController {

    @Autowired
    ProjectService projectService;
    @Autowired
    ClassifyService classifyService;

    @RequestMapping("/main_project")
    public String main_project(HttpServletRequest request){
        List<Classify> classifies = classifyService.findAll();
        Classify classify = new Classify();
        classify.setClassifyName("====全部====");
        classifies.add(0, classify);
        request.setAttribute("classifies", classifies);
        return "main_project";
    }

    @RequestMapping(value = "/getProjectList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage getProjectList(int page, int limit, Long classify_id) {
        List<Project> projectList;
        List<Project> projectCount;
        System.out.println("classify_id============="+classify_id);
        try {
            System.out.println("classify_id:"+classify_id);
            if(classify_id == null){
                projectList = projectService.findAll(
                        PageRequest.of(page-1, limit, Sort.by(Sort.Order.desc("id")))).getContent();
                projectCount = projectService.findAll();
            } else {
                projectList = projectService.findAllByClassifyId(classify_id,
                        PageRequest.of(page-1, limit, Sort.by(Sort.Order.desc("id"))));
                projectCount = projectService.findAllByClassifyId(classify_id,
                        PageRequest.of(0, 10000));
            }
            if(projectCount != null){
                return ResponseMessage.ok(projectCount.size(),projectList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.saveErrorMsg("getProjectList", e);
        }
        return ResponseMessage.fail("获取数据出错");
    }

    @RequestMapping(value = "/deleteGithub", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage deleteGithub(Long g_id){
        try {
            Project gp = projectService.findById(g_id).get();
            if(gp != null){
                projectService.delete(gp);
                return ResponseMessage.ok(gp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.saveErrorMsg("deleteGithub", e);
        }
        return ResponseMessage.fail("删除失败，请重试");
    }

}
