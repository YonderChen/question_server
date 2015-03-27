package com.foal.question.jersey.resource.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foal.question.service.app.AppUserService;

/**
 * 
 * @author yonder
 * @date 2015-3-23
 */
@Component
public class BaseResource {
	@Autowired
	AppUserService appUserService;
	
}
