package de.wk.jwt.util;

import de.wk.jwt.beans.Member;
import de.wk.jwt.beans.OAuthToken;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

public class OneClickClient {

    //well defined uri endpoints
    private final static String BASE_URL = "https://services.%s/ServiceHosts";
    private final static String ENDPOINT_TOKEN_OAUTH = "/authority/connect/token";
    private final static String ENDPOINT_USER_SESSION_ME_NEW = "/oneclick/api/network/members/me";

    //oneClick system to connect to (eg. portalbereich.de)
    private final String oneClickUrl;
    private final String clientSecret;

    public OneClickClient(String oneClickUrl, String clientSecret) {
        this.oneClickUrl = oneClickUrl;
        this.clientSecret = clientSecret;
    }

    /**
     * Login directly with given parameter and returns the access_token which is formatted as JWT.
     *
     * @param customerUrl CostumerUrl of taxadvisor
     * @param clientNumber Number of client or null if login of taxadvisor
     * @param userName UserName
     * @param password Password
     * @return JWT as String
     */
    public String login(String customerUrl, String clientNumber, String userName, String password) {
        Client client = ClientBuilder.newClient();

        String loginName;
        if (clientNumber == null || clientNumber.length() == 0) {
            loginName = String.format("%s\\%s", customerUrl, userName);
        } else {
            loginName = String.format("%s\\%s\\%s", customerUrl, clientNumber, userName);
        }

        MultivaluedMap<String, String> formData = new MultivaluedHashMap<String, String>();
        formData.add("grant_type", "password");
        formData.add("username", loginName);
        formData.add("password", password);
        formData.add("scope", "oneclick");

        OAuthToken response = client.target(buildTokenUrl(oneClickUrl))
            .request()
            .header("Authorization", "Basic " + clientSecret)
            .post(Entity.form(formData), OAuthToken.class);


        return response.getAccessToken();
    }

    /**
     * Call the OneClick-Endpoint to retrieve additional data of current member.
     * @param token Access token
     * @return Member related to the provided token
     */
    public Member retrieveMember(String token) {
        Client client = ClientBuilder.newClient();

        Member response = client.target(buildUserSessionMeUrl(oneClickUrl))
            .request()
            .header("Authorization", "Bearer " + token)
            .get(Member.class);

        return response;
    }


    // -- Private Methods -----------------------------------------------------------------------------------------------------------------------------------

    private static String  buildTokenUrl(String systemUrl) {
        String systemBaseUrl = String.format(BASE_URL, systemUrl);
        return systemBaseUrl + ENDPOINT_TOKEN_OAUTH;
    }

    private static String buildUserSessionMeUrl(String systemUrl) {
        String systemBaseUrl = String.format(BASE_URL, systemUrl);
        return systemBaseUrl + ENDPOINT_USER_SESSION_ME_NEW;
    }
}
