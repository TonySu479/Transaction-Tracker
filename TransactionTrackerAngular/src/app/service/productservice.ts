import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../model/product';
import {ProductDTO} from "../model/productDTO";

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

    getProducts(name= "") {
        if(name != ""){
            return this.http.get<any>(this.baseUrl + `?name=${name}`);
        }
        return this.http.get<any>(this.baseUrl);
    }

    create(product: Product){
        return this.http.post<Product>(this.baseUrl, product);
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

    inventoryCheck(products: Product[]) {
        return this.http.post<ProductDTO[]>(`${this.baseUrl}/inventory-check`, products)
    }

    updateQuantities(products: Product[]) {
        return this.http.post<any>(`${this.baseUrl}/inventory-check/update-quantities`, products)
    }
}
