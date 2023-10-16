package com.scoot.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DistanceRequest {
    private double latitude1;
    private double longitude1;
    private double latitude2;
    private double longitude2;
}
