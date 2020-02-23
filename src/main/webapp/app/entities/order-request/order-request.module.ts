import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BSharedModule } from 'app/shared/shared.module';
import { OrderRequestComponent } from './order-request.component';
import { OrderRequestDetailComponent } from './order-request-detail.component';
import { OrderRequestUpdateComponent } from './order-request-update.component';
import { OrderRequestDeleteDialogComponent } from './order-request-delete-dialog.component';
import { orderRequestRoute } from './order-request.route';

@NgModule({
  imports: [BSharedModule, RouterModule.forChild(orderRequestRoute)],
  declarations: [OrderRequestComponent, OrderRequestDetailComponent, OrderRequestUpdateComponent, OrderRequestDeleteDialogComponent],
  entryComponents: [OrderRequestDeleteDialogComponent]
})
export class BOrderRequestModule {}
