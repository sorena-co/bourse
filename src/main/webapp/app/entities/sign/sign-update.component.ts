import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ISign, Sign } from 'app/shared/model/sign.model';
import { SignService } from './sign.service';

@Component({
  selector: 'jhi-sign-update',
  templateUrl: './sign-update.component.html'
})
export class SignUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    instrumentId: [],
    instrumentIsin: [],
    instrumentName: []
  });

  constructor(protected signService: SignService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ sign }) => {
      this.updateForm(sign);
    });
  }

  updateForm(sign: ISign) {
    this.editForm.patchValue({
      id: sign.id,
      instrumentId: sign.instrumentId,
      instrumentIsin: sign.instrumentIsin,
      instrumentName: sign.instrumentName
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const sign = this.createFromForm();
    if (sign.id !== undefined) {
      this.subscribeToSaveResponse(this.signService.update(sign));
    } else {
      this.subscribeToSaveResponse(this.signService.create(sign));
    }
  }

  private createFromForm(): ISign {
    return {
      ...new Sign(),
      id: this.editForm.get(['id']).value,
      instrumentId: this.editForm.get(['instrumentId']).value,
      instrumentIsin: this.editForm.get(['instrumentIsin']).value,
      instrumentName: this.editForm.get(['instrumentName']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISign>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
