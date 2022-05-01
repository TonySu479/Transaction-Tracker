import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AppMainComponent } from './app.main.component';

@Component({
    selector: 'app-menu',
    template: `
        <div class="layout-menu-container">
            <ul class="layout-menu" role="menu" (keydown)="onKeydown($event)">
                <li app-menu class="layout-menuitem-category" *ngFor="let item of model; let i = index;" [item]="item" [index]="i" [root]="true" role="none">
                    <div class="layout-menuitem-root-text" [attr.aria-label]="item.label">{{item.label}}</div>
                    <ul role="menu">
                        <li app-menuitem *ngFor="let child of item.items" [item]="child" [index]="i" role="none"></li>
                    </ul>
                </li>

            </ul>
        </div>
    `
})
export class AppMenuComponent implements OnInit {

    model: any[];

    constructor(public appMain: AppMainComponent) { }

    ngOnInit() {
        this.model = [
            {
                label: 'Home',
                items:[
                    // {label: 'Dashboard',icon: 'pi pi-fw pi-home', routerLink: ['/']},
                    {label: 'Products', icon: 'pi pi-box', routerLink: ['/products']},
                    {label: 'Categories', icon: 'pi pi-key', routerLink: ['/product-categories']},
                    {label: 'Transactions', icon: 'pi pi-credit-card', routerLink: ['/transactions']},
                    {label: 'Inventory-Check', icon: 'pi pi-briefcase', routerLink: ['/inventory-check']},
                    {label: 'Inventory-check History', icon: 'pi pi-book', routerLink: ['/inventory-check-history']},
                ]
            }
        ];
    }

    onKeydown(event: KeyboardEvent) {
        const nodeElement = (<HTMLDivElement> event.target);
        if (event.code === 'Enter' || event.code === 'Space') {
            nodeElement.click();
            event.preventDefault();
        }
    }
}
