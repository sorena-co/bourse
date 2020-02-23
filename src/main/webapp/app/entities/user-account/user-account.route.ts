import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { UserAccount } from 'app/shared/model/user-account.model';
import { UserAccountService } from './user-account.service';
import { UserAccountComponent } from './user-account.component';
import { UserAccountDetailComponent } from './user-account-detail.component';
import { UserAccountUpdateComponent } from './user-account-update.component';
import { IUserAccount } from 'app/shared/model/user-account.model';

@Injectable({ providedIn: 'root' })
export class UserAccountResolve implements Resolve<IUserAccount> {
  constructor(private service: UserAccountService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUserAccount> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((userAccount: HttpResponse<UserAccount>) => userAccount.body));
    }
    return of(new UserAccount());
  }
}

export const userAccountRoute: Routes = [
  {
    path: '',
    component: UserAccountComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'bApp.userAccount.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: UserAccountDetailComponent,
    resolve: {
      userAccount: UserAccountResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'bApp.userAccount.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: UserAccountUpdateComponent,
    resolve: {
      userAccount: UserAccountResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'bApp.userAccount.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: UserAccountUpdateComponent,
    resolve: {
      userAccount: UserAccountResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'bApp.userAccount.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
