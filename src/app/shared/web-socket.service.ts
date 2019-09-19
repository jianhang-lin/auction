import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable()
export class WebSocketService {

  ws: WebSocket;

  constructor() {}

  createObservalbeSocket(url: string, id: number): Observable<any> {
    this.ws = new WebSocket(url);
    return new Observable<string>(
      observer => {
        this.ws.onmessage = (event) => observer.next(event.data);
        this.ws.onerror = (event) => observer.next(String(event));
        this.ws.onclose = (event) => observer.complete();
        this.ws.onopen = (event) => this.sendMessage({productId: id});
      }
    ).pipe(map(message => JSON.parse(message)));
  }

  sendMessage(message: any) {
    this.ws.send(JSON.stringify(message));
  }
}
