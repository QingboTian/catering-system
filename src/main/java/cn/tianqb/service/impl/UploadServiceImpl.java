package cn.tianqb.service.impl;

import cn.tianqb.service.UploadService;
import cn.tianqb.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.StringJoiner;

/**
 * @author tianqingbo3
 * @date 2021/3/3 22:04
 * @version v1.0
 */
@Service
@Slf4j
public class UploadServiceImpl implements UploadService {

    @Value("${upload.base-path}")
    private String basePath;

    @Override
    public String uploadImg(MultipartFile file) {
        String init = init(basePath);
        String name = file.getOriginalFilename();
        InputStream inputStream = null;
        FileOutputStream fos = null;
        try {
            inputStream = file.getInputStream();
            StringJoiner targetPath = new StringJoiner("/");
            targetPath.add(init).add(UUIDUtils.uuid() + getExt(name));
            fos = new FileOutputStream(new File(targetPath.toString()));
            StreamUtils.copy(inputStream, fos);
            return targetPath.toString().replace(basePath, "");
        } catch (IOException e) {
            log.error("上传文件发生错误", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private String init(String path) {
        String str = path + "/static";
        File file = new File(str);
        if (!file.exists()) {
            boolean res = file.mkdirs();
            log.info("文件创建结果：{}", res);
        }
        return str;
    }

    private String getExt(String fileName) {
        int index = fileName.lastIndexOf('.');
        if (index > 0) {
            return fileName.substring(index);
        }
        return "";
    }
}
