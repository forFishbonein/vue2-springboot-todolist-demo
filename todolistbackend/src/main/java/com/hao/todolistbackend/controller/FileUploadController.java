package com.hao.todolistbackend.controller;


import com.hao.todolistbackend.dao.vo.Result;
import com.hao.todolistbackend.service.impl.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileUploadController {

    @Resource
    private FileUploadService fileUploadService;

    @PostMapping("/upload")
    @ResponseBody
    public Result upload(@RequestParam("file") MultipartFile multipartFile) {
        fileUploadService.upload(multipartFile);
        return Result.success("ok");
    }


}
