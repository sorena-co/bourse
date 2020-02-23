import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BTestModule } from '../../../test.module';
import { OrderRequestDetailComponent } from 'app/entities/order-request/order-request-detail.component';
import { OrderRequest } from 'app/shared/model/order-request.model';

describe('Component Tests', () => {
  describe('OrderRequest Management Detail Component', () => {
    let comp: OrderRequestDetailComponent;
    let fixture: ComponentFixture<OrderRequestDetailComponent>;
    const route = ({ data: of({ orderRequest: new OrderRequest(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BTestModule],
        declarations: [OrderRequestDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(OrderRequestDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OrderRequestDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.orderRequest).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
