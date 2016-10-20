package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * MailProperties
 * Properties class for mail through this class uses MailService necessary data are set in application.properties
 *
 * @author Andrii Blyznuk
 */

@Component
@ConfigurationProperties(prefix = "mail")
public class MailProperties {

    private String host;
    private String port;
    private String userName;
    private String password;
    private String protocol;
    private String auth;
    private String starttlsEnable;
    private String checkServerIdentity;
    private String trust;
    private String starttlsRequired;
    private String debug;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUserName() { return userName; }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getStarttlsEnable() {
        return starttlsEnable;
    }

    public void setStarttlsEnable(String starttlsEnable) {
        this.starttlsEnable = starttlsEnable;
    }

    public String getCheckServerIdentity() {
        return checkServerIdentity;
    }

    public void setCheckServerIdentity(String checkServerIdentity) {
        this.checkServerIdentity = checkServerIdentity;
    }

    public String getTrust() {
        return trust;
    }

    public void setTrust(String trust) {
        this.trust = trust;
    }

    public String getStarttlsRequired() {
        return starttlsRequired;
    }

    public void setStarttlsRequired(String starttlsRequired) {
        this.starttlsRequired = starttlsRequired;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }
}
