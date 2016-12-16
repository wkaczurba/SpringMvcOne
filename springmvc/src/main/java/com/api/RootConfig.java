package com.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@Import(DataConfig.class)
@ComponentScan(basePackages={"com"},
		excludeFilters={@Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)})
	//excludeFilters{@Filter(type=FilterType.CUSTOM, value=WebPackage.class)})
    //FIXME: Not sure what is this exclude filter for (from Chapter5 code example -> spittr)
public class RootConfig {

//FIXME: Not sure what is this exclude filter for (from Chapter5 code example -> spittr) 
/*
  public static class WebPackage extends RegexPatternTypeFilter {
    public WebPackage() {
      super(Pattern.compile("spittr\\.web"));
    }    	  
*/
	
}
