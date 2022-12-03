
package top.mxzero.travel.vo;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/12/3
 */
public class RestData {
    private String message;
    private Integer code;
    private Object data;

    public RestData() {
    }

    public static RestData success(Object data) {
        RestData restData = new RestData();
        restData.setMessage("success");
        restData.setCode(200);
        restData.setData(data);
        return restData;
    }

    public static RestData notFound() {
        RestData restData = new RestData();
        restData.setCode(404);
        restData.setMessage("resource not found");
        return restData;
    }

    public static RestData fail(String message) {
        RestData restData = new RestData();
        restData.setMessage(message);
        restData.setCode(400);
        return restData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
