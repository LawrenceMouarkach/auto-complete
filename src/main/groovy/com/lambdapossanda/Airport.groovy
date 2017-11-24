package com.lambdapossanda

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical

@Canonical
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(["ident", "type", "coordinates", "elevation_ft",
        "continent", "iso_country", "iso_region", "municipality", "gps_code", "local_code"])
class Airport {

    String name
    @JsonProperty("iata_code")
    String iata
}
