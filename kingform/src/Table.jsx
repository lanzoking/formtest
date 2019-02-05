import React from 'react';
import './App.css';

class Table extends React.Component{
    render(){
        return(
            <div align="center">
                
                <form>
                    <fieldset>
                    <legend><Header/></legend>
                        <table>
                            <tr>
                                <td><label>First Name:</label></td>
                                <td><input type="text" /></td>
                            </tr>
                            <tr>
                                <td><label>Last Name:</label></td>
                                <td><input type="text" /></td>
                            </tr>
                            <tr>
                                <td><label>Email Address:</label></td>
                                <td><input type="text" /></td>
                            </tr>
                            <tr>
                                <td><label>Username:</label></td>
                                <td><input type="text" /></td>
                            </tr>
                            <tr>
                                <td><label>Password:</label></td>
                                <td><input type="password" /></td>
                            </tr>
                            <tr>
                                <td><label>Confirm Password:</label></td>
                                <td><input type="password" /></td>
                            </tr>
                            <tr>
                                <td><label><input type="Reset" value="Clear" /></label></td>
                                <td><input type="Submit" value="Submit" /></td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
            </div>
        );
    }
}

class Header extends React.Component{
    render(){
        return(
            <h1 align = "center">React JS</h1>
        );
    }
}
export default Table;