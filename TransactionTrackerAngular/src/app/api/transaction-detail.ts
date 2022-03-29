import {Transaction} from "./transaction";
import {Product} from "./product";

export interface TransactionDetail {
    id?: string;
    transaction?: Transaction;
    transactionId?: string;
    product?: Product;
    productId?: string;
    price?: number;
    quantity?: number;
}
