import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { TicketService } from '../service/ticket.service';

import { TicketComponent } from './ticket.component';

describe('Ticket Management Component', () => {
  let comp: TicketComponent;
  let fixture: ComponentFixture<TicketComponent>;
  let service: TicketService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'ticket', component: TicketComponent }]), HttpClientTestingModule],
      declarations: [TicketComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            data: of({
              defaultSort: 'id,asc',
            }),
            queryParamMap: of(
              jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              })
            ),
            snapshot: { queryParams: {} },
          },
        },
      ],
    })
      .overrideTemplate(TicketComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(TicketComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(TicketService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 123 }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.tickets?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to ticketService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getTicketIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getTicketIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
