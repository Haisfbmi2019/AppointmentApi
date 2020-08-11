package andry.hais.appointment_api.config;

import andry.hais.appointment_api.models.Profile;
import andry.hais.appointment_api.models.Student;
import andry.hais.appointment_api.models.Teacher;
import andry.hais.appointment_api.models.User;
import andry.hais.appointment_api.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppConfig extends GlobalMethodSecurityConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public CommandLineRunner demo(
                                  final UserService userService
                                  ) {
        return strings -> {
            userService.save(new User("email1@gmail.com", "1234", "teacher1", "1234121", true, "ROLE_TEACHER", new Profile(new Teacher("Professor", "Math"))));
            userService.save(new User("email2@gmail.com", "2222", "student1", "1212121", true, "ROLE_STUDENT", new Profile(new Student("KPI", "1234"))));
        };
    }

}
