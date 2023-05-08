package org.example.dto;

import ru.tinkoff.kora.json.common.annotation.Json;

import javax.annotation.Nullable;
import java.util.List;

@Json
public record ResponseVacancy(String name, @Nullable Snippet snippet, @Nullable Salary salary, @Nullable List<ProfessionalRole> professional_roles){

    @Json
    public record Snippet(@Nullable String responsibility, @Nullable String requirement){};

    @Json
    public record Salary(@Nullable Integer from, @Nullable Integer to, @Nullable String currency){};

    @Json
    public record ProfessionalRole(@Nullable String id, @Nullable String name){};


}
