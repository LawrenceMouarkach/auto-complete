package com.lambdapossanda

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.fasterxml.jackson.databind.MappingIterator
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import com.google.common.collect.ImmutableList
import com.google.common.io.Resources
import org.apache.log4j.Logger

import java.nio.charset.StandardCharsets

class Handler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private static final Logger LOG = Logger.getLogger(Handler.class)


    static List<Airport> getAirportData() {
        List<Airport> airports = new ArrayList<>()
        try {
            airports = ImmutableList.copyOf(read(Airport.class, "airport-codes.csv"))
        } catch (IOException e) {
            e.printStackTrace()
        }
        return airports
    }

    private static <T> List<T> read(Class<T> type, String file) throws IOException {
        CsvMapper mapper = new CsvMapper()
        String json = Resources.toString(Resources.getResource(file), StandardCharsets.UTF_8)
        CsvSchema schema = CsvSchema.emptySchema().withHeader()
        MappingIterator<T> it = mapper.readerFor(type).with(schema)
                .readValues(json)
        return it.readAll()
    }

    @Override
    ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        LOG.info("received: " + input)

        Map<String, String> queryParams = input.get("queryStringParameters") as Map<String, String>

        Response responseBody = Response.builder()
                .body(AutoComplete.getData(queryParams.get("query")))
                .build()

        return ApiGatewayResponse.builder()
                .statusCode(200)
                .body(responseBody.toJson())
                .headers(['Access-Control-Allow-Origin': '*'])
                .build()
    }
}
