import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {ProductCategory} from "../api/product-category";

@Injectable()
export class ProductCategoryService {
    baseUrl = "http://localhost:8080/api/product-categories";

    constructor(private http: HttpClient) { }

    getProductCategories(name= "") {
        if(name != ""){
            return this.http.get<any>(this.baseUrl + `?name=${name}`);
        }
        return this.http.get<any>(this.baseUrl);
    }

    create(productCategory: ProductCategory){
        return this.http.post<ProductCategory>(this.baseUrl, productCategory);
    }

    delete(productCategory: ProductCategory){
        return this.http.delete<ProductCategory>(`${this.baseUrl}/${productCategory.id}`);
    }

    update(productCategory: ProductCategory){
        return this.http.put<ProductCategory>(`${this.baseUrl}/${productCategory.id}`, productCategory);
    }

    deleteProductCategories(listOfIds : String[]) {
        return this.http.post<ProductCategory>(`${this.baseUrl}/delete-products`, listOfIds);
    }

}
