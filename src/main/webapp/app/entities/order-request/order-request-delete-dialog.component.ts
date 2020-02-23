import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOrderRequest } from 'app/shared/model/order-request.model';
import { OrderRequestService } from './order-request.service';

@Component({
  templateUrl: './order-request-delete-dialog.component.html'
})
export class OrderRequestDeleteDialogComponent {
  orderRequest: IOrderRequest;

  constructor(
    protected orderRequestService: OrderRequestService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.orderRequestService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'orderRequestListModification',
        content: 'Deleted an orderRequest'
      });
      this.activeModal.dismiss(true);
    });
  }
}
