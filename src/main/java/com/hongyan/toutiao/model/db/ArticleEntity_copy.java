//package com.hongyan.toutiao.model.db;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.*;
//import lombok.Data;
//
//@Data
//@Entity
//@Table(name = "Article")
//public class ArticleEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @Column(name = "title", nullable = false, length = 191)
//    private String title;
//
//    @Column(name = "abstract", length = 191)
//    private String abstractText;
//
//    @Column(name = "content", length = 191)
//    private String content;
//
//    @Column(name = "type", nullable = false)
//    private int type;
//
//    @Column(name = "published", nullable = false)
//    private boolean published;
//
//    @Column(name = "authorId", nullable = false)
//    private int authorId;
//
//    @Column(name = "like_count", nullable = false)
//    private long likeCount;
//
//    @Column(name = "digg_count", nullable = false)
//    private long diggCount;
//
//    @Column(name = "bury_count", nullable = false)
//    private long buryCount;
//
//    @Column(name = "comment_count", nullable = false)
//    private long commentCount;
//
//    @Column(name = "read_count", nullable = false)
//    private long readCount;
//
//    @Column(name = "repin_count", nullable = false)
//    private long repinCount;
//
//    @Column(name = "share_count", nullable = false)
//    private long shareCount;
//
//    @Column(name = "is_crawled", nullable = false)
//    private boolean isCrawled;
//
//    @Column(name = "source", length = 191)
//    private String source;
//
//    @Column(name = "article_id", length = 191)
//    private String articleId;
//
//    @Column(name = "json")
//    private String json;
//
//    @Column(name = "has_image", nullable = false)
//    private boolean hasImage;
//
//    @Column(name = "has_m3u8_video", nullable = false)
//    private boolean hasM3u8Video;
//
//    @Column(name = "has_mp4_video", nullable = false)
//    private boolean hasMp4Video;
//
//    @Column(name = "has_video", nullable = false)
//    private boolean hasVideo;
//
//    @Column(name = "image_type", length = 191)
//    private String imageType;
//
//    @Column(name = "publish_time")
//    private int publishTime;
//
//}
