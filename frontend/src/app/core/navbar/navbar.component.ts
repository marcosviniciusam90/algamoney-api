import { AfterViewInit, Component } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from 'src/app/seguranca/auth.service';
import { ErrorHandlerService } from '../error-handler.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements AfterViewInit {

    exibindoMenu = false;

    constructor(
      public authService: AuthService,
      private router: Router,
      private errorHandlerService: ErrorHandlerService
    ) {}

    ngAfterViewInit(): void {
        this.clickLinksDoMenuEscondeMenu();
        this.clickForaDoMenuEscondeMenu();
    }

    logout(): void {
      this.authService.logout()
        .then(() => {
          this.router.navigate(['/login']);
        })
        .catch(erro => this.errorHandlerService.handle(erro));
    }

    private clickLinksDoMenuEscondeMenu(): void {
      const links = document.querySelectorAll('.navbar-menuitem a');
      links.forEach(link => {
        link.addEventListener('click', () => { this.exibindoMenu = false; });
      });
    }

    private clickForaDoMenuEscondeMenu(): void {
      const menu = document.getElementsByClassName('navbar-menu')[0];
      const iconeMenu = document.getElementsByClassName('pi pi-bars')[0];

      window.addEventListener('click', evt => {

        if (evt.target !== iconeMenu && !menu.contains((evt.target as any))) {
          this.exibindoMenu = false;
        }

      });
    }

}
