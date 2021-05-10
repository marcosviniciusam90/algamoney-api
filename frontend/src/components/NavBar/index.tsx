import ImgAlgaWorks from 'assets/img/algaworks.png';
import { Link } from 'react-router-dom';

const NavBar = () => {
  return (
    <div className="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-primary border-bottom shadow-sm">
      <div className="container">
        <nav className="my-2 my-md-0 mr-md-3">
          <Link to="/"><img src={ImgAlgaWorks} alt="AlgaMoney" /></Link>
        </nav>
      </div>
    </div>
  );
}

export default NavBar;
