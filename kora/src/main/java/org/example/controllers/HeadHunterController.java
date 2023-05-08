package org.example.controllers;


import org.example.entities.EntityProfession;
import org.example.entities.EntityVacancy;
import org.example.service.VacancyService;
import ru.tinkoff.kora.common.Component;

import ru.tinkoff.kora.http.common.HttpMethod;
import ru.tinkoff.kora.http.common.annotation.HttpRoute;

import ru.tinkoff.kora.http.common.annotation.Path;
import ru.tinkoff.kora.http.server.common.annotation.HttpController;
import ru.tinkoff.kora.json.common.annotation.Json;

import java.util.List;


@Component
@HttpController
public final class HeadHunterController {
    public record HelloWorldResponse(String greeting) {}
    private VacancyService vacancyService;

    public HeadHunterController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @HttpRoute(method = HttpMethod.GET, path = "/topTen")

    @Json
    public List<EntityProfession> getTopProfession() {
        return vacancyService.getTopProfession();
    }

    @HttpRoute(method = HttpMethod.GET, path = "/find/{name}")

    @Json
    public List<EntityVacancy> findVacancy(@Path("name") String name) {
        return vacancyService.findByName(name);
    }
}