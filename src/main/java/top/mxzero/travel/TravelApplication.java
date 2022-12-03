package top.mxzero.travel;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import top.mxzero.travel.context.SpringContext;
import top.mxzero.travel.context.SpringWebContext;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/22
 */
public class TravelApplication extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringContext.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringWebContext.class};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceResponseEncoding(true);

        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();

        DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy("springSecurityFilterChain");

        return new Filter[]{
                characterEncodingFilter, hiddenHttpMethodFilter, delegatingFilterProxy
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

//    @Override
//    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
//        MultipartConfigElement element = new MultipartConfigElement(
//                "/tmp", 2097152, 5242880, 0
//        );
//        registration.setMultipartConfig(element);
//    }
}
