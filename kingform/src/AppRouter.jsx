import React from "react";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import './AppRoute.css';
import Form from "./Form";
import Login from "./Login";
import App from "./App";
import Admin from "./Admin";

// const AppRouter = (username, url) => (
//   <Router>
//       <div>
//       {(() => {
//         if(username != null){
//           username = "Guest";
//            url = "/Form";
//         }
//         }
//       )()}
//         <div class="topnav"> 
//             <Link to ="/"></Link>  

//             <Link to="/App/">Home</Link>
            
//             <Link to="/Form/">Signup</Link>
            
//             <Link to="/Login/">Login</Link>
//             <Link to={url}>{username}</Link>
//         </div>
        
//         <Route exact path="/" component={Login} />
//         <Route path="/App" component={App} />
//         <Route path="/Form/" component={Form} />
//         <Route path="/Login/" component={Login} />
//         <Route Path="/Admin" component={Admin} />
//       </div>
//   </Router>
// );

class AppRouter extends React.Component{
  constructor(username, url){
    super(); 
    this.state = {
      username: "",
      url: "" 
    }
  }
  render(){
    return(
      <div>
        <Router>
         <div>
          {(() => {
            if(this.state.username == ""){
              this.state.username = "Guest";
              this.state.url = "/Form";
            }
            }
          )()}
            <div class="topnav"> 
                <Link to ="/"></Link>  

                <Link to="/App/">Home</Link>
                
                <Link to="/Form/">Signup</Link>
                
                <Link to="/Login/">Login</Link>
                <Link to={this.state.url}>{this.props.data}</Link>
            </div>
            
            <Route exact path="/" component={Login} />
            <Route path="/App" component={App} />
            <Route path="/Form/" component={Form} />
            <Route path="/Login/" component={Login} />
            <Route Path="/Admin" component={Admin} />
          </div>
      </Router>
      </div>
    );
  }
}
export default AppRouter;