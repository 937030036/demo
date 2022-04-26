/*
 * @Description: 
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-07 10:59:45
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-26 22:13:38
 */
package com.example.demo.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.BeanUtils;
import com.example.demo.Service.interfaces.PageService;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user/Meau")
public class UserIndexCtler {

    private PageService pageservice = BeanUtils.getBean(PageService.class);

    @RequestMapping("")
    public String showMeau(HttpServletRequest request) {
        pageservice.IndexPageService(request);
        return "./users/Index";
    }

    @RequestMapping("/pic")
    public String showPic() {
        return "./users/pic";
    }

    @RequestMapping("/video")
    public String showVideo() {
        return "./users/video";
    }

    @RequestMapping("/secret")
    @ResponseBody
    public String showSecret() {
        return "关于作者:\nqq:937030036\n有任何技术问题或版权问题欢迎联系，或者在github上提issue，本人看到都会第一时间回复。";
    }

    @RequestMapping(value = "/getpic", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public byte[] getPic() throws IOException {
        File file = new File("src/main/resources/templates/users/pic.png");
        FileInputStream input = new FileInputStream(file);
        byte[] bytes = new byte[input.available()];
        input.read(bytes, 0, input.available());
        input.close();
        return bytes;
    }

    @RequestMapping(value = "/getvideo")
    public void getvideo(HttpServletResponse response) throws IOException {
        File file = new File("src/main/resources/templates/users/video.mp4");
        FileInputStream input = new FileInputStream(file);
        byte[] bytes = new byte[input.available()];
        input.read(bytes, 0, input.available());
        OutputStream os = response.getOutputStream();
        os.write(bytes);

        os.flush();
        os.close();
        input.close();
    }
}
