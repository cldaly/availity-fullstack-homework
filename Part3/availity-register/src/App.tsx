import { Component } from 'react';
import { Switch, Route } from 'react-router-dom';
import { Logo } from './components/logo/logo.component';
import { Intro } from './components/intro/intro.component';
import { Register } from './components/register/register.component';
import './App.css';

export default class App extends Component {
  render() {
    return (
      <div className="app">
        <Logo />
        <div className="main">
          <h1 className='title'>Welcome to Availity</h1>
          <Switch>
            <Route exact path='/'>
              <Intro />
            </Route>
            <Route path='/register'>
              <Register />
            </Route>
          </Switch>
        </div>
      </div>
    );
  }
}
