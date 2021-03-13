package com.example.profile;

public class DevProfile implements SystemProfile {
    public static final String DEV_PROFILE_MSG = "Current profile is dev";

    @Override
    public String getProfile() {
        return DEV_PROFILE_MSG;
    }
}
