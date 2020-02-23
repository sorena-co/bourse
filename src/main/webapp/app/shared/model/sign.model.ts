export interface ISign {
  id?: number;
  instrumentId?: number;
  instrumentIsin?: string;
  instrumentName?: string;
}

export class Sign implements ISign {
  constructor(public id?: number, public instrumentId?: number, public instrumentIsin?: string, public instrumentName?: string) {}
}
