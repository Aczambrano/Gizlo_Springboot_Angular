package ec.telconet.mscomppruebaandersonzambrano;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class MsCompPruebaAndersonZambranoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCompPruebaAndersonZambranoApplication.class, args);
	}

	@Bean
	public CorsFilter corsFiler() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOriginPatterns(Collections.singletonList("*"));
		config.setAllowedHeaders(Arrays.asList("*"));
		config.addAllowedMethod("POST");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

}
