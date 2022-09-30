package top.mxzero.travel.vo;

import java.util.Date;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/23
 */
public class Area {
    private Integer id;
    private String name;
    private String introduction;
    private Boolean recommended;
    private Date createTime;
    private Date updateTime;
    public Area(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Boolean getRecommended() {
        return recommended;
    }

    public void setRecommended(Boolean recommended) {
        this.recommended = recommended;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", introduction='" + introduction + '\'' +
                ", recommended=" + recommended +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
