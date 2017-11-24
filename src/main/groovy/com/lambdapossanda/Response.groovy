package com.lambdapossanda

import groovy.json.JsonOutput
import groovy.transform.CompileStatic
import groovy.transform.builder.Builder

@Builder
@CompileStatic
class Response {

    Object body

    String toJson() {
        return JsonOutput.prettyPrint(JsonOutput.toJson(body))
    }
}
