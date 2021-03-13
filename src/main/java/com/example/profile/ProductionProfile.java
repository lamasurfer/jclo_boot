package com.example.profile;

public class ProductionProfile implements SystemProfile {
    public static final String PROD_PROFILE_MSG = "Current profile is production";

    @Override
    public String getProfile() {
        return PROD_PROFILE_MSG;
    }
}
