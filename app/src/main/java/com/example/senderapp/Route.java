package com.example.senderapp;

public class Route {

    private final int id;
    private final String name;
    private final String length;
    private final String description;
    private final String difficulty;
    private final String location;
    private final float distanceKm;
    private final String duration;
    private final String recommendations;
    private final int imageResId;
    private final double latitude;
    private final double longitude;

    public Route(
            int id,
            String name,
            String length,
            String description,
            String difficulty,
            String location,
            float distanceKm,
            String duration,
            String recommendations,
            int imageResId,
            double latitude,
            double longitude) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.description = description;
        this.difficulty = difficulty;
        this.location = location;
        this.distanceKm = distanceKm;
        this.duration = duration;
        this.recommendations = recommendations;
        this.imageResId = imageResId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLength() {
        return length;
    }

    public String getDescription() {
        return description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getLocation() {
        return location;
    }

    public float getDistanceKm() {
        return distanceKm;
    }

    public String getDuration() {
        return duration;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public int getImageResId() {
        return imageResId;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
