import dayjs from 'dayjs/esm';

import { Status } from 'app/entities/enumerations/status.model';

import { ITicket, NewTicket } from './ticket.model';

export const sampleWithRequiredData: ITicket = {
  id: 65582,
};

export const sampleWithPartialData: ITicket = {
  id: 93545,
  libelle: 'Small',
};

export const sampleWithFullData: ITicket = {
  id: 9940,
  libelle: 'capacitor Rubber deposit',
  status: Status['EN_COURS'],
  dateCreation: dayjs('2023-02-15'),
};

export const sampleWithNewData: NewTicket = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
