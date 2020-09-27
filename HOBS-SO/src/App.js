import React from 'react';
import './App.css';
import Workbench from './scenes/workbench';
import {Provider} from 'react-redux';
import store from './store/store'

class App extends React.Component{

  render(){
    return(
      <Provider store = {store}>
        <Workbench/>
      </Provider>
    )
  }
}

export default App;
