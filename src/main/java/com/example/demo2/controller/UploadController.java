package com.example.demo2.controller;

import com.example.demo2.controller.upload.UploadProperties;
import com.example.demo2.domian.User;
import com.example.demo2.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @author Rainmean.Li
 * @create 2019-06-14 17:35
 **/
@Controller
public class UploadController {

    private Logger log = LoggerFactory.getLogger(UploadController.class);
    @Resource
    private ResourceLoader resourceLoader;
    @Resource
    private UploadProperties uploadProperties;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/upload")
    public String upload(){
        return "upload";
    }

    /**
     * @Title: upload
     * @Description: TODO(上传)
     * @param request
     * @return String    返回类型
     */
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile[] files, HttpServletRequest request, HttpSession session, Model model) {
        String id = request.getParameter("id");
        // 获取文件存放路径
        String basePath = uploadProperties.getBasePath();
        // 判断文件夹是否存在，不存在则
        File folder = new File(basePath);

        if(files.length==0){
            model.addAttribute("error","没有上传图片！");
            return "upload";
        }

        for(MultipartFile file : files) {
            try {
                //获取文件的后缀名
                String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));;
                //新文件名
                String filename = UUID.randomUUID().toString() + suffix;
                //新文件对象
                File saveFile = new File(folder + "/" + filename);
                //保存文件
                file.transferTo(saveFile);
                User user = new User();
                user.setUrlHead(filename);
                user.setId(Long.parseLong(id));
                int row = userMapper.updateImg(user);
                if(row>0){
                    model.addAttribute("message","上传图片"+filename+"成功！");
                    model.addAttribute("image", filename);
                    User byId = userMapper.findById(user.getId());
                    session.setAttribute("user",byId);
                }else {
                    model.addAttribute("error","上传图片路径到数据失败！");
                }
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("error","上传图片失败！");
            }
        }
        return "teacher/index";
    }

    /**
     * @Title: getFile
     * @Description: TODO(获取图片)
     * @param filename
     * @return ResponseEntity<?>    返回类型
     */
    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename) {
        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(uploadProperties.getBasePath() + filename)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
