import { AccountType } from 'app/shared/model/enumerations/account-type.model';

export interface IUserAccount {
  id?: number;
  firstName?: string;
  lastName?: string;
  accountType?: AccountType;
  username?: string;
  password?: string;
  active?: boolean;
}

export class UserAccount implements IUserAccount {
  constructor(
    public id?: number,
    public firstName?: string,
    public lastName?: string,
    public accountType?: AccountType,
    public username?: string,
    public password?: string,
    public active?: boolean
  ) {
    this.active = this.active || false;
  }
}
