package hello

import hello.client.Client
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Application extends Client {

    static applicationContext

    public static void main(String[] args) throws Exception {
        Thread.start {
            SpringApplication app = new SpringApplication(Application.class);
            app.setShowBanner(false);
            applicationContext = app.run(args);
        }
        launch(Application.class, args)
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        applicationContext.close();
    }
}