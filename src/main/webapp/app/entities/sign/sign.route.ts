import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Sign } from 'app/shared/model/sign.model';
import { SignService } from './sign.service';
import { SignComponent } from './sign.component';
import { SignDetailComponent } from './sign-detail.component';
import { SignUpdateComponent } from './sign-update.component';
import { ISign } from 'app/shared/model/sign.model';

@Injectable({ providedIn: 'root' })
export class SignResolve implements Resolve<ISign> {
  constructor(private service: SignService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISign> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((sign: HttpResponse<Sign>) => sign.body));
    }
    return of(new Sign());
  }
}

export const signRoute: Routes = [
  {
    path: '',
    component: SignComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'bApp.sign.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SignDetailComponent,
    resolve: {
      sign: SignResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'bApp.sign.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SignUpdateComponent,
    resolve: {
      sign: SignResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'bApp.sign.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SignUpdateComponent,
    resolve: {
      sign: SignResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'bApp.sign.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
