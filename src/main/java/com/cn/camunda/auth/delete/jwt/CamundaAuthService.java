package com.cn.camunda.auth.delete.jwt;

import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.authorization.Authorization;
import org.camunda.bpm.engine.authorization.Permissions;
import org.camunda.bpm.engine.authorization.Resource;
import org.camunda.bpm.engine.authorization.Resources;

import java.util.Arrays;

public class CamundaAuthService {

    protected static void addAuthorization(String userId, AuthorizationService authorizationService) {
        Arrays.stream(Resources.values()).forEach(resource -> {
            boolean authzExists = authExists(userId, authorizationService, Authorization.AUTH_TYPE_GRANT, Permissions.ALL, resource, "*");
            if (!authzExists) {
                Authorization authz = authorizationService.createNewAuthorization(Authorization.AUTH_TYPE_GRANT);
                authz.setUserId(userId);
                authz.addPermission(Permissions.ALL);
                authz.setResource(resource);
                authz.setResourceId("*");
                authorizationService.saveAuthorization(authz);
            }
        });
    }

    protected static boolean authExists(String userId, ProcessEngine engine) {
        Authorization auth = engine.getAuthorizationService().createAuthorizationQuery()
                .userIdIn(userId)
                .singleResult();

        return auth != null;
    }

    protected static boolean authExists(String userId, AuthorizationService authorizationService, int authzType, Permissions permissions, Resource resource, String resourceId) {
        Authorization authz = authorizationService.createAuthorizationQuery()
                .userIdIn(userId)
                .authorizationType(authzType)
                .hasPermission(permissions)
                .resourceType(resource)
                .resourceId(resourceId)
                .singleResult();

        return authz != null;
    }
}
