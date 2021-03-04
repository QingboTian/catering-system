package cn.tianqb.controller;

import cn.tianqb.common.WebResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/3/3 20:58
 */
@RestController
@Slf4j
@RequestMapping("/api/upload")
public class UploadController {

    @PostMapping("/img")
    public WebResult<String> uploadImg(MultipartFile file) {
        return null;
    }

}
