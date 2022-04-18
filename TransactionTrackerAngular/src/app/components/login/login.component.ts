import { Component, OnInit, OnDestroy } from '@angular/core';
import { ConfigService } from '../../service/app.config.service';
import { AppConfig } from '../../api/appconfig';
import { Subscription } from 'rxjs';
import { TokenStorageService } from 'src/app/service/token-storage.service';
import { AuthService } from 'src/app/service/auth.service';
import { Router } from '@angular/router';
import {FormBuilder, FormControl, Validators} from "@angular/forms";
import { MessageService} from "primeng/api";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss'],
    providers: [MessageService]
})
export class LoginComponent implements OnInit, OnDestroy {

    isLoggedIn = false;
    isLoginFailed = false;
    errorMessage = '';
    roles: string[] = [];
    valCheck: string[] = ['remember'];

    loginForm;

    config: AppConfig;

    subscription: Subscription;

    constructor(
        public configService: ConfigService,
        private authService: AuthService,
        private tokenStorage: TokenStorageService,
        private router: Router,
        private formBuilder: FormBuilder,
        private messageService: MessageService
    ) {}

    get username(){
        return this.loginForm.controls.username;
    }

    get password() {
        return this.loginForm.controls.password;
    }

    ngOnInit(): void {
        this.config = this.configService.config;
        this.subscription = this.configService.configUpdate$.subscribe(
            (config) => {
                this.config = config;
            }
        );

        if (this.tokenStorage.getToken()) {
            this.isLoggedIn = true;
            this.roles = this.tokenStorage.getUser().roles;
        }

        this.loginForm = this.formBuilder.group({
            username: new FormControl("", [Validators.required]),
            password: new FormControl("", [Validators.required])
        });
    }

    ngOnDestroy(): void {
        if (this.subscription) {
            this.subscription.unsubscribe();
        }
    }

    submit(): void {
        if (!this.loginForm.valid) {
            this.loginForm.markAllAsTouched();
            return;
        }
        this.authService.login(this.loginForm.value.username, this.loginForm.value.password).subscribe(
            (data) => {
                this.tokenStorage.saveToken(data.token);
                this.tokenStorage.saveUser(data);
                console.log(data);
                this.isLoginFailed = false;
                this.isLoggedIn = true;
                this.roles = this.tokenStorage.getUser().roles;

                if(this.roles.indexOf("ROLE_ADMIN") > - 1) {
                    this.router.navigate(["/products"]);
                } else {
                    this.router.navigate(["/cashier"]);
                }
            },
            (err) => {
                this.messageService.add({key: 'loginFailToast',severity:'error', summary:'Login error', detail:'invalid username or password'});
                this.errorMessage = err.error?.message;
                this.isLoginFailed = true;
            }
        );
    }

    register(): void {
        this.router.navigate(["/register"]);
    }

}
