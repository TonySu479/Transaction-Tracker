import {Component, OnInit} from '@angular/core';
import {AppConfig} from "../../api/appconfig";
import {Subscription} from "rxjs";
import {ConfigService} from "../../service/app.config.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../service/auth.service";
import {Router} from "@angular/router";
import {ConfirmationService, MessageService} from "primeng/api";
import {DialogService} from "primeng/dynamicdialog";

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.scss'],
    providers: [MessageService]
})

export class RegisterComponent implements OnInit {

    constructor(private configService: ConfigService,
                private authService: AuthService,
                private router: Router,
                private messageService: MessageService,
                private formBuilder: FormBuilder) {
    }

    get username() {
        return this.registerForm.controls.username;
    }

    get email() {
        return this.registerForm.controls.email;
    }

    get password() {
        return this.registerForm.controls.password;
    }


    registerForm: FormGroup;

    isLoggedIn = false;
    isLoginFailed = false;
    errorMessage = '';
    roles: string[] = [];
    valCheck: string[] = ['remember'];
    showPassword: boolean = false;
    config: AppConfig;

    subscription: Subscription;

    ngOnInit(): void {
        this.config = this.configService.config;
        this.subscription = this.configService.configUpdate$.subscribe(config => {
            this.config = config;
        });

        this.registerForm = this.formBuilder.group({
            username: new FormControl("", [Validators.required]),
            email: new FormControl("", [Validators.required]),
            password: new FormControl("", [Validators.required])
        });
    }

    submit() {
        if (!this.registerForm.valid) {
            this.registerForm.markAllAsTouched();
            return;
        }
        this.authService.register(this.registerForm.value.username, this.registerForm.value.email, this.registerForm.value.password).subscribe(() => {
            this.router.navigate(["/login"]);
            this.messageService.add({
                severity: "success",
                summary: "user registered",
                detail: `You have successfully registered`
            })
        });
    }

    togglePasswordVisibility() {
        this.showPassword = !this.showPassword;
    }

    login() {
        this.router.navigate(["/login"]);
    }


}
