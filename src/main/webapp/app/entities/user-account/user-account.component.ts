import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IUserAccount } from 'app/shared/model/user-account.model';
import { UserAccountService } from './user-account.service';
import { UserAccountDeleteDialogComponent } from './user-account-delete-dialog.component';

@Component({
  selector: 'jhi-user-account',
  templateUrl: './user-account.component.html'
})
export class UserAccountComponent implements OnInit, OnDestroy {
  userAccounts: IUserAccount[];
  eventSubscriber: Subscription;

  constructor(
    protected userAccountService: UserAccountService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll() {
    this.userAccountService.query().subscribe((res: HttpResponse<IUserAccount[]>) => {
      this.userAccounts = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInUserAccounts();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IUserAccount) {
    return item.id;
  }

  registerChangeInUserAccounts() {
    this.eventSubscriber = this.eventManager.subscribe('userAccountListModification', () => this.loadAll());
  }

  delete(userAccount: IUserAccount) {
    const modalRef = this.modalService.open(UserAccountDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.userAccount = userAccount;
  }
}
