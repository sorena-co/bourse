import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IOrderRequest, OrderRequest } from 'app/shared/model/order-request.model';
import { OrderRequestService } from './order-request.service';
import { ISign } from 'app/shared/model/sign.model';
import { SignService } from 'app/entities/sign/sign.service';
import { IUserAccount } from 'app/shared/model/user-account.model';
import { UserAccountService } from 'app/entities/user-account/user-account.service';

@Component({
  selector: 'jhi-order-request-update',
  templateUrl: './order-request-update.component.html'
})
export class OrderRequestUpdateComponent implements OnInit {
  isSaving: boolean;

  signs: ISign[];

  useraccounts: IUserAccount[];

  editForm = this.fb.group({
    id: [],
    customerId: [],
    customerTitle: [],
    orderSide: [],
    orderSideId: [],
    price: [],
    quantity: [],
    value: [],
    validityDate: [],
    minimumQuantity: [],
    disclosedQuantity: [],
    validityType: [],
    bankAccountId: [],
    expectedRemainingQuantity: [],
    tradedQuantity: [],
    categoryId: [],
    remainingQuantity: [],
    orderExecuterId: [],
    signId: [],
    userAccountId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected orderRequestService: OrderRequestService,
    protected signService: SignService,
    protected userAccountService: UserAccountService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ orderRequest }) => {
      this.updateForm(orderRequest);
    });
    this.signService
      .query()
      .subscribe((res: HttpResponse<ISign[]>) => (this.signs = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.userAccountService
      .query()
      .subscribe(
        (res: HttpResponse<IUserAccount[]>) => (this.useraccounts = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(orderRequest: IOrderRequest) {
    this.editForm.patchValue({
      id: orderRequest.id,
      customerId: orderRequest.customerId,
      customerTitle: orderRequest.customerTitle,
      orderSide: orderRequest.orderSide,
      orderSideId: orderRequest.orderSideId,
      price: orderRequest.price,
      quantity: orderRequest.quantity,
      value: orderRequest.value,
      validityDate: orderRequest.validityDate,
      minimumQuantity: orderRequest.minimumQuantity,
      disclosedQuantity: orderRequest.disclosedQuantity,
      validityType: orderRequest.validityType,
      bankAccountId: orderRequest.bankAccountId,
      expectedRemainingQuantity: orderRequest.expectedRemainingQuantity,
      tradedQuantity: orderRequest.tradedQuantity,
      categoryId: orderRequest.categoryId,
      remainingQuantity: orderRequest.remainingQuantity,
      orderExecuterId: orderRequest.orderExecuterId,
      signId: orderRequest.signId,
      userAccountId: orderRequest.userAccountId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const orderRequest = this.createFromForm();
    if (orderRequest.id !== undefined) {
      this.subscribeToSaveResponse(this.orderRequestService.update(orderRequest));
    } else {
      this.subscribeToSaveResponse(this.orderRequestService.create(orderRequest));
    }
  }

  private createFromForm(): IOrderRequest {
    return {
      ...new OrderRequest(),
      id: this.editForm.get(['id']).value,
      customerId: this.editForm.get(['customerId']).value,
      customerTitle: this.editForm.get(['customerTitle']).value,
      orderSide: this.editForm.get(['orderSide']).value,
      orderSideId: this.editForm.get(['orderSideId']).value,
      price: this.editForm.get(['price']).value,
      quantity: this.editForm.get(['quantity']).value,
      value: this.editForm.get(['value']).value,
      validityDate: this.editForm.get(['validityDate']).value,
      minimumQuantity: this.editForm.get(['minimumQuantity']).value,
      disclosedQuantity: this.editForm.get(['disclosedQuantity']).value,
      validityType: this.editForm.get(['validityType']).value,
      bankAccountId: this.editForm.get(['bankAccountId']).value,
      expectedRemainingQuantity: this.editForm.get(['expectedRemainingQuantity']).value,
      tradedQuantity: this.editForm.get(['tradedQuantity']).value,
      categoryId: this.editForm.get(['categoryId']).value,
      remainingQuantity: this.editForm.get(['remainingQuantity']).value,
      orderExecuterId: this.editForm.get(['orderExecuterId']).value,
      signId: this.editForm.get(['signId']).value,
      userAccountId: this.editForm.get(['userAccountId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrderRequest>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackSignById(index: number, item: ISign) {
    return item.id;
  }

  trackUserAccountById(index: number, item: IUserAccount) {
    return item.id;
  }
}
