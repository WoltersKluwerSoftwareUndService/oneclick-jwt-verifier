package de.wk.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import de.wk.jwt.beans.Member;
import de.wk.jwt.util.OneClickClient;
import de.wk.jwt.util.TokenVerifier;

public class Main {

    public static void main(String[] args) {

        String jwtSecretParam = "oneclick_jwt_secret";
        String jwtSecret = System.getenv(jwtSecretParam);
        if (jwtSecret == null || jwtSecret.length() == 0) {
            System.err.println("Provide the jwt secret as environment variable: " + jwtSecretParam);
            return;
        }

        String clientSecretParam = "oneclick_client_secret";
        String clientSecret = System.getenv(clientSecretParam);
        if (clientSecret == null || clientSecret.length() == 0) {
            System.err.println("Provide the client secret as environment variable: " + clientSecretParam);
            return;
        }

        if (args == null || (args.length != 4 && args.length != 5)) {
            System.err.println("Call with 4 parameters: <OneClickUrl> <CustomerUrl> <User> <Password>");
            System.err.println("Call with 5 parameters: <OneClickUrl> <CustomerUrl> <ClientNumber> <User> <Password>");
            return;
        }


        String oneClickUrl  = args[0];
        String customerUrl  = args[1];
        String clientNumber = args.length != 4 ? args[2] : "";
        String user         = args.length != 4 ? args[3] : args[2];
        String password     = args.length != 4 ? args[4] : args[3];

        System.out.println("\n#### Input ####");
        System.out.println("OneClickUrl        : " + oneClickUrl);
        System.out.println("CustomerUrl        : " + customerUrl);
        System.out.println("ClientNumber       : " + clientNumber);
        System.out.println("User               : " + user);
        System.out.println("Password-Length    : " + password.length());
        System.out.println("Client-Sec.-Length : " + clientSecret.length());
        System.out.println("JWT-Secret-Length  : " + jwtSecret.length());

        OneClickClient client = new OneClickClient(oneClickUrl, clientSecret);
        String token = client.login(customerUrl, clientNumber, user, password);
        System.out.println("\n#### Token ####");
        System.out.println(token);

        Member member = client.retrieveMember(token);
        System.out.println("\n#### Member ####");
        System.out.println(member);


        try {
            System.out.println("\n#### Verification ####");
            TokenVerifier verifier = new TokenVerifier(clientSecret, oneClickUrl);
            verifier.verify(token, member);
            System.out.println("Valid jwt!");
        } catch (JWTVerificationException e) {
            System.err.println("Invalid jwt! Reason: " + e.getMessage());
        }
    }

}
