import React, { Component } from 'react';
import {Redirect } from 'react-router';
import axios from 'axios'

class Form extends Component {
    constructor() {
        super();

        this.state = {
            data:
                [
                    {
                        "id": "firstName",
                        "value": "First Name",
                        "type": "text"
                    },
                    {
                        "id": "lastName",
                        "value": "Last Name",
                        "type": "text"
                    },
                    {
                        "id": "email",
                        "value": "Email Address",
                        "type": "email"
                    },
                    {
                        "id": "userName",
                        "value": "Username",
                        "type": "text"
                    },
                    {
                        "id": "dateOfBirth",
                        "value": "Date of Birth",
                        "type": "date"
                    },
                    {
                        "id": "password",
                        "value": "Password",
                        "type": "password"
                    },
                    {
                        "id": "confirmPassword",
                        "value": "Confirm Password",
                        "type": "password"
                    }
                ],
            currentPage: "home"
        }
    }
    validateForm = (event) => {
        event.preventDefault();
        
        let firstName = document.getElementById('firstName').value
        let lastName = document.getElementById('lastName').value
        let emailAddress = document.getElementById('email').value
        let dateOfBirth = document.getElementById('dateOfBirth').value
        let userName = document.getElementById('userName').value
        let password = document.getElementById('password').value
        let confirmPassword = document.getElementById('confirmPassword').value

        if ((firstName === null) || (firstName === "")) {
            alert("Please enter your first name");
        }
        else if ((lastName === null) || (lastName === "")) {
            alert("Please enter your last name");
        }
        else if ((emailAddress === null) || (emailAddress === "")) {
            alert("Please enter your email address");
        }
        else if ((userName === null) || (userName === "")) {
            alert("Please enter your username");
        }
        else if ((dateOfBirth === null) || (dateOfBirth === "")) {
            alert("Please enter your date of birth");
        }
        else if ((password === null) || (password === "")) {
            alert("Please enter a password");
        }
        else if ((confirmPassword === null) || (confirmPassword === "")) {
            alert("Please enter a password");
        }
        else if ((password.length < 7) || (confirmPassword.length < 7)) {
            alert("The password must have more than 7 characters. Please enter a valid password")
        }
        else if (password != confirmPassword) {
            alert("Both passwords must be the same");
        }
        else {
            this.validateUserName(userName);
        }
    };
    validateUserName = (userName) => {
        axios.get("http://localhost:8080/formtest/checkUserNameOrEmail?data="+userName+"&type=Username")
        .then(response => response.data)
        .then(data => {
            switch (data.code) {
                case "Username is available":
                    this.validateEmailAddress(document.getElementById('email').value);
                    break;
                default:
                    alert(data.code);
            }
        })
        .catch(function (error) {
            alert("Error: "+error);
        })
    };
    validateEmailAddress = (emailAddress) => {
        axios.get("http://localhost:8080/formtest/checkUserNameOrEmail?data="+emailAddress+"&type=Email")
        .then(response => response.data)
        .then(data => {
            switch (data.code) {
                case "Email is available":
                    this.createUserAccount();
                    break;
                default:
                    alert(data.code);
            }
        })
        .catch(function (error) {
            alert("Error: "+error);
        })
    }
    createUserAccount = () => {
        fetch("http://localhost:8080/formtest/add?userName="+document.getElementById('userName').value+"&firstName="+document.getElementById('firstName').value+"&lastName="+document.getElementById('lastName').value
        +"&email="+document.getElementById('email').value+"&dateOfBirth="+document.getElementById('dateOfBirth').value+"&password="+document.getElementById('password').value+"")
            .then(response => response.json())
            .then(data => {
                switch (data.code) {
                    case "Saved":
                        alert("Registration was successful. Thank you")
                        this.setState({ currentPage: "Login" });
                        break;
                    default:
                        alert(data.code);
                }
            })
            .catch(function (error) {
                alert("Error: "+error);
            })
    }
    render() {
        if (this.state.currentPage === "Login") {
            return <Redirect to="/Login" />;
        }
        return (
            <div align="center" class="App-header">
                <form>
                    <fieldset>
                        <legend><h1>Registration</h1></legend>
                        <table >
                            <tr>
                                <td>
                                    {this.state.data.map((rowData, i) => <FieldName data={rowData} />)}
                                </td>
                                <td>
                                    {this.state.data.map((rowData, i) => <FieldType data={rowData} />)}
                                </td>
                            </tr>
                            <tr align="center">
                                <td><input type="Reset" value="Clear" /></td>
                                <td><input type="Submit" value="Submit" onClick={this.validateForm} /></td>
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
                <tr><label class="labelcolor">{this.props.data.value}</label></tr><br />
            </div>
        );
    }
}

class FieldType extends Component {
    render() {
        return (
            <div>
                <tr>
                    <input id={this.props.data.id} type={this.props.data.type}></input>
                </tr><br />
            </div>
        );
    }
}

export default Form;