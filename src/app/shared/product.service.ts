import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient ) { }

  getAllCategories(): string[] {
    return ['电子产品', '硬件设备', '软件设备', '图书'];
  }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.baseUrl + '/api/products').pipe(
      tap(_ => this.log(`fetched products`)),
      catchError(this.handleError<Product[]>('getProducts', []))
    );
  }

  getProduct(id: number): Observable<Product> {
    const url = `${this.baseUrl}/api/product/${id}`;
    return this.http.get<Product>(url).pipe(
      tap(_ => this.log(`fetched Product id = ${id}`)),
      catchError(this.handleError<Product>(`getProduct id = ${id}`))
    );
  }

  getCommentsForProductId(id: number): Observable<Comment[]> {
    const url = `${this.baseUrl}/api/product/${id}/comments`;
    return this.http.get<Comment[]>(url).pipe(
      tap(_ => this.log(`fetched Comments by ProductId = ${id}`)),
      catchError(this.handleError<Comment[]>(`getCommentsForProductId id = ${id}`, []))
    );
  }

  private log(message: string) {
    console.log(`ProductService: ${message}`);
  }
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

  search(params: ProductSearchParams): Observable<Product[]> {
    const httpParmas = new HttpParams();
    Object.keys(params).filter(key => params[key]).forEach(key => {
      httpParmas.set(key, params[key]);
    });
    return this.http.get<Product[]>(this.baseUrl + '/api/products', {params: httpParmas}).pipe(
      tap(_ => this.log(`fetched products`)),
      catchError(this.handleError<Product[]>('getProducts', []))
    );
  }

  private encodeParams(params: ProductSearchParams) {
    return Object.keys(params).filter(key => params[key]).reduce((sum: URLSearchParams, key: string) => {
      sum.append(key, params[key]);
      return sum;
    }, new URLSearchParams());
  }
}
export class Product {

  constructor(
    public id: number,
    public title: string,
    public price: number,
    public rating: number,
    public desc: string,
    public categories: Array<string>
  ) {}
}
export class Comment {
  constructor(
    public id: number,
    public productId: number,
    public timestamp: string,
    public user: string,
    public rating: number,
    public content: string
  ) {}
}
export class ProductSearchParams {
  constructor(public title: string,
              public price: number,
              public category: string) {

  }

}
