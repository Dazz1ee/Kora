package org.example.service;

import org.example.httpClient.clientHeadHunter;
import org.example.dto.ResponseHH;
import org.example.dto.ResponseVacancy;
import org.example.entities.EntityProfession;
import org.example.entities.EntityVacancy;
import org.example.repositories.VacanciesRepository;
import ru.tinkoff.kora.common.Component;
import ru.tinkoff.kora.json.common.JsonWriter;


import ru.tinkoff.kora.scheduling.quartz.ScheduleWithCron;

import java.util.List;

@Component
public class VacancyService {
    private final clientHeadHunter clientHeadHunter;
    private final VacanciesRepository vacanciesRepository;

    public VacancyService(clientHeadHunter clientHeadHunter, VacanciesRepository vacanciesRepository, JsonWriter<ResponseVacancy> jsonWriter) {
        this.clientHeadHunter = clientHeadHunter;
        this.vacanciesRepository = vacanciesRepository;
    }

    @ScheduleWithCron(value = "0 45 21 ? * *")
    public void reloadVacancies() {
        vacanciesRepository.clear();

        int page = 0;
        ResponseHH response = clientHeadHunter.getVacancies(page);

        do {
            for (ResponseVacancy vacancy : response.items()) {
                Integer vacancyId;
                if (vacancy.salary() != null) {
                    vacancyId = vacanciesRepository.insertVacancyWithSalary(vacancy.name(), average(vacancy.salary().from(), vacancy.salary().to()));
                } else {
                    vacancyId = vacanciesRepository.insertVacancy(vacancy.name());
                }

                if (vacancy.snippet() != null && vacancy.snippet().requirement() != null) {
                    Integer skillId = vacanciesRepository.insertSkill(vacancy.snippet().requirement());
                    vacanciesRepository.addRelationSkill(vacancyId, skillId);
                }

                if (vacancy.professional_roles() != null && vacancy.professional_roles().size() != 0) {
                    vacanciesRepository.insertProfession(Integer.parseInt(vacancy.professional_roles().get(0).id()), vacancy.professional_roles().get(0).name());
                    Integer professionId = Integer.parseInt(vacancy.professional_roles().get(0).id());
                    vacanciesRepository.addRelationProfession(vacancyId, professionId);

                }
            }

            page++;
            response = clientHeadHunter.getVacancies(page);

        } while (response.items().size() == 20);
    }

    public List<EntityProfession> getTopProfession(){
        return vacanciesRepository.getTopProfession();
    }

    public List<EntityVacancy> findByName(String name){
        return vacanciesRepository.findByVacancy("%" + name + "%");
    }

    public Integer average(Integer from, Integer to) {
        int sum = 0;

        if (from != null) {
            sum += from;
        }

        if(to != null) {
            sum += to;
            sum /= 2;
        }

        return sum;
    }
}
