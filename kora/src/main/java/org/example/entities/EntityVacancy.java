package org.example.entities;

import ru.tinkoff.kora.json.common.annotation.Json;

import javax.annotation.Nullable;

@Json
public record EntityVacancy(String name, @Nullable Integer salary) {
}
