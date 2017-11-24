package com.lambdapossanda

import com.google.common.collect.ImmutableList

import static com.lambdapossanda.Handler.getAirportData

class AutoComplete {

    private static final List<Airport> DATA = getAirportData()

    static List<Airport> getData(String query) {
        Set<Airport> matched = new HashSet<>()

        for (Airport airport : DATA) {
            String airportName = airport.getName().toLowerCase()
            if (airportName.startsWith(query.toLowerCase())) {
                matched.add(airport)
            }
        }

        return ImmutableList.copyOf(matched)
    }

    static void main(String[] args) {

        Scanner scanner = new Scanner(System.in)
        System.out.println("Start typing airport name")
        System.out.println(getData(scanner.nextLine()))

    }
}
