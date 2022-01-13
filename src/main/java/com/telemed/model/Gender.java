package com.telemed.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum Gender {
    @JsonProperty("MALE") MALE,
    @JsonProperty("FEMALE") FEMALE
}