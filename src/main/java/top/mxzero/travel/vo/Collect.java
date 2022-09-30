package top.mxzero.travel.vo;

import java.util.Date;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/28
 */
public class Collect {
    private Long id;
    private Long userId;
    private Integer scenicId;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getScenicId() {
        return scenicId;
    }

    public void setScenicId(Integer scenicId) {
        this.scenicId = scenicId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Collect{" +
                "id=" + id +
                ", userId=" + userId +
                ", scenicId=" + scenicId +
                '}';
    }
}
