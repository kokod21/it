package com.koko.it.controller;

import com.alibaba.druid.util.StringUtils;
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
@RequestMapping("/main/project")
public class MainProjectController {

    private LogUtil logUtil = new LogUtil(MainProjectController.class);

    @Autowired
    ProjectService projectService;
    @Autowired
    ClassifyService classifyService;

    @RequestMapping("/main_project")
    public String main_project(HttpServletRequest request){
        List<Classify> classifies = classifyService.findByParentIdNot(0l);
        Classify classify = new Classify();
        classify.setClassifyName("====全部====");
        classifies.add(0, classify);
        request.setAttribute("classifies", classifies);
        return "main/project/main_project";
    }

    @RequestMapping("/add_project")
    public String add_project(HttpServletRequest request, String id){
        if(!StringUtils.isEmpty(id)){
            Project gp = projectService.findById(Long.parseLong(id)).get();
            if(gp != null){
                request.setAttribute("gp", gp);
            }
        }
        List<Classify> classifies = classifyService.findByParentIdNot(0l);
        request.setAttribute("classifies", classifies);
        return "main/project/add_project";
    }

    @RequestMapping(value = "/get_project_list", method = RequestMethod.GET)
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
            logUtil.saveErrorMsg("getProjectList", e);
        }
        return ResponseMessage.fail("获取数据出错");
    }


    @RequestMapping(value = "/save_project", method = RequestMethod.POST)
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
            return ResponseMessage.ok();
        } catch (Exception e) {
            e.printStackTrace();
            logUtil.saveErrorMsg("saveProject", e);
        }
        return ResponseMessage.fail("保存数据错误");
    }

    @RequestMapping(value = "/delete_project", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage deleteProject(Long g_id){
        try {
            Project gp = projectService.findById(g_id).get();
            if(gp != null){
                projectService.delete(gp);
                return ResponseMessage.ok(gp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logUtil.saveErrorMsg("deleteProject", e);
        }
        return ResponseMessage.fail("删除失败，请重试");
    }

}
