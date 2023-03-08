package sn.esp.ticketmanagement.repository.search;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import java.util.stream.Stream;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sn.esp.ticketmanagement.domain.Ticket;
import sn.esp.ticketmanagement.repository.TicketRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Ticket} entity.
 */
public interface TicketSearchRepository extends ElasticsearchRepository<Ticket, Long>, TicketSearchRepositoryInternal {}

interface TicketSearchRepositoryInternal {
    Stream<Ticket> search(String query);

    Stream<Ticket> search(Query query);

    void index(Ticket entity);
}

class TicketSearchRepositoryInternalImpl implements TicketSearchRepositoryInternal {

    private final ElasticsearchRestTemplate elasticsearchTemplate;
    private final TicketRepository repository;

    TicketSearchRepositoryInternalImpl(ElasticsearchRestTemplate elasticsearchTemplate, TicketRepository repository) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<Ticket> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return search(nativeSearchQuery);
    }

    @Override
    public Stream<Ticket> search(Query query) {
        return elasticsearchTemplate.search(query, Ticket.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(Ticket entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }
}
