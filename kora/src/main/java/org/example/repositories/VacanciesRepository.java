package org.example.repositories;

import org.example.entities.EntityProfession;
import org.example.entities.EntityVacancy;
import ru.tinkoff.kora.common.Component;
import ru.tinkoff.kora.database.common.annotation.Query;
import ru.tinkoff.kora.database.common.annotation.Repository;
import ru.tinkoff.kora.database.jdbc.JdbcRepository;

import java.util.List;

@Component
@Repository
public interface VacanciesRepository extends JdbcRepository {
    @Query("INSERT INTO vacancies(name) VALUES (:name) RETURNING id")
    Integer insertVacancy(String name);

    @Query("INSERT INTO vacancies(name, salary) VALUES (:name, :salary) RETURNING id")
    Integer insertVacancyWithSalary(String name, Integer salary);
    @Query("TRUNCATE vacancy_skill, vacancies, skills, profession, vacancies_profession")
    void clear();

    @Query("INSERT INTO skills(skill) VALUES (:skill) RETURNING id")
    Integer insertSkill(String skill);

    @Query("INSERT INTO vacancy_skill(vacancy_id, skill_id) VALUES (:vacancyId, :skillId)")
    void addRelationSkill(Integer vacancyId, Integer skillId);

    @Query("INSERT INTO profession(id, name) VALUES (:id, :name) on conflict (id) do nothing")
    void insertProfession(Integer id, String name);

    @Query("INSERT INTO vacancies_profession(vacancy_id, profession_id) VALUES (:vacancyId, :professionId)")
    void addRelationProfession(Integer vacancyId, Integer professionId);

    @Query("SELECT p.name FROM profession p JOIN vacancies_profession vp ON p.id = vp.profession_id WHERE vp.profession_id <> 40 GROUP BY p.name ORDER BY COUNT(*) DESC LIMIT 10")
    List<EntityProfession> getTopProfession();

    @Query("SELECT name, salary FROM vacancies WHERE name LIKE :regexp")
    List<EntityVacancy> findByVacancy(String regexp);
}
