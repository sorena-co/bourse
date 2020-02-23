import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'user-account',
        loadChildren: () => import('./user-account/user-account.module').then(m => m.BUserAccountModule)
      },
      {
        path: 'sign',
        loadChildren: () => import('./sign/sign.module').then(m => m.BSignModule)
      },
      {
        path: 'order-request',
        loadChildren: () => import('./order-request/order-request.module').then(m => m.BOrderRequestModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class BEntityModule {}
