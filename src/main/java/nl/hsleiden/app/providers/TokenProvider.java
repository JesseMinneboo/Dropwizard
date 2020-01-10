package nl.hsleiden.app.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TokenProvider {
    public static final String ISSUER = "IIPSEN2-APP";
    private static final String KEY_ALGORITHM = "RSA";
    private static final int KEY_SIZE = 1048;
    private static final String PUBLIC_KEY = "PUBLIC_KEY";
    private static final String PRIVATE_KEY = "PRIVATE_KEY";

    private Algorithm algorithm;
    private RSAPrivateKey privateKey;
    private RSAPublicKey publicKey;


    public TokenProvider() {
        Map<String, Object> keys = generateKeyPair();
        this.privateKey = (RSAPrivateKey) Objects.requireNonNull(keys).get(PRIVATE_KEY);
        this.publicKey = (RSAPublicKey) Objects.requireNonNull(keys).get(PUBLIC_KEY);
        this.algorithm = Algorithm.RSA256(this.publicKey, this.privateKey);
    }

    public static Map<String, Object> generateKeyPair() {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            keyPairGen.initialize(KEY_SIZE);
            KeyPair keyPair = keyPairGen.generateKeyPair();

            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

            Map<String, Object> keyMap = new HashMap<>(2);

            keyMap.put(PUBLIC_KEY, publicKey);
            keyMap.put(PRIVATE_KEY, privateKey);

            return keyMap;
        } catch (Exception e) {
            System.err.println("Could not generate keypair...");
        }

        return null;
    }

    public String generateToken(long id) {
        try {
            return JWT.create()
                    .withClaim("user_id", id)
                    .withIssuer(ISSUER)
                    .sign(getAlgorithm());
        } catch (JWTCreationException e) {
            System.err.println("Could not generate token");
        }

        return null;
    }

    public boolean verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(getAlgorithm())
                    .withIssuer(ISSUER)
                    .build();

            verifier.verify(token);

            return true;
        } catch (JWTVerificationException e) {
            System.err.println("Could not verify token");
            return false;
        }
    }

    public DecodedJWT getDecodedJWT(String token) {
        try {
            return JWT.decode(token);
        } catch (JWTDecodeException e) {
            System.err.println("Could not decode token");
        }

        return null;
    }

    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }

    public RSAPublicKey getPublicKey() {
        return publicKey;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }
}
