import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISign } from 'app/shared/model/sign.model';

@Component({
  selector: 'jhi-sign-detail',
  templateUrl: './sign-detail.component.html'
})
export class SignDetailComponent implements OnInit {
  sign: ISign;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sign }) => {
      this.sign = sign;
    });
  }

  previousState() {
    window.history.back();
  }
}
