package com.heitian.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.heitian.ssm.model.User;
import com.heitian.ssm.service.UserService;
import com.heitian.ssm.utils.AliyunUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URL;


@Controller
@RequestMapping("/upload")
public class Upload {
    @Resource
    private UserService userService;

    private AliyunUtils aliyunUtils = new AliyunUtils();


    //上传头像le
    @RequestMapping("/to_upload")
    @ResponseBody
    public JSONObject toupload(MultipartFile file, HttpServletRequest request) throws Exception {
        JSONObject object = new JSONObject();
        String path = request.getSession().getServletContext().getRealPath("upload");
        String userID = request.getParameter("userId");
        try {
            User user = userService.getUserById(Long.valueOf(userID));
            String fileName = System.currentTimeMillis() + file.getOriginalFilename();
            File dir = new File(path, fileName);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            file.transferTo(dir);
// 上传文件
            user.setUserHead(fileName);
            userService.upDataUserHead(user);
            String info = aliyunUtils.upload(fileName, dir);
// 生成URL
            URL url = aliyunUtils.getUrl(fileName);
            object.put("url", url);
            object.put("info", info);
        } catch (Exception e) {
            object.put("info", "failed");

        }
        return object;
    }


}

