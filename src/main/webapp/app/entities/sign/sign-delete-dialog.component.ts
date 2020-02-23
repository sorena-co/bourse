import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISign } from 'app/shared/model/sign.model';
import { SignService } from './sign.service';

@Component({
  templateUrl: './sign-delete-dialog.component.html'
})
export class SignDeleteDialogComponent {
  sign: ISign;

  constructor(protected signService: SignService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.signService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'signListModification',
        content: 'Deleted an sign'
      });
      this.activeModal.dismiss(true);
    });
  }
}
