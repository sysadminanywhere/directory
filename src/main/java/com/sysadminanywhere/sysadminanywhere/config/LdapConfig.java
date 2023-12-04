package com.sysadminanywhere.sysadminanywhere.config;

import lombok.SneakyThrows;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.message.BindRequest;
import org.apache.directory.api.ldap.model.message.BindRequestImpl;
import org.apache.directory.api.ldap.model.name.Dn;
import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.ldap.client.api.LdapNetworkConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LdapConfig {

    @Value("${ldap.host.server}")
    private String server;

    @Value("${ldap.host.port}")
    private int port;

    @Value("${ldap.host.username}")
    private String userName;

    @Value("${ldap.host.password}")
    private String password;

    @SneakyThrows
    @Bean
    public LdapConnection GetConnection() {
        LdapConnection connection = new LdapNetworkConnection(server, port);

        BindRequest bindRequest = new BindRequestImpl();
        bindRequest.setCredentials(password);
        bindRequest.setSimple(true);
        bindRequest.setName(userName);
        connection.bind(bindRequest);

        return connection;
    }

}