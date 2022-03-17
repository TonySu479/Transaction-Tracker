import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../api/product';

@Injectable()
export class ProductService {

    baseUrl = "http://localhost:8080/api/products";

    constructor(private http: HttpClient) { }

    getProductsSmall() {
        return this.http.get<any>('assets/demo/data/products-small.json')
        .toPromise()
        .then(res => res.data as Product[])
        .then(data => data);
    }

    getProducts() {
        return this.http.get<any>(this.baseUrl)
    }

    create(product: Product){
        return this.http.post<Product>("http://localhost:8080/api/products", product);
    }

    delete(product: Product){
        return this.http.delete<Product>(`${this.baseUrl}/${product.id}`);
    }

    update(product: Product){
        return this.http.put<Product>(`${this.baseUrl}/${product.id}`, product);
    }

    deleteProducts(listOfIds : String[]) {
        return this.http.post<Product>(`${this.baseUrl}/delete-products`, listOfIds);
    }

    getProductsMixed() {
        return this.http.get<any>('assets/demo/data/products-mixed.json')
        .toPromise()
        .then(res => res.data as Product[])
        .then(data => data);
    }

    getProductsWithOrdersSmall() {
        return this.http.get<any>('assets/demo/data/products-orders-small.json')
        .toPromise()
        .then(res => res.data as Product[])
        .then(data => data);
    }

}
