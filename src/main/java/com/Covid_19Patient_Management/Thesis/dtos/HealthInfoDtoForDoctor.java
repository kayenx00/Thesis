package com.Covid_19Patient_Management.Thesis.dtos;

import java.util.Date;

public interface HealthInfoDtoForDoctor {
    Long getId();
    Long getPatient_id();
    String getPatient_name();
    String getDistrict();
    String getCity();
    int getBlood_pressure();
    int getOxygen_level();
    String getOther_diagnose();
    Date getLast_update();
}
