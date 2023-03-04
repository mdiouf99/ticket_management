import dayjs from 'dayjs/esm';
import { IUser } from 'app/entities/user/user.model';
import { Status } from 'app/entities/enumerations/status.model';

export interface ITicket {
  id: number;
  libelle?: string | null;
  status?: Status | null;
  dateCreation?: dayjs.Dayjs | null;
  user?: Pick<IUser, 'id'> | null;
  assignea?: Pick<IUser, 'id'> | null;
}

export type NewTicket = Omit<ITicket, 'id'> & { id: null };
