import { LOCALE_ID, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ToastModule} from 'primeng/toast';
import { MessageService } from 'primeng/api';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {ConfirmationService} from 'primeng/api';
import { RouterModule } from '@angular/router';

import { registerLocaleData } from '@angular/common';
import localePt from '@angular/common/locales/pt';

import { NavbarComponent } from './navbar/navbar.component';
import { LancamentoService } from '../lancamentos/lancamento.service';
import { PessoaService } from '../pessoas/pessoa.service';
import { ErrorHandlerService } from './error-handler.service';
import { CategoriaService } from '../categorias/categoria.service';
import { PaginaNaoEncontradaComponent } from './pagina-nao-encontrada.component';

registerLocaleData(localePt);

@NgModule({
  declarations: [
      NavbarComponent,
      PaginaNaoEncontradaComponent
  ],
  imports: [
    CommonModule,
    ToastModule,
    ConfirmDialogModule,
    RouterModule
  ],
  exports: [
      NavbarComponent,
      ToastModule,
      ConfirmDialogModule
  ],
  providers: [
    { provide: LOCALE_ID, useValue: 'pt-BR' },

    MessageService,
    ConfirmationService,
    LancamentoService,
    PessoaService,
    ErrorHandlerService,
    CategoriaService
  ]
})
export class CoreModule { }
