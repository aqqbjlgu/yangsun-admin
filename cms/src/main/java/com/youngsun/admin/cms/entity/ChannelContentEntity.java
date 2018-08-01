package com.youngsun.admin.cms.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_channel_content")
public class ChannelContentEntity implements Serializable {

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;
    /**
     * 栏目ID
     */
    @Column(name = "t_channel_id")
    private Long channelId;

    /**
     * 文章Id
     */
    @Column(name = "t_content_id")
    private Long contentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

}
