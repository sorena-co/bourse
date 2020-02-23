import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { OrderRequest } from 'app/shared/model/order-request.model';
import { OrderRequestService } from './order-request.service';
import { OrderRequestComponent } from './order-request.component';
import { OrderRequestDetailComponent } from './order-request-detail.component';
import { OrderRequestUpdateComponent } from './order-request-update.component';
import { IOrderRequest } from 'app/shared/model/order-request.model';

@Injectable({ providedIn: 'root' })
export class OrderRequestResolve implements Resolve<IOrderRequest> {
  constructor(private service: OrderRequestService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOrderRequest> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((orderRequest: HttpResponse<OrderRequest>) => orderRequest.body));
    }
    return of(new OrderRequest());
  }
}

export const orderRequestRoute: Routes = [
  {
    path: '',
    component: OrderRequestComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'bApp.orderRequest.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OrderRequestDetailComponent,
    resolve: {
      orderRequest: OrderRequestResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'bApp.orderRequest.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OrderRequestUpdateComponent,
    resolve: {
      orderRequest: OrderRequestResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'bApp.orderRequest.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OrderRequestUpdateComponent,
    resolve: {
      orderRequest: OrderRequestResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'bApp.orderRequest.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
