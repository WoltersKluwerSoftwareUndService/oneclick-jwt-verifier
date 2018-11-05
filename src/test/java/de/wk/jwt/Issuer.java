package de.wk.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import de.wk.jwt.beans.Member;

import java.util.UUID;

public class Issuer {

    private final String secret;
    private final String oneClickUrl;

    public Issuer(String secret, String oneClickUrl) {
        this.secret = secret;
        this.oneClickUrl = oneClickUrl;
    }

    public String createToken(Member member) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
            .withIssuer("https://services." + oneClickUrl + "/servicehosts/identityserver")
            .withSubject(member.getMemberId())
            .withClaim("http://claims.addison.de/memberguid", member.getMemberId())
            .withClaim("http://claims.addison.de/membername", member.getLoginName())
            .withClaim("http://claims.addison.de/organizationguid", member.getOrganizationId())
            .withJWTId(UUID.randomUUID().toString())
            .sign(algorithm);
    }
}
