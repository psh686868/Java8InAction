package lambdasinaction.testjson;


import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author psh
 * @description 车辆详情页视频model
 * @date Created on 2017/9/20.
 */
public class CarDetailVideo2Json implements Serializable {
    private static final long serialVersionUID = 1;
    private Integer id = 0;
    private String modelCode = "";
    private List<VideoDetail> sources = Collections.emptyList();
    private Integer sort = 0;
    private String name = "";
    private String value = "";
    private String duration = "";
    private String thumb = "";

    public CarDetailVideo2Json(){

    }

    public CarDetailVideo2Json(Integer id, String modelCode, Integer sort, String name, String value, String duration, String thumb) {
        this.id = id;
        this.modelCode = modelCode;
        this.sort = sort;
        this.name = name;
        this.value = value;
        this.duration = duration;
        this.thumb = thumb;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public List<VideoDetail> getSources() {
        return sources;
    }

    public void setSources(List<VideoDetail> sources) {
        this.sources = sources;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
