import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOrderRequest } from 'app/shared/model/order-request.model';

type EntityResponseType = HttpResponse<IOrderRequest>;
type EntityArrayResponseType = HttpResponse<IOrderRequest[]>;

@Injectable({ providedIn: 'root' })
export class OrderRequestService {
  public resourceUrl = SERVER_API_URL + 'api/order-requests';

  constructor(protected http: HttpClient) {}

  create(orderRequest: IOrderRequest): Observable<EntityResponseType> {
    return this.http.post<IOrderRequest>(this.resourceUrl, orderRequest, { observe: 'response' });
  }

  update(orderRequest: IOrderRequest): Observable<EntityResponseType> {
    return this.http.put<IOrderRequest>(this.resourceUrl, orderRequest, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOrderRequest>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOrderRequest[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  sendRequest(id: number): Observable<EntityResponseType> {
    return this.http.get<IOrderRequest>(`${this.resourceUrl}/${id}/start`, { observe: 'response' });
  }
}
