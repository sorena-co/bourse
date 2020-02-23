import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BTestModule } from '../../../test.module';
import { OrderRequestUpdateComponent } from 'app/entities/order-request/order-request-update.component';
import { OrderRequestService } from 'app/entities/order-request/order-request.service';
import { OrderRequest } from 'app/shared/model/order-request.model';

describe('Component Tests', () => {
  describe('OrderRequest Management Update Component', () => {
    let comp: OrderRequestUpdateComponent;
    let fixture: ComponentFixture<OrderRequestUpdateComponent>;
    let service: OrderRequestService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BTestModule],
        declarations: [OrderRequestUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(OrderRequestUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OrderRequestUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrderRequestService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OrderRequest(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new OrderRequest();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
