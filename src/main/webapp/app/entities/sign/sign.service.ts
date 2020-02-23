import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISign } from 'app/shared/model/sign.model';

type EntityResponseType = HttpResponse<ISign>;
type EntityArrayResponseType = HttpResponse<ISign[]>;

@Injectable({ providedIn: 'root' })
export class SignService {
  public resourceUrl = SERVER_API_URL + 'api/signs';

  constructor(protected http: HttpClient) {}

  create(sign: ISign): Observable<EntityResponseType> {
    return this.http.post<ISign>(this.resourceUrl, sign, { observe: 'response' });
  }

  update(sign: ISign): Observable<EntityResponseType> {
    return this.http.put<ISign>(this.resourceUrl, sign, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISign>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISign[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
