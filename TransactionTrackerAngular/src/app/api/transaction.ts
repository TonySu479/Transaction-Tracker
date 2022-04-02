import {TransactionType} from "./transaction-type.enum";

export interface Transaction {
    id?: string;
    createdAt?: string;
    transactionType?: TransactionType;
}
