package com.kingboy.controller.upload;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/22 下午7:23
 * @desc 文件上传服务.
 */
@RestController
@RequestMapping(value = "/file")
public class UploadController {

    @PostMapping(value = "/upload")
    @SneakyThrows
    public String uploadFile(@RequestParam(value = "file", required = true)
                                         MultipartFile file) {
        Files.copy(file.getInputStream(), Paths.get("/Users/kingboy/Desktop/"
                + file.getOriginalFilename()));
        return "请到桌面进行查看上传的文件";
    }

}
