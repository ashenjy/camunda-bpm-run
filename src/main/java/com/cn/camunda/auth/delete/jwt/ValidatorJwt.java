//package com.cn.camunda.security.config.jwt;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.cn.camunda.security.config.jwt.groovy.AbstractValidatorJwt;
//import com.cn.camunda.security.config.jwt.groovy.ValidatorResultJwt;
//import org.apache.commons.io.IOUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.camunda.bpm.engine.ProcessEngine;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.InputStream;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.Date;
//import java.util.List;
//
//import static com.cn.camunda.security.config.jwt.CamundaAuthService.authExists;
//
//public class ValidatorJwt extends AbstractValidatorJwt {
//
//    private static final Logger LOG = LoggerFactory.getLogger(ValidatorJwt.class);
//
//    private static String jwtSecret = "";
//
//    @Override
//    public ValidatorResultJwt validateJwt(HttpServletRequest request, ProcessEngine engine, String encodedCredentials, String jwtSecretPath) {
//        LOG.info("com.cn.camunda.security.config.jwt.ValidatorJwt.validateJwt()");
//
//        if (StringUtils.isEmpty(jwtSecret)) {
//            try {
//                InputStream inStream = Files.newInputStream(Paths.get(jwtSecretPath));
//                jwtSecret = IOUtils.toString(inStream, StandardCharsets.UTF_8);
//            } catch (Exception e) {
//                LOG.error("ERROR: Unable to load JWT Secret: ${e.getLocalizedMessage()}");
//                return ValidatorResultJwt.setValidatorResult(false, null, null, null);
//            }
//        }
//
//        LOG.info("ValidatorJwt.validateJwt() jwtSecretPath:" + jwtSecretPath);
//        LOG.info("ValidatorJwt.validateJwt() encodedCredentials: " + encodedCredentials);
//
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
//            JWTVerifier verifier = JWT.require(algorithm)
//                    .acceptNotBefore(new Date().getTime())
//                    .build();
//            DecodedJWT jwt = verifier.verify(encodedCredentials);
//
//            String username = jwt.getClaim("username").asString();
//            List<String> groupIds = jwt.getClaim("groupIds").asList(String.class);
//            List<String> tenantIds = jwt.getClaim("tenantIds").asList(String.class);
//
//            if (StringUtils.isEmpty(username)) {
//                LOG.error("BAD JWT: Missing username");
//                return ValidatorResultJwt.setValidatorResult(false, null, null, null);
//            }
//
////            /*TODO: mock verification - Stateless Static*/
//            if (!authExists(username, engine)) {
//                LOG.error("BAD JWT: Username doesn't Exist in Camunda");
//                return ValidatorResultJwt.setValidatorResult(false, null, null, null);
//            }
//
//            return ValidatorResultJwt.setValidatorResult(true, username, groupIds, tenantIds);
//
//        } catch (JWTVerificationException exception) {
//            LOG.error("BAD JWT: ${exception.getLocalizedMessage()}");
//            return ValidatorResultJwt.setValidatorResult(false, null, null, null);
//        }
//    }
//
//}
