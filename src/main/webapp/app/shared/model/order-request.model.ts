export interface IOrderRequest {
  id?: number;
  customerId?: number;
  customerTitle?: string;
  orderSide?: string;
  orderSideId?: number;
  price?: number;
  quantity?: number;
  value?: number;
  validityDate?: string;
  minimumQuantity?: number;
  disclosedQuantity?: number;
  validityType?: number;
  bankAccountId?: number;
  expectedRemainingQuantity?: number;
  tradedQuantity?: number;
  categoryId?: string;
  remainingQuantity?: number;
  orderExecuterId?: number;
  signInstrumentName?: string;
  signId?: number;
  userAccountUsername?: string;
  userAccountId?: number;
}

export class OrderRequest implements IOrderRequest {
  constructor(
    public id?: number,
    public customerId?: number,
    public customerTitle?: string,
    public orderSide?: string,
    public orderSideId?: number,
    public price?: number,
    public quantity?: number,
    public value?: number,
    public validityDate?: string,
    public minimumQuantity?: number,
    public disclosedQuantity?: number,
    public validityType?: number,
    public bankAccountId?: number,
    public expectedRemainingQuantity?: number,
    public tradedQuantity?: number,
    public categoryId?: string,
    public remainingQuantity?: number,
    public orderExecuterId?: number,
    public signInstrumentName?: string,
    public signId?: number,
    public userAccountUsername?: string,
    public userAccountId?: number
  ) {}
}
