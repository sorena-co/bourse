import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { OrderRequestService } from 'app/entities/order-request/order-request.service';
import { IOrderRequest, OrderRequest } from 'app/shared/model/order-request.model';

describe('Service Tests', () => {
  describe('OrderRequest Service', () => {
    let injector: TestBed;
    let service: OrderRequestService;
    let httpMock: HttpTestingController;
    let elemDefault: IOrderRequest;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(OrderRequestService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new OrderRequest(0, 0, 'AAAAAAA', 'AAAAAAA', 0, 0, 0, 0, 'AAAAAAA', 0, 0, 0, 0, 0, 0, 'AAAAAAA', 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a OrderRequest', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new OrderRequest(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a OrderRequest', () => {
        const returnedFromService = Object.assign(
          {
            customerId: 1,
            customerTitle: 'BBBBBB',
            orderSide: 'BBBBBB',
            orderSideId: 1,
            price: 1,
            quantity: 1,
            value: 1,
            validityDate: 'BBBBBB',
            minimumQuantity: 1,
            disclosedQuantity: 1,
            validityType: 1,
            bankAccountId: 1,
            expectedRemainingQuantity: 1,
            tradedQuantity: 1,
            categoryId: 'BBBBBB',
            remainingQuantity: 1,
            orderExecuterId: 1
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of OrderRequest', () => {
        const returnedFromService = Object.assign(
          {
            customerId: 1,
            customerTitle: 'BBBBBB',
            orderSide: 'BBBBBB',
            orderSideId: 1,
            price: 1,
            quantity: 1,
            value: 1,
            validityDate: 'BBBBBB',
            minimumQuantity: 1,
            disclosedQuantity: 1,
            validityType: 1,
            bankAccountId: 1,
            expectedRemainingQuantity: 1,
            tradedQuantity: 1,
            categoryId: 'BBBBBB',
            remainingQuantity: 1,
            orderExecuterId: 1
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a OrderRequest', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
