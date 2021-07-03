import { Component } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  login(usuario: string, senha: string): void {
    console.log(`Usuário: ${usuario} - Senha: ${senha}`);
  }
}
