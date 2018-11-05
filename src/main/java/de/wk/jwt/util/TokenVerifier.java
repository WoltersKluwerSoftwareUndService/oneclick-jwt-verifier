package de.wk.jwt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import de.wk.jwt.beans.Member;

public class TokenVerifier {

    private final String secret;
    private final String oneClickUrl;

    /**
     * Create TokenVerifier with given secret and OneClickUrl
     * @param secret Secret to verify tokens
     * @param oneClickUrl Needed to ensure the right issuer
     */
    public TokenVerifier(String secret, String oneClickUrl) {
        this.secret = secret;
        this.oneClickUrl = oneClickUrl;
    }

    /**
     * Verifies the given token and member with each other. Member is used to verify the MemberId, MemberName and OrganizationId as examples.
     * @param token Token in JWT-Format
     * @param member Member related to the token
     */
    public void verify(String token, Member member) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier =
            JWT.require(algorithm)
                .withIssuer("https://services." + oneClickUrl + "/servicehosts/identityserver")
                .withSubject(member.getMemberId())
                .withClaim("http://claims.addison.de/memberguid", member.getMemberId())
                .withClaim("http://claims.addison.de/membername", member.getLoginName())
                .withClaim("http://claims.addison.de/organizationguid", member.getOrganizationId())
                .build(); //Reusable verifier instance
        verifier.verify(token);
    }
}
