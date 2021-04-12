package com.atguigu.yygh.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author : xsh
 * @create : 2021-04-13 - 0:45
 * @describe:
 */
public interface FileService {
    //上传文件到阿里云oss
    String upload(MultipartFile file);
}
