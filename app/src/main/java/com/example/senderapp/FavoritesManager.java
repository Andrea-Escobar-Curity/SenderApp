package com.example.senderapp;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class FavoritesManager {

    private static final String PREFS = "senderapp_prefs";
    private static final String KEY_FAVORITES = "favorite_route_ids";

    private FavoritesManager() {
    }

    public static boolean isFavorite(Context context, int routeId) {
        return getFavoriteIds(context).contains(routeId);
    }

    public static void toggleFavorite(Context context, int routeId) {
        Set<String> ids = new HashSet<>(getPrefs(context).getStringSet(KEY_FAVORITES, new HashSet<>()));
        String key = String.valueOf(routeId);
        if (ids.contains(key)) {
            ids.remove(key);
        } else {
            ids.add(key);
        }
        getPrefs(context).edit().putStringSet(KEY_FAVORITES, ids).apply();
    }

    public static List<Route> getFavoriteRoutes(Context context) {
        List<Route> favorites = new ArrayList<>();
        for (Route route : RouteCatalog.getRoutes()) {
            if (isFavorite(context, route.getId())) {
                favorites.add(route);
            }
        }
        return favorites;
    }

    private static Set<Integer> getFavoriteIds(Context context) {
        Set<Integer> result = new HashSet<>();
        for (String id : getPrefs(context).getStringSet(KEY_FAVORITES, new HashSet<>())) {
            result.add(Integer.parseInt(id));
        }
        return result;
    }

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }
}
