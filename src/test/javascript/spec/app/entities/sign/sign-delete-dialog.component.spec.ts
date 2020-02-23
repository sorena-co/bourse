import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { BTestModule } from '../../../test.module';
import { SignDeleteDialogComponent } from 'app/entities/sign/sign-delete-dialog.component';
import { SignService } from 'app/entities/sign/sign.service';

describe('Component Tests', () => {
  describe('Sign Management Delete Component', () => {
    let comp: SignDeleteDialogComponent;
    let fixture: ComponentFixture<SignDeleteDialogComponent>;
    let service: SignService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BTestModule],
        declarations: [SignDeleteDialogComponent]
      })
        .overrideTemplate(SignDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SignDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SignService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
