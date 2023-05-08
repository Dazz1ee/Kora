package org.example;

import ru.tinkoff.kora.common.KoraApp;
import ru.tinkoff.kora.config.common.ConfigModule;
import ru.tinkoff.kora.database.jdbc.JdbcDatabaseModule;
import ru.tinkoff.kora.http.client.jdk.JdkHttpClientModule;
import ru.tinkoff.kora.http.server.undertow.UndertowHttpServerModule;
import ru.tinkoff.kora.json.module.JsonModule;
import ru.tinkoff.kora.micrometer.module.MetricsModule;
import ru.tinkoff.kora.scheduling.quartz.QuartzModule;

@KoraApp
public interface Application extends
        ConfigModule,
        MetricsModule,
        JsonModule,
        ru.tinkoff.kora.scheduling.jdk.SchedulingJdkModule,
        QuartzModule,
        UndertowHttpServerModule,
        JdbcDatabaseModule,
        JdkHttpClientModule
        {
}