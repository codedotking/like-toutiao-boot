package com.hongyan.toutiao.model.dto;

import lombok.Data;


@Data
public class ArticlePageDto {

    private Long id;

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

    private boolean hasImage;

    private boolean hasM3u8Video;

    private boolean hasMp4Video;

    private boolean hasVideo;

    private String imageType;

    private int publishTime;



}
