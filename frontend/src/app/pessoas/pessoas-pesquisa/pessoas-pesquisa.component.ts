import { Component } from '@angular/core';
import { PessoaFiltro, PessoaService } from '../pessoa.service';

@Component({
  selector: 'app-pessoas-pesquisa',
  templateUrl: './pessoas-pesquisa.component.html',
  styleUrls: ['./pessoas-pesquisa.component.css']
})
export class PessoasPesquisaComponent {

    filtro = new PessoaFiltro();

    pessoas: any = [];
    totalRegistros = 0;

    constructor(private pessoaService: PessoaService) {}

    pesquisar(pagina = 0): void {
      this.filtro.pagina = pagina;

      this.pessoaService.pesquisar(this.filtro)
        .then(response => {
          this.pessoas = response.content;
          this.totalRegistros = response.totalElements;
        });
    }

}
