package org.example.httpClient;


import org.example.dto.ResponseHH;
import ru.tinkoff.kora.common.Component;
import ru.tinkoff.kora.http.client.common.annotation.HttpClient;
import ru.tinkoff.kora.http.common.HttpMethod;
import ru.tinkoff.kora.http.common.annotation.HttpRoute;
import ru.tinkoff.kora.http.common.annotation.Query;
import ru.tinkoff.kora.json.common.annotation.Json;

@Component
@HttpClient
public interface clientHeadHunter {
    @HttpRoute(method = HttpMethod.GET, path = "/vacancies")
    @Json
    ResponseHH getVacancies(@Query("page") int page);
}
