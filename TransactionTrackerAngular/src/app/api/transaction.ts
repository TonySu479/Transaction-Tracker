import {Product} from "./product";

export interface Transaction {
    id?: string;
    name?: string;
    date?: string;
    products?: Product[];
}
