import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from 'src/app/seguranca/auth.service';
import { ErrorHandlerService } from '../error-handler.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

    exibindoMenu = false;

    constructor(
      public authService: AuthService,
      private router: Router,
      private errorHandlerService: ErrorHandlerService
    ) {}

    logout(): void {
      this.authService.logout()
        .then(() => {
          this.router.navigate(['/login']);
        })
        .catch(erro => this.errorHandlerService.handle(erro));
    }

}
