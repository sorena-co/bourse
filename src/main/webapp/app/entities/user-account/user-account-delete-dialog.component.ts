import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUserAccount } from 'app/shared/model/user-account.model';
import { UserAccountService } from './user-account.service';

@Component({
  templateUrl: './user-account-delete-dialog.component.html'
})
export class UserAccountDeleteDialogComponent {
  userAccount: IUserAccount;

  constructor(
    protected userAccountService: UserAccountService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.userAccountService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'userAccountListModification',
        content: 'Deleted an userAccount'
      });
      this.activeModal.dismiss(true);
    });
  }
}
