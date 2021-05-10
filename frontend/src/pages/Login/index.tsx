import 'assets/css/form.css';
import Footer from 'components/Footer';
import NavBar from 'components/NavBar';
import { Link } from 'react-router-dom';

const Login = () => {
  return (
    <>
      <NavBar />
      <main className="form-signin">
        <form>
          <h1 className="h3 mb-3 fw-normal">Por favor, faça login</h1>

          <div className="form-floating">
            <input type="email" className="form-control" id="floatingInput" placeholder="nome@algamoney.com" />
            <label htmlFor="floatingInput">Email</label>
          </div>
          <div className="form-floating">
            <input type="password" className="form-control" id="floatingPassword" placeholder="Senha" />
            <label htmlFor="floatingPassword">Senha</label>
          </div>

          <Link to="/home"><button className="btn btn-primary btn-lg" type="submit">Entrar</button></Link>
        </form>
      </main>
      <Footer />
    </>
  );
}

export default Login;