import React, { Component } from 'react';
import logo from './logo.svg';
import viking from './viking.gif';
import goku from './goku.gif';
import './App.css';

class App extends Component {
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          {/* <img class = "image" src={viking} alt="viking" /> */}
          <img class = "image" src={goku} alt="goku" />
        </header>
      </div>
    );
  }
} 

export default App;
