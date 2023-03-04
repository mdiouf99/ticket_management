package sn.esp.ticketmanagement.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import sn.esp.ticketmanagement.web.rest.TestUtil;

class TicketTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ticket.class);
        Ticket ticket1 = new Ticket();
        ticket1.setId(1L);
        Ticket ticket2 = new Ticket();
        ticket2.setId(ticket1.getId());
        assertThat(ticket1).isEqualTo(ticket2);
        ticket2.setId(2L);
        assertThat(ticket1).isNotEqualTo(ticket2);
        ticket1.setId(null);
        assertThat(ticket1).isNotEqualTo(ticket2);
    }
}
