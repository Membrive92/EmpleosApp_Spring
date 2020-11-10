package Membri.springBoot.empleosApp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Value("${empleosApp.ruta.imagenes}")
	private String rutaImagenes;
	
@Override	
public void addResourceHandlers(ResourceHandlerRegistry registry) {
	//configura la ruta visibler donde se almacenan las imagenes
	registry.addResourceHandler("/logos/**").addResourceLocations("file:"+rutaImagenes);
}
}
