package de.wk.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import de.wk.jwt.beans.Member;
import de.wk.jwt.util.TokenVerifier;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

class VerifierTest {

    private static final String secret = "secret";
    private static final String oneClickUrl = "portalbereich.de";

    private static final Issuer issuer = new Issuer(secret, oneClickUrl);
    private static final TokenVerifier verifier = new TokenVerifier(secret, oneClickUrl);

    @Test
    void passVerification() {
        Member member = createMember();
        String token = issuer.createToken(member);
        System.out.println(token);
        verifier.verify(token, member);
    }

    @Test
    void failVerificationWrongSecret() {
        final TokenVerifier invalidVerifier = new TokenVerifier("invalid-secret", oneClickUrl);

        Member member = createMember();
        String token = issuer.createToken(member);

        assertThrows(JWTVerificationException.class, () -> invalidVerifier.verify(token, member));
    }

    @Test
    void failVerificationWrongLoginName() {
        Member member = createMember();
        String token = issuer.createToken(member);
        member.setLoginName("invalid-testlogin");

        assertThrows(JWTVerificationException.class, () -> verifier.verify(token, member));
    }


    private static Member createMember() {
        Member member = new Member();
        member.setMemberId(UUID.randomUUID().toString());
        member.setOrganizationId(UUID.randomUUID().toString());
        member.setLoginName("testlogin");
        return member;
    }

}