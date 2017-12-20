package lambdasinaction.chap5.psh;


import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author psh
 * @description 车辆详情页视频model
 * @date Created on 2017/9/20.
 */
public class CarDetailVideo implements Serializable {
    private static final long serialVersionUID = 1;
    private Integer id;
    private String modelCode;
    private List<Map<String, String>> sources;
    private Integer sort;
    private String name;
    private String value;
    private String duration;
    private String thumb;

    public CarDetailVideo(){

    }

    public CarDetailVideo(Integer id, String modelCode,  String name, String value, String duration, String thumb) {
        this.id = id;
        this.modelCode = modelCode;
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

    public List<Map<String, String>> getSources() {
        return sources;
    }

    public void setSources(List<Map<String, String>> sources) {
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

    public static final List<CarDetailVideo> carDetailVideos = Arrays.asList(
            new CarDetailVideo(1,"code","name","value","dur","thu"),
            new CarDetailVideo(2,"code2","name2","value2","dur2","thu2")
    );
}
