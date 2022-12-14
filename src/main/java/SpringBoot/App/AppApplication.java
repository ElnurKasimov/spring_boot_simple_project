package SpringBoot.App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);

//		BCryptPasswordEncoder coder = new BCryptPasswordEncoder();
//		String ivan = coder.encode("ivan");
//		String petr = coder.encode("petr");
//		String test = coder.encode("test");
//		String boss = coder.encode("boss");
//		System.out.println("ivan = " + ivan);
//		System.out.println("petr = " + petr);
//		System.out.println("test = " + test);
//		System.out.println("boss = " + boss);
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}
