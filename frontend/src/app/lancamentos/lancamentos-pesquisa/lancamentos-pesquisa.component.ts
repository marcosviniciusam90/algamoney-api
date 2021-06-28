import { Component } from '@angular/core';
import { LancamentoFiltro, LancamentoService } from '../lancamento.service';

@Component({
  selector: 'app-lancamentos-pesquisa',
  templateUrl: './lancamentos-pesquisa.component.html',
  styleUrls: ['./lancamentos-pesquisa.component.css']
})
export class LancamentosPesquisaComponent {

    filtro = new LancamentoFiltro();

    lancamentos: any = [];
    totalRegistros = 0;

    constructor(private lancamentoService: LancamentoService) {}

    pesquisar(pagina = 0): void {
      this.filtro.pagina = pagina;

      this.lancamentoService.pesquisar(this.filtro)
        .then(response => {
          this.lancamentos = response.content;
          this.totalRegistros = response.totalElements;
        });
    }

    excluir(lancamento: any): void {
      this.lancamentoService.excluir(lancamento.codigo)
        .then(() => {
          alert(`Lançamento ${lancamento.codigo} excluído`);
          this.pesquisar();
        });
    }
}
