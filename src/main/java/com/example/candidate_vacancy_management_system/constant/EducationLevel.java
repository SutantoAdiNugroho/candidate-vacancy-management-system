package com.example.candidate_vacancy_management_system.constant;

public enum EducationLevel {
    SD("SD"),
    SMP("SMP"),
    SMA("SMA"),
    SARJANA("SARJANA"),
    MASTER("MASTER"),
    DOKTOR("DOKTOR");

    private final String level;

    EducationLevel(String level) {
        this.level = level;
    }

    public static boolean isValidEducationLevel(String level) {
        for (EducationLevel eLevel : EducationLevel.values()) {
            if (eLevel.level.equals(level))
                return true;
        }
        return false;
    }
}
