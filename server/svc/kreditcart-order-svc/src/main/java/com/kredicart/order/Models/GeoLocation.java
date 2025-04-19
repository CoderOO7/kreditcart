package com.kredicart.order.Models;

import jakarta.persistence.Embeddable;

@Embeddable
public class GeoLocation {
    private Double latitude;
    private Double longitude;

    public GeoLocation() {}

    public GeoLocation(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}