package com.connsec.api.v1.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.connsec.dao.service.UserInfoService;

@Controller
@RequestMapping(value = { "/api/v1/users" })
public class ApiUsersController {


	@Autowired
	private ShaPasswordEncoder passwordEncoder;

	@Autowired
	@Qualifier("userInfoService")
	private UserInfoService userInfoService;
	
	

}
