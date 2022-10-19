package com.cn.camunda.auth.delete.groovy

import groovy.transform.CompileStatic
import org.camunda.bpm.engine.ProcessEngine

import javax.servlet.http.HttpServletRequest

@CompileStatic
abstract class AbstractValidatorJwt {
// @TODO Add use of logger

    abstract ValidatorResultJwt validateJwt(HttpServletRequest request, ProcessEngine engine, String encodedCredentials, String jwtSecretPath)

}
