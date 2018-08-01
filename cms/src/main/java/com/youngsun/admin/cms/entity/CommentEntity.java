package com.youngsun.admin.cms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "t_content_comment")
public class CommentEntity extends BasicEntity implements Serializable {

    /**
     * 文章ID
     */
    @Column(name = "t_content_id")
    private Long contentId;

    /**
     * 评论
     */
    @Column(name = "t_comment")
    private String contentComment;

    /**
     * 状态(0:未审核;1:审核未通过;2:审核通过)
     */
    @Column(name = "t_status")
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
