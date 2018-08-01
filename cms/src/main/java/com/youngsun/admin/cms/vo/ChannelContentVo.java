package com.youngsun.admin.cms.vo;

import java.io.Serializable;

public class ChannelContentVo implements Serializable {

    /**
     * 主键ID
     */
    private Long id;
    /**
     * 栏目ID
     */
    private Long channelId;

    /**
     * 文章Id
     */
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
