/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.security.service;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * client user service.
 */
@Configuration
@Data
public class SecurityClientDetailsSevice implements ClientDetailsService {
	private Map<String, ClientDetails> clientDetailsStore = new HashMap<String, ClientDetails>();

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		ClientDetails details = clientDetailsStore.get(clientId);
		if (details == null) {
			throw new NoSuchClientException("No client with requested id: " + clientId);
		}
		return details;
	}

	public void setClientDetailsStore(Map<String, ? extends ClientDetails> clientDetailsStore) {
		this.clientDetailsStore = new HashMap<String, ClientDetails>(clientDetailsStore);
	}

}
