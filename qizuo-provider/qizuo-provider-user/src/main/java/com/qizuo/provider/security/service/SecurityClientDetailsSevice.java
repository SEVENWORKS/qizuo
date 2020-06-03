/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.provider.security.service;

import com.qizuo.provider.security.model.SecurityUser;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;

import java.util.HashMap;
import java.util.Map;

/** client user service. */
@Configuration
@Data
public class SecurityClientDetailsSevice implements ClientDetailsService {
  private Map<String, SecurityUser> clientDetailsStore = new HashMap<String, SecurityUser>();

  // 返回user信息
  @Override
  public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
    ClientDetails details = clientDetailsStore.get(clientId);
    if (details == null) {
      throw new NoSuchClientException("No client with requested id: " + clientId);
    }
    return details;
  }

  public void setClientDetailsStore(Map<String, ? extends SecurityUser> clientDetailsStore) {
    this.clientDetailsStore = new HashMap<String, SecurityUser>(clientDetailsStore);
  }
}
