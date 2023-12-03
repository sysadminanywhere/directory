package com.sysadminanywhere.sysadminanywhere.config;

import lombok.SneakyThrows;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.message.BindRequest;
import org.apache.directory.api.ldap.model.message.BindRequestImpl;
import org.apache.directory.api.ldap.model.name.Dn;
import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.ldap.client.api.LdapNetworkConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LdapConfig {

    @SneakyThrows
    @Bean
    public LdapConnection GetConnection() {
        String host = "192.168.245.129";
        int port = 389;

        String userName = "admin";
        String password = "Secret2#";

        LdapConnection connection = new LdapNetworkConnection(host, port);

        BindRequest bindRequest = new BindRequestImpl();
        bindRequest.setCredentials(password);
        bindRequest.setSimple(true);
        bindRequest.setName(userName);
        connection.bind(bindRequest);

        return connection;
    }

}