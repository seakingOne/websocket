package com.ynhuang.websocket.demo2.file;

import lombok.Data;

/**
 * @Auther: 018399
 * @Date: 2019/2/25 10:24
 * @Description: 待上传文件基本信息
 */
@Data
public class FileInfo {

    //文件名
    private String fileName;

    private String fileId;

    //文件大小
    private long fileSize;

    //文件信息
    private String fileInfo;

}
