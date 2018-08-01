package com.youngsun.admin.cms.vo;

import java.io.Serializable;

public class CommentVo extends BasicVo implements Serializable {
    /**
     * 文章ID
     */
    private Long contentId;

    /**
     * 评论
     */
    private String contentComment;

    /**
     * 状态(0:未审核;1:审核未通过;2:审核通过)
     */
    private String stuts;

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String getContentComment() {
        return contentComment;
    }

    public void setContentComment(String contentComment) {
        this.contentComment = contentComment;
    }

    public String getStuts() {
        return stuts;
    }

    public void setStuts(String stuts) {
        this.stuts = stuts;
    }
}
