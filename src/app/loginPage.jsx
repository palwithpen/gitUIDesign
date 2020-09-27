import React from 'react';
import './PageDefine.css';
import Header from '../component/header';
import $ from 'jquery';
import { TextField } from '@fluentui/react/lib/TextField';
import MaterialTextField from '@material-ui/core/TextField';
import { DefaultButton, PrimaryButton, Button} from '@fluentui/react/lib/Button';
import {navigateScene} from '../store/action'
import swal from 'sweetalert';
import { connect } from 'react-redux';
import { HiOutlineUserAdd } from 'react-icons/hi';

class LoginPage extends React.Component{
    constructor(props){
        super(props);
        this.state={
            loginParams:{
                userName:"",
                passKey:""
            },
            dump:""
        }
    }


    componentDidMount(){
        var $this = this;
        
        $.ajax({
            type: "GET",
            //dataType: "json",
            url: 'http://localhost:4545/palwithpen/hey',
            //contentType: "text/plain;charset=UTF-8",
            success:function(data){
                console.log("Connected")
            },
            error:function(data){
                console.log("Error")
                swal("Oops!", "Please Check Your Connectivity", "error");
            }})
    }

    login(){
        var $this = this;
        
        $.ajax({
            type: "GET",
            dataType: "json",
            url: 'http://localhost:4545/palwithpen/checkUser/'+$this.state.loginParams.userName,
            contentType: "application/json",
            success:function(data){
                if(data == null){
                    swal("Sorry!", "Sorry Username not found!", "error");
                }
                else{
                    console.log("aaya data")
                    if(data.userId === $this.state.loginParams.userName && data.passKey === $this.state.loginParams.passKey){
                        swal("Logged In!", "Logged In Successfully!", "success");
                        $this.props.navigateScene("HomeScreen")
                    }
                    else{
                        console.log("Sorry login failed")
                        swal("Oops!", "Sorry Username or Password is incorrect!", "error");
                    }
                }
            },
            error:function(data){
                swal("Oops!", "Please Enter a Username", "error");
            }})}

    render(){
        return(
        <div className = {"login-component-wrapper"}>
            <div className={"login-header-container"}>LOGIN</div>
            <div className={"login-textfield"}>
                <MaterialTextField 
                id="outlined-basic" 
                label="Username" 
                variant="outlined" 
                value={this.state.loginParams.userName}
                onChange={(event)=>this.setState({loginParams:{...this.state.loginParams,userName:event.target.value}})}/>
            </div>
            <div className={"login-textfield"}>
                <MaterialTextField 
                    id="outlined-password-input"
                    label="Password"
                    type="password"
                    autoComplete="current-password"
                    variant="outlined"
                    value={this.state.loginParams.passKey}
                    onChange={(event)=>this.setState({loginParams:{...this.state.loginParams,passKey:event.target.value}})}/>
            </div>
            <div className={"button-container"}>
                <a className={"custom-button"}  onClick={()=>this.login()}>Click here to Login</a>
            </div>
        </div>
        )
    }
}

const mapStateToProps = state =>{
    return{

    }

}

const mapDispatchToProps = dispatch =>({
    navigateScene:(screenName)=>dispatch(navigateScene(screenName)),
})
export default connect(mapStateToProps,mapDispatchToProps) (LoginPage);