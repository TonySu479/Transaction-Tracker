import {Product} from "./product";
import {InventoryCheck} from "./inventory-check";

export interface InventoryCheckDetail {
    id?: string;
    inventoryCheck?: InventoryCheck
    productId?: string;
    product?: Product;
    trackedQuantity?: number;
    inputtedQuantity?: number;
}
