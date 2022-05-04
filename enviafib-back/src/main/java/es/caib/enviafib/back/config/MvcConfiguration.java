package es.caib.enviafib.back.config;

import java.util.Locale;

import es.caib.enviafib.commons.utils.Configuracio;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.util.UrlPathHelper;

/**
 * 
 * @author anadal
 *
 */
@Configuration
@ComponentScan(basePackages = {"es.caib.enviafib"})
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Bean
	public LocaleResolver localeResolver() {
	    SessionLocaleResolver slr = new es.caib.enviafib.back.utils.EnviaFIBSessionLocaleResolver();
	    slr.setDefaultLocale(new Locale(Configuracio.getDefaultLanguage()));
	    return slr;
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(new String[] { "/WEB-INF/tiles.xml" });

		return tilesConfigurer;
	}

	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		TilesViewResolver viewResolver = new TilesViewResolver();
		registry.viewResolver(viewResolver);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css").addResourceLocations("/img").addResourceLocations("/js");
	}

	@Bean
	public BeanPostProcessor beanPostProcessor() {
	    return new es.caib.enviafib.back.security.DefaultRolesPrefixPostProcessor();
	}

	@Bean
	public MultipartResolver multipartResolver() {
		return new es.caib.enviafib.back.utils.EnviaFIBCommonsMultipartResolver();
	}

	@Bean
	public HandlerExceptionResolver getFileSizeExceeds() {
	  return new es.caib.enviafib.back.utils.EnviaFIBMaxUploadSizeExceededExceptionHandler();
	}

	@Bean
	public MessageSource messageSource() {

		ReloadableResourceBundleMessageSource messageSource = new org.springframework.context.support.ReloadableResourceBundleMessageSource();
		messageSource.setDefaultEncoding("UTF-8");

		messageSource.addBasenames("/WEB-INF/classes/missatges", "/WEB-INF/classes/logicmissatges",
				"/WEB-INF/classes/enviafib_genapp", "/WEB-INF/classes/genapp");

		// messageSource.addBasenames("/WEB-INF/classes/messages",
		// "/WEB-INF/classes/ValidationMessages",
		// "classpath:shared/ValidationMessages");

		return messageSource;
	}
	
	@Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        
        System.setProperty("org.apache.tomcat.util.buf.UDecoder.ALLOW_ENCODED_SLASH", "true");
        
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setUrlDecode(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }

	/*
	 * @Bean public LocalValidatorFactoryBean validator() {
	 * 
	 * ReloadableResourceBundleMessageSource messageSource = messageSource();
	 * 
	 * MyLocalValidatorFactoryBean bean = new
	 * MyLocalValidatorFactoryBean(messageSource);
	 * bean.setValidationMessageSource(messageSource);
	 * 
	 * bean.setMessageInterpolator( new
	 * MyMessageInterpolator(Validation.byDefaultProvider().configure().
	 * getDefaultMessageInterpolator()));
	 * 
	 * return bean; }
	 * 
	 * @Override public org.springframework.validation.Validator getValidator() {
	 * return validator(); }
	 */
}
