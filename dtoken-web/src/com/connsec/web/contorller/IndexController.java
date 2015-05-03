package com.connsec.web.contorller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Index
 * @author Crystal.Sea
 *
 */
@Controller
public class IndexController {
	
	private static Log _log = LogFactory.getLog(IndexController.class);
  	

	@RequestMapping(value={"/index"})
	public ModelAndView home() {
		_log.debug("IndexController /index.");
		
		return  new ModelAndView("index");
	}
	
	@RequestMapping(value={"/"})
	public ModelAndView index() {
		_log.debug("IndexController /.");
		return  new ModelAndView("index");
		
	}
	
	@RequestMapping(value={"/layout/top"})
	public ModelAndView top() {
		_log.debug("IndexController /layout/top.");
		return  new ModelAndView("layout/top");
	}
	
	@RequestMapping(value={"/layout/nologintop"})
	public ModelAndView nologintop() {
		_log.debug("IndexController /layout/nologintop.");
		return  new ModelAndView("layout/nologintop");
	}
	
	
	@RequestMapping(value={"/layout/left"})
	public ModelAndView left() {
		_log.debug("IndexController /layout/left.");
        return  new ModelAndView("layout/left");
	}
	
	@RequestMapping(value={"/layout/main"})
	public ModelAndView main() {
		_log.debug("IndexController /layout/main.");
		return  new ModelAndView("layout/main");
	}
	
	@RequestMapping(value={"/layout/bottom"})
	public ModelAndView bottom() {
		_log.debug("IndexController /layout/bottom.");
		return  new ModelAndView("layout/bottom");
	}
	
	@RequestMapping(value={"/accessdeny"})
	public ModelAndView accessdeny() {
		_log.debug("exception/accessdeny.");
		return  new ModelAndView("exception/accessdeny");
	}
}