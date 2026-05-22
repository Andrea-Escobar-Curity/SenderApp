package com.example.senderapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.List;

public final class RouteListHelper {

    private RouteListHelper() {
    }

    public static void bindRoutes(Context context, LinearLayout container, List<Route> routes) {
        container.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(context);
        for (Route route : routes) {
            View card = inflater.inflate(R.layout.item_route_card, container, false);
            TextView routeName = card.findViewById(R.id.routeName);
            TextView routeLength = card.findViewById(R.id.routeLength);
            ImageView routeThumb = card.findViewById(R.id.routeThumb);
            MaterialButton btnViewDetail = card.findViewById(R.id.btnViewDetail);

            routeName.setText(route.getName());
            routeLength.setText(route.getLocation() + " · " + route.getLength());
            routeThumb.setImageResource(route.getImageResId());

            btnViewDetail.setOnClickListener(v -> {
                Intent intent = new Intent(context, RouteDetailActivity.class);
                intent.putExtra(RouteDetailActivity.EXTRA_ROUTE_ID, route.getId());
                context.startActivity(intent);
            });

            container.addView(card);
        }
    }
}
