import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';

import { JwtModule, JwtHelperService } from '@auth0/angular-jwt';

import { LoginComponent } from './login/login.component';
import { SegurancaRoutingModule } from './seguranca-routing.module';


@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    CommonModule,
    SegurancaRoutingModule,
    InputTextModule,
    ButtonModule,
    FormsModule,

    JwtModule.forRoot({
      config: {
        tokenGetter: () => {
          return '';
        }
      }
    })
  ],
  providers: [JwtHelperService]
})
export class SegurancaModule { }
