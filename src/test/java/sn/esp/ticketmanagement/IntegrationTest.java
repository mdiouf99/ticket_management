package sn.esp.ticketmanagement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import sn.esp.ticketmanagement.TicketmanagementApp;
import sn.esp.ticketmanagement.config.AsyncSyncConfiguration;
import sn.esp.ticketmanagement.config.EmbeddedElasticsearch;
import sn.esp.ticketmanagement.config.EmbeddedSQL;
import sn.esp.ticketmanagement.config.TestSecurityConfiguration;

/**
 * Base composite annotation for integration tests.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = { TicketmanagementApp.class, AsyncSyncConfiguration.class, TestSecurityConfiguration.class })
@EmbeddedElasticsearch
@EmbeddedSQL
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public @interface IntegrationTest {
}
