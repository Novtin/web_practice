package application.entity;

public class PushNotifyConf {
    private String title;
    private String body;
    private String click_action;
    private String ttlInSeconds;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getClick_action() {
        return click_action;
    }

    public void setClick_action(String click_action) {
        this.click_action = click_action;
    }

    public String getTtlInSeconds() {
        return ttlInSeconds;
    }

    public void setTtlInSeconds(String ttlInSeconds) {
        this.ttlInSeconds = ttlInSeconds;
    }

    public PushNotifyConf(String title, String body, String click_action, String ttlInSeconds) {
        this.title = title;
        this.body = body;
        this.click_action = click_action;
        this.ttlInSeconds = ttlInSeconds;
    }

    public PushNotifyConf() {
    }
}
