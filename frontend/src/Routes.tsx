import Lancamentos from 'pages/Lancamentos';
import Home from 'pages/Home';
import { BrowserRouter, Route, Switch} from 'react-router-dom';
import Login from 'pages/Login';

const Routes = () => {
  return (
    <BrowserRouter>
      <Switch>
        <Route path="/" exact> <Login/> </Route>
        <Route path="/home"> <Home/> </Route>
        <Route path="/lancamentos"> <Lancamentos/> </Route>

      </Switch>
    
    </BrowserRouter>
  );
}

export default Routes;