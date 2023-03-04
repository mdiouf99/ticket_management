import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITicket } from '../ticket.model';
import { TicketService } from '../service/ticket.service';

@Injectable({ providedIn: 'root' })
export class TicketRoutingResolveService implements Resolve<ITicket | null> {
  constructor(protected service: TicketService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITicket | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((ticket: HttpResponse<ITicket>) => {
          if (ticket.body) {
            return of(ticket.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(null);
  }
}
