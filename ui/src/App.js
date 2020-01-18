import React, {Component} from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {
  state = {
      isLoading: true,
      recipes: []
  };

  async componentDidMount() {
      console.log("fetching from web service");
      const response = await fetch('http://sanjudf02:8080/api/v1/recipes');
      const body = await response.json();
      console.log(body);
      this.setState({recipes: body.data, isLoading: false});
  };

  render() {
      const {recipes, isLoading} = this.state;

      if (isLoading) {
          return <p>Loading...</p>
      }
      return (
        <div className="App">
          <header className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <div className="App-Intro">
                <h2>Recipes List</h2>
                {recipes.map(recipe =>
                    <div key={recipe.id}>
                        {recipe.name}
                    </div>
                )}
            </div>
          </header>
        </div>
      );
  }

}

export default App;
