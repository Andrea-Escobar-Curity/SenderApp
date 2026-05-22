package com.example.senderapp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class RouteCatalog {

    private RouteCatalog() {
    }

    public static List<Route> getRoutes() {
        return Collections.unmodifiableList(Arrays.asList(
                new Route(
                        0,
                        "Ruta del Bosque",
                        "Longitud: 5.2 km",
                        "Sendero natural entre montañas y vegetación densa. "
                                + "Ideal para caminatas moderadas con vistas panorámicas.",
                        "Media",
                        "Bogotá",
                        5.2f,
                        "2 h 30 min",
                        "Lleva agua, bloqueador solar y calzado antideslizante.",
                        R.drawable.route_bosque,
                        4.5333,
                        -73.7833
                ),
                new Route(
                        1,
                        "Ruta del Lago",
                        "Longitud: 8.1 km",
                        "Recorrido costero alrededor del lago con zonas planas y tramos rocosos.",
                        "Media-Alta",
                        "Medellín",
                        8.1f,
                        "3 h 45 min",
                        "Recomendado iniciar temprano. Evitar días de lluvia intensa.",
                        R.drawable.route_lago,
                        6.2317,
                        -75.1589
                ),
                new Route(
                        2,
                        "Ruta de la Cumbre",
                        "Longitud: 12.4 km",
                        "Ascenso exigente hasta el mirador principal.",
                        "Alta",
                        "Manizales",
                        12.4f,
                        "5 h 15 min",
                        "Solo para senderistas con experiencia. Bastones y capa térmica.",
                        R.drawable.route_nevado,
                        5.0000,
                        -75.5000
                )
        ));
    }

    public static Route getById(int id) {
        for (Route route : getRoutes()) {
            if (route.getId() == id) {
                return route;
            }
        }
        return getRoutes().get(0);
    }
}
