import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOrderRequest } from 'app/shared/model/order-request.model';

@Component({
  selector: 'jhi-order-request-detail',
  templateUrl: './order-request-detail.component.html'
})
export class OrderRequestDetailComponent implements OnInit {
  orderRequest: IOrderRequest;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ orderRequest }) => {
      this.orderRequest = orderRequest;
    });
  }

  previousState() {
    window.history.back();
  }
}
