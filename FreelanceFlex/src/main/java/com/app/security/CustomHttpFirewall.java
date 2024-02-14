package com.app.security;

import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.FirewalledRequest;
import javax.servlet.http.HttpServletRequest;

	public class CustomHttpFirewall extends DefaultHttpFirewall {
	    @Override
	    public FirewalledRequest getFirewalledRequest(HttpServletRequest request) {
	        FirewalledRequest firewalledRequest = super.getFirewalledRequest(request);
	        // Allow URLs containing potentially malicious strings
	        return firewalledRequest;
	    }
	}
