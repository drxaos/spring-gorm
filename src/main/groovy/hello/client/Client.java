package hello.client;

import java.util.Date;

public class Client extends WebApp {

    @Override
    protected String getStartUrl() {
        return "webapp:///client/html/main.html";
    }

    @Override
    protected void onRelocate(String url) {
        System.out.println("New URL: " + url);
    }

    @Override
    protected void onAlert(String data) {
        System.out.println("Alert: " + data);
    }

    @Override
    protected Object getAppBridge() {
        return new AppBridge();
    }

    public static class AppBridge {
        public String getUsername() {
            return System.getProperty("user.name");
        }

        public String getTime() {
            return new Date().toString();
        }
    }
}

