import React from 'react';
import './workbench.css';
import $ from 'jquery';
import LoginPage from '../app/loginPage';
import { connect } from 'react-redux';
import Header from '../component/header'

class workbench extends React.Component{

    constructor(){
        super()
        this.state={
            userName:"palwithpen"
        }
    }

        render() {
            switch(this.props.currentScreen){
                case "LoginPage":
                    return(
                <div className={"login-grid-container"}>
                    <div className = {"red-box"}><LoginPage/></div>
                    <div className = {"grey-box"}></div>
                </div>)

                case "HomeScreen":
                    return(
                        <div className={"home-screen-conatiner"}>
                            <div className={"header-conatiner"}><Header/></div>
                            <div className={"body-conatiner"}>
                                <div></div>
                                <div></div>
                                <div></div>
                            </div>
                        </div>
                    )

                default : return(<div>Page Not Found</div>)
            }
          }
        }
    
const mapStateToProps = state => {
    return{
        currentScreen:state.currentScreen
    }
}

const mapDispatchToProps = dispatch=>({

})

export default connect(mapStateToProps,mapDispatchToProps) (workbench)