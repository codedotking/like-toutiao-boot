package com.hongyan.toutiao.model.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Article")
public class ArticleEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    private String title;

    private String abstractText;

    private String content;

    private int type;

    private boolean published;

    private int authorId;

    private long likeCount;

    private long diggCount;

    private long buryCount;

    private long commentCount;

    private long readCount;

    private long repinCount;

    private long shareCount;

    private boolean isCrawled;

    private String source;

    private String articleId;

    private String json;

    private boolean hasImage;

    private boolean hasM3u8Video;

    private boolean hasMp4Video;

    private boolean hasVideo;

    private String imageType;

    private int publishTime;
}