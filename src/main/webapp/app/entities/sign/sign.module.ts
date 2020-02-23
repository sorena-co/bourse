import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BSharedModule } from 'app/shared/shared.module';
import { SignComponent } from './sign.component';
import { SignDetailComponent } from './sign-detail.component';
import { SignUpdateComponent } from './sign-update.component';
import { SignDeleteDialogComponent } from './sign-delete-dialog.component';
import { signRoute } from './sign.route';

@NgModule({
  imports: [BSharedModule, RouterModule.forChild(signRoute)],
  declarations: [SignComponent, SignDetailComponent, SignUpdateComponent, SignDeleteDialogComponent],
  entryComponents: [SignDeleteDialogComponent]
})
export class BSignModule {}
