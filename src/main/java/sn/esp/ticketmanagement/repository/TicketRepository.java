package sn.esp.ticketmanagement.repository;

import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import sn.esp.ticketmanagement.domain.Ticket;

/**
 * Spring Data JPA repository for the Ticket entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("select ticket from Ticket ticket where ticket.user.login = ?#{principal.preferredUsername}")
    List<Ticket> findByUserIsCurrentUser();

    @Query("select ticket from Ticket ticket where ticket.assignea.login = ?#{principal.preferredUsername}")
    List<Ticket> findByAssigneaIsCurrentUser();

    @Query("select ticket from Ticket ticket where ticket.status <> 'CLOTURE'")
    List<Ticket> findByActiveTickets();
}
