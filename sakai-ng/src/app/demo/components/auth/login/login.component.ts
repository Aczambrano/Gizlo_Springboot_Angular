import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LayoutService } from 'src/app/layout/service/app.layout.service';
import { AuthService } from '../services/auth.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styles: [
        `
            :host ::ng-deep .pi-eye,
            :host ::ng-deep .pi-eye-slash {
                transform: scale(1.6);
                margin-right: 1rem;
                color: var(--primary-color) !important;
            }
        `,
    ],
})
export class LoginComponent implements OnInit {
    private _formLogin: FormGroup = new FormGroup({});

    constructor(
        public layoutService: LayoutService,
        private _router: Router,
        private _frmBuilder: FormBuilder,
        private _loginService: AuthService
    ) {}

    ngOnInit() {
        this._formLogin = this._frmBuilder.group({
            usuario: this._frmBuilder.control('mmorantedos', [Validators.required]),
            clave: this._frmBuilder.control('Co1@ntrasena', [Validators.required]),
        });
    }

    public get getFormLogin(): FormGroup {
        return this._formLogin;
    }

    clickLogin() {
        // TODO VALIDAR EL FORMULARIO DEL LOGIN
        console.log(this._formLogin.value)
        if (this._formLogin.valid) {
            // LLAMAR AL SERVICIO QUE VA A COMUNICAR AL API
            this._loginService.login(this._formLogin.value).subscribe(
                (res) =>{
                    // GUARDAR EN EL LOCAL STORAGE 
                    console.table(res.data.token);
                    this._loginService.saveToken(res.data.token);
                    this._router.navigateByUrl('/dashboard');
                }
            )
        } else {
            alert("Ingres los datos");
            // MoSTRAR MENSAJE DE ERROR
        }
    }
}
