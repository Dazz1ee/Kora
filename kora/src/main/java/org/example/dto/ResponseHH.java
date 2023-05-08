package org.example.dto;

import ru.tinkoff.kora.json.common.annotation.Json;

import java.util.List;

@Json
public record ResponseHH(List<ResponseVacancy> items){
}
