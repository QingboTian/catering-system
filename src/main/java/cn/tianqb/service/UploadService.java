package cn.tianqb.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author tianqingbo3
 * @date 2021/3/3 21:59  
 * @version v1.0
 */
public interface UploadService {
    String uploadImg(MultipartFile file);
}
