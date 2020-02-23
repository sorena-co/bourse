import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BTestModule } from '../../../test.module';
import { SignDetailComponent } from 'app/entities/sign/sign-detail.component';
import { Sign } from 'app/shared/model/sign.model';

describe('Component Tests', () => {
  describe('Sign Management Detail Component', () => {
    let comp: SignDetailComponent;
    let fixture: ComponentFixture<SignDetailComponent>;
    const route = ({ data: of({ sign: new Sign(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BTestModule],
        declarations: [SignDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SignDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SignDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sign).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
