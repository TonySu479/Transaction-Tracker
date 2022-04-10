import {TransactionDetail} from "./transaction-detail";

export interface TransactionDetailsListDTO {
    transactionDetailsDTOS?: TransactionDetail[];
    shiftId: string;
}
