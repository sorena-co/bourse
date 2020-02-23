import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BTestModule } from '../../../test.module';
import { SignUpdateComponent } from 'app/entities/sign/sign-update.component';
import { SignService } from 'app/entities/sign/sign.service';
import { Sign } from 'app/shared/model/sign.model';

describe('Component Tests', () => {
  describe('Sign Management Update Component', () => {
    let comp: SignUpdateComponent;
    let fixture: ComponentFixture<SignUpdateComponent>;
    let service: SignService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BTestModule],
        declarations: [SignUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SignUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SignUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SignService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Sign(123);
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
        const entity = new Sign();
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
