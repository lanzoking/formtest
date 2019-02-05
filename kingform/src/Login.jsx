import React, {Component} from 'react';
import {Redirect } from 'react-router';
import axios from 'axios';
import AppRouter from './AppRouter';

class Login extends Component{
    constructor(){
        super();
        this.state = {
            data:
                [
                    {
                        "id":"userName",
                        "value":"Username",
                        "type":"text"
                    },
                    {
                        "id":"password",
                        "value":"Password",
                        "type":"password"
                    }
                ],
            currentPage: "home"
        }
    }
    validateForm = (event) => {
        event.preventDefault();

        let userName = document.getElementById('userName').value;
        let password = document.getElementById('password').value;

        if ((userName === null) || (userName === "")) {
            alert("Please enter a valid username");
        }
        else if ((password === null) || (password === "")) {
            alert("Please enter a valid password");
        }
        else if (password.length < 7) {
            alert("Password must contain more than 7 characters");
        }
        else {
            this.validateCredentials(userName, password);
        }
    };
    validateCredentials = (userName, password) => {
        axios.get("http://localhost:8080/formtest/validateUserCredentials?userName="+userName+"&password="+password)
        .then(response => response.data)
        .then(data => {
            switch (data.code) {
                case "Login successful":
                    alert(data.code);
                    // return <AppRouter data={userName}/>
                    this.setState({ currentPage: "App" });
                break;
                default:
                    alert(data.code)
            }
        })
        .catch(function (error) {
            alert("Error: "+error);
        })
    };
    render(){
        if (this.state.currentPage === "App") {
            return <Redirect to="/App" />;
        }
        return(
            <div align="center" class="App-header">
                <form>
                    <fieldset>
                        <legend><h1>Login</h1></legend>
                        <table>
                            <tr>
                                <td>
                                    {this.state.data.map((rowData, i) => <FieldName data = {rowData}/>)}
                                </td>
                                <td>
                                    {this.state.data.map((rowData, i) => <FieldType data = {rowData} />)}
                                </td>
                            </tr>
                            <tr align="center">
                                <td><input type="Reset" value="Cancel"/></td>
                                <td><input type="Submit" value="Login" onClick={this.validateForm}/></td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
            </div>
        );
    }
}

class FieldName extends Component {
    render() {
        return (
            <div>
                <tr><label class="labelcolor">{this.props.data.value}</label></tr><br/>
           </div>
        );
    }
}

class FieldType extends Component {
    render() {
        return (
            <div>
                <tr><input id={this.props.data.id} type={this.props.data.type}></input></tr><br/>
            </div>
        );
    }
}
export default Login;