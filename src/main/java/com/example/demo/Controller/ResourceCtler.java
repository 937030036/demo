/*
 * @Description: 
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-07 10:59:45
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-30 14:31:08
 */
package com.example.demo.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "", method = RequestMethod.GET)
public class ResourceCtler {
    // private PageService pageservice = BeanUtils.getBean(PageService.class);

    @RequestMapping("/secret")
    @ResponseBody
    public String showSecret() {
        return "关于作者:\nqq:937030036\n有任何技术问题或版权问题欢迎联系，或者在github上提issue，本人看到都会第一时间回复。";
    }

    @GetMapping(value = "/getpic", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public byte[] getPic(HttpServletRequest request) throws IOException {
        String pic = new String(request.getParameter("pic").getBytes("ISO-8859-1"), "UTF-8");
        File file = new File("src/main/resources/" + pic + ".jpg");
        FileInputStream input = new FileInputStream(file);
        byte[] bytes = new byte[input.available()];
        input.read(bytes, 0, input.available());
        input.close();
        return bytes;
    }

    @GetMapping(value = "/getvideo")
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
