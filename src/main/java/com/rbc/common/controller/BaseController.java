package com.rbc.common.controller;

import com.rbc.common.utils.ShiroUtils;
import com.rbc.system.domain.UserDO;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}

}