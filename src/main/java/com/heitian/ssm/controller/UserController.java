package com.heitian.ssm.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heitian.ssm.model.Token;
import com.heitian.ssm.model.User;
import com.heitian.ssm.service.TokenService;
import com.heitian.ssm.service.UserService;
import com.heitian.ssm.utils.AliyunUtils;
import com.heitian.ssm.utils.DateUtils;
import com.heitian.ssm.utils.JackJson;
import com.heitian.ssm.utils.Random;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController {
    //private Logger log = Logger.getLogger(UserController.class);
    @Resource
    private UserService userService;
    @Resource
    private TokenService tokenService;

    private ObjectMapper objectMapper = new ObjectMapper();

    private JSONObject jsonObject = new JSONObject();

    private AliyunUtils aliyunUtils = new AliyunUtils();

    private JackJson jackJson = new JackJson();

    private DateUtils dateUtils = new DateUtils();

    //通过用户id查询出用户的主要信息
    @RequestMapping("/get_DetailUser")
    @ResponseBody
    public JSONObject getDetailUser(@RequestBody User user) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String idJson = objectMapper.writeValueAsString(user);
        User user1 = objectMapper.readValue(idJson, User.class);
        User user2 = userService.getUserById(user1.getUserId());
        if (user2 != null) {
            JSONObject object1 = new JSONObject();
            jsonObject.put("info", "success");
            URL url = aliyunUtils.getUrl(user2.getUserHead());
            object1.put("userHead", url);
            object1.put("userName", user2.getUserName());
            object1.put("userId", user2.getUserId());
            object1.put("userBirth", DateUtils.dateToStrLong(user2.getUserBirth()));
            object1.put("userCreateTime", DateUtils.dateToStrLong(user2.getUserCreateTime()));
            jsonObject.put("user", object1);
        } else {
            jsonObject.put("info", "error");
        }
        return jsonObject;
    }

    //用于发送验证码
    @RequestMapping("/get_Code")
    @ResponseBody
    public void getCode() throws Exception {
//        String num=objectMapper.writeValueAsString(phoneNum);
//        User user1 = objectMapper.readValue(num, User.class);
//        System.out.println(user1);
    }


    //用于手机用户注册
    @RequestMapping("/insert_user_by_phone")
    @ResponseBody
    public JSONObject insertUserByPhone(@RequestBody User user) {
        JSONObject jsonObject = new JSONObject();
        try {
            String jsonUser = objectMapper.writeValueAsString(user);
            User user1 = objectMapper.readValue(jsonUser, User.class);
            user1.setUserCreateTime(dateUtils.getNow());
            user1.setUserName(Random.getRandomString(11));
            User user2 = userService.selectUserByPhone(user1.getUserPhone());
            if (user2 != null) {
                jsonObject.put("info", "exists");
            } else {
                int result = userService.insertuser(user1);
                if (result != 0) {
                    jsonObject.put("info", "success");
                } else {
                    jsonObject.put("info", "failed");
                }
            }
        } catch (Exception e) {
            jsonObject.put("info", "failed");
        }
        return jsonObject;
    }

    //用于第三方用户登录
    @RequestMapping("/login_by_qq")
    @ResponseBody
    public JSONObject loginByQq(@RequestBody User user) {
        try {
            String request = objectMapper.writeValueAsString(user);
            User user1 = objectMapper.readValue(request, User.class);
            User user2 = userService.selectUserByQqId(user1);
            if (user2 == null) {
                User newUser = new User();
                newUser.setUserCreateTime(dateUtils.getNow());
                newUser.setUserName(Random.getRandomString(11));
                newUser.setUserQqId(user1.getUserQqId());
                userService.insertuser(newUser);
                jsonObject.put("user", newUser);
                jsonObject.put("info", "success");
            } else {
                jsonObject.put("user", user2);
                jsonObject.put("info", "success");
            }
        } catch (Exception e) {
            jsonObject.put("info", "error");
        }
        return jsonObject;
    }

    //用户登录
    @RequestMapping("/user_login")
    @ResponseBody
    public JSONObject userLogin(@RequestBody User user) {
        JSONObject object = new JSONObject();
        try {
            String username = objectMapper.writeValueAsString(user);
            User user1 = objectMapper.readValue(username, User.class);
            User user2 = userService.userlogin(user1);
            if (user2 != null) {
                JSONObject object1 = new JSONObject();
                object1.put("userName", user2.getUserName());
                object1.put("userId", user2.getUserId());
                URL url = aliyunUtils.getUrl(user2.getUserHead());
                object1.put("userHead", url);
                Token token = new Token();
                token.setId(user2.getUserId());
                token.setToken((String.valueOf((Math.random() * 9 + 1) * 1000)));
                tokenService.insertToken(token);
                object.put("token", token.getToken());
                object.put("user", object1);
                object.put("info", "success");
            } else {
                object.put("user", null);
                object.put("info", "error");
            }
        } catch (Exception e) {
            object.put("info", "error_already");
        }
        return object;
    }

    //更改用户信息
    @RequestMapping("/upData_user")
    @ResponseBody
    public JSONObject upDataUser(@RequestBody User user) {
        JSONObject object = new JSONObject();
        try {
            String username = objectMapper.writeValueAsString(user);
            User user1 = objectMapper.readValue(username, User.class);
            User user2 = userService.getUserById(user1.getUserId());
            if (user2 != null) {
                userService.updatauser(user1);
                object.put("user", user1);
                object.put("info", "success");
            } else {
                object.put("user", null);
                object.put("info", "error");
            }
        } catch (Exception e) {
            object.put("info", "error");
        }
        return object;
    }

    //用户登出
    @RequestMapping("/user_quit")
    @ResponseBody
    public JSONObject userQuit(@RequestBody Token token) {
        JSONObject jsonObject = new JSONObject();
        try {
            String request = objectMapper.writeValueAsString(token);
            Token token1 = objectMapper.readValue(request, Token.class);
            Token realToken = tokenService.getTokenByid(token1.getId());
            if (realToken.getToken().equals(token1.getToken())) {
                tokenService.deleteToken(token1.getId());
                jsonObject.put("info", "success");
            } else {
                jsonObject.put("info", "error");
            }
        } catch (Exception e) {
            jsonObject.put("info", "error_quit");
        }
        return jsonObject;
    }


    //验证原密码
    @RequestMapping("/sure_Pwd_By_Old")
    @ResponseBody
    public JSONObject surePassWord(HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("userId");
        String password = request.getParameter("password");
        int id2 = Integer.valueOf(id);
        User user = userService.getUserById((long) id2);
        if (user.getUserPassword().equals(password)) {
            jsonObject.put("info", "success");
        } else {
            jsonObject.put("info", "error");
        }
        return jsonObject;
    }


    //更改密码
    @RequestMapping("/upData_Pwd_By_Old")
    @ResponseBody
    public JSONObject upDataPassWord(HttpServletRequest request) throws Exception {
        try {
            String id = request.getParameter("userId");
            String newPassWord = request.getParameter("newPassword");
            int id2 = Integer.valueOf(id);
            User user = userService.getUserById((long) id2);
            user.setUserPassword(newPassWord);
            userService.upDataUserPassWord(user);
            jsonObject.put("info", "success");
        } catch (Exception e) {
            jsonObject.put("info", "error");
        }
        return jsonObject;
    }


    //通过验证短信更改密码
    @RequestMapping("/upData_passWord_By_code")
    @ResponseBody
    public JSONObject upDataPassWordByCode(HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id");
        String newPassWord = request.getParameter("newPassword");
        int id2 = Integer.valueOf(id);
        User user = userService.getUserById((long) id2);
        user.setUserPassword(newPassWord);
        userService.updatauser(user);
        jsonObject.put("info", "success");
        return jsonObject;
    }

    //找回密码
    @RequestMapping("/to_back_password")
    @ResponseBody
    public JSONObject backPass(HttpServletRequest request) throws Exception {
        String phone = request.getParameter("phone");
        User user1 = userService.getUserById((long) 9);
        user1.setUserPhone(phone);
        JSONObject object = new JSONObject();
        object.put("info", user1.getUserPassword());
        return object;
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public List<User> myDomain(@RequestBody List<User> jsonObject) throws Exception {
        String J = jackJson.writeValue(jsonObject);
        List<User> getUser = objectMapper.readValue(J, new TypeReference<List<User>>() {
        });
        for (User user : getUser) {
            System.out.println(user.getUserName());
        }
        return getUser;
    }


    @RequestMapping(value = "/test1", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject mapTest(@RequestBody Map<String, Object> map) throws Exception {
        ArrayList<String> img = (ArrayList<String>) map.get("img");
        String name = (String) map.get("name");
        String str1 = "";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("else", name);
        for (String str : img) {
            JSONArray array = new JSONArray();
            str1 = str1 + str;
            StringBuffer buffer = new StringBuffer(str1);
            buffer.append(str);
            User user = new User();
            user.setUserName(buffer.toString());
            array.add(user);
            jsonObject.put("user", array);
        }
        return jsonObject;
    }
}
