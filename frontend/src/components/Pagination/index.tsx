import { LancamentoPage } from "types/lancamento";

type Props = {
  page: LancamentoPage;
  onPageChange: Function; //comunicação entre componentes, um componente roda função de outro
}

//O componente Pagination está desacoplado do componente DataTable, portanto 
//o Pagination pode ser utilizado com outros componentes que usam a mesma lógica

const Pagination = ({ page, onPageChange }: Props) => {

  return (
    <div className="row d-flex justify-content-center">
      <nav>
        <ul className="pagination">
          <li className={`page-item ${page.first ? 'disabled' : ''}`}>
            <button className="page-link" onClick={() => onPageChange(page.number - 1)}>Anterior</button>
          </li>
          <li className="page-item disabled">
            <span className="page-link">{page.number + 1}</span>
          </li>
          <li className={`page-item ${page.last ? 'disabled' : ''}`}>
            <button className="page-link" onClick={() => onPageChange(page.number + 1)}>Próxima</button>
          </li>
        </ul>
      </nav>
    </div>
  )
}

export default Pagination;