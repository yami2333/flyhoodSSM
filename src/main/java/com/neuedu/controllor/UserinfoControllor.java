package com.neuedu.controllor;


import com.neuedu.pojo.Userinfo;
import com.neuedu.service.UserinfoService;
import com.neuedu.utils.MD5Utils;
import com.neuedu.utils.UploadInfo;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import static jdk.nashorn.internal.objects.NativeError.getFileName;

@Controller
@RequestMapping("user")
public class UserinfoControllor {
    @Autowired
    UserinfoService userinfoService;

    @GetMapping("emailCheck")
    @ResponseBody
    public int emailCheck(@RequestParam("email") String email){

        return userinfoService.userEmailCheck(email);
    }

    @PostMapping("reg")
    public String userReg(String email,String username,String pass){
        Userinfo u = new Userinfo();
        Date regTime = new Date();
        u.setEmail(email);
        u.setNickname(username);
        u.setPassword(MD5Utils.getPwd(pass)); // 密码加密
        u.setRegtime(regTime);
        int num = userinfoService.userReg(u);
        if(num != 0){
            return "redirect:go/login";
        }
        return "";
    }


    @RequestMapping("userLogin")
    public ModelAndView userLogin(String email,String password){
        ModelAndView mav = new ModelAndView();
        Userinfo u = new Userinfo();
        u.setEmail(email);
        u.setPassword(password);
        Userinfo userinfo = userinfoService.userLogin(u);
        if(userinfo != null){
            mav.addObject("login_user",userinfo);
            mav.setViewName("go/index");
        }else{
            mav.setViewName("go/login");
        }
        return mav;
    }


    @RequestMapping("uploadHead")
    public @ResponseBody UploadInfo uploadHeadImg(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        // 需要有上传图片的依赖
        // 在servlet上需要一个注解@MultipartConfig
        // 1.在服务器上保存图片 /xxxxxxx.jpg
        // 指定存储路径
        String savePath = request.getServletContext().getRealPath("/fly/res/images/avatar");
        File file = new File(savePath);
        if(!file.exists()) {
            file.mkdirs();
        }
        // 获取上传的文件集合
        Collection<Part> parts = request.getParts();
        UUID uuid = null;
        String fileName = "";
        for(Part part : parts){
            String header = part.getHeader("content-disposition");
            System.out.println(header);
            // 获取文件名
            fileName = (String) getFileName(header);// 1.jpg v JPG
            if(!fileName.endsWith(".jpg") && !fileName.endsWith(".png") &&  !fileName.endsWith(".gif") && !fileName.endsWith(".JPG") &&  !fileName.endsWith(".PNG") &&  !fileName.endsWith(".GIF")){
                UploadInfo uploadInfo = new UploadInfo();
                uploadInfo.setStatus(1);
//                response.getWriter().println(mapper.writeValueAsString(uploadInfo));
                return uploadInfo;
            }
            // 把文件写到指定路径
            // 生成一个uuid
            uuid = UUID.randomUUID();
            part.write(savePath + File.separator + uuid + fileName);
        }
        // 2.修改数据库xxxxxxx.jpg
        Userinfo login_user = (Userinfo) request.getSession().getAttribute("login_user");
        login_user.setHeadurl(uuid + fileName);
        int num = userinfoService.uploadHeadImg(login_user);
        // 3.同步更新session中login_user的headurl xxxxxxx.jpg
        // 4.响应ajax信息
        UploadInfo uploadInfo = new UploadInfo();
        uploadInfo.setStatus(0);
        uploadInfo.setPath(request.getContextPath()+"/fly/res/images/avatar/"+uuid + fileName);

//        response.getWriter().println(mapper.writeValueAsString(uploadInfo));
        return uploadInfo;
    }
    public String getFileName(String header) {
        /**
         * String[] tempArr1 = header.split(";");代码执行完之后，在不同的浏览器下，tempArr1数组里面的内容稍有区别
         * 火狐或者google浏览器下：tempArr1={form-data,name="file",filename="snmp4j--api.zip"}
         * IE浏览器下：tempArr1={form-data,name="file",filename="E:\snmp4j--api.zip"}
         */
        String[] tempArr1 = header.split(";");
        /**
         * 火狐或者google浏览器下：tempArr2={filename,"snmp4j--api.zip"}
         * IE浏览器下：tempArr2={filename,"E:\snmp4j--api.zip"}
         */
        String[] tempArr2 = tempArr1[2].split("=");
        // 获取文件名，兼容各种浏览器的写法
        String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\") + 1).replaceAll("\"", "");
        return fileName;
    }
}
