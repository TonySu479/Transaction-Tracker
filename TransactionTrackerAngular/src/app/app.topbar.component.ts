import { Component, OnDestroy } from '@angular/core';
import { AppMainComponent } from './app.main.component';
import { Subscription } from 'rxjs';
import { MenuItem } from 'primeng/api';
import { Router } from '@angular/router';

@Component({
    selector: 'app-topbar',
    templateUrl: './app.topbar.component.html',
})
export class AppTopBarComponent {
    items: MenuItem[];

    constructor(public appMain: AppMainComponent, private router: Router) {}

    logout() {
        window.sessionStorage.clear();
        setTimeout(() => {
            this.router.navigate(['/login']);
        }, 100);
    }
}
