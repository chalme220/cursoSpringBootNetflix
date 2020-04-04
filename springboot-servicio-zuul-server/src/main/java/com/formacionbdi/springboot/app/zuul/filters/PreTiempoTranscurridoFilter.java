package com.formacionbdi.springboot.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

//marcamos como un bean de Spring
@Component
public class PreTiempoTranscurridoFilter  extends ZuulFilter {

	//pero mejor utilizar lombok (log)
	private static Logger log = LoggerFactory.getLogger(PreTiempoTranscurridoFilter.class);
	
	//valida  si ejecutamos o no el filtro en cada request 
	@Override
	public boolean shouldFilter() {
		return true;
	}

	//resuelve la logica del filtro
	@Override
	public Object run() throws ZuulException {
		
		
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info("%s request enrutado a %s", request.getMethod(), request.getRequestURL().toString());
		
		Long tiempoInicio = System.currentTimeMillis();
		request.setAttribute("tiempoInicio", tiempoInicio);
				
		return null;
	}

	//tipo pre
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
