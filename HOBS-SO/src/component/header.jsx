import { red } from '@material-ui/core/colors';
import React from 'react';
import './header.css';
import { IconContext } from 'react-icons';
import {AiOutlineLogout} from 'react-icons/ai'
import {navigateScene} from '../store/action'
import { connect } from 'react-redux';

class header extends React.Component{
    constructor(){
        super();
        this.state={

        }
    }

    render(){
        return(
            <div className={"main-header header-grid-container"}>
                <div className={"icon-conatiner"}></div>
                <div className={"header-body-conatiner"}></div>
                <div className={"header-left-over"}>
                    <a className={"logout-button"} onClick ={()=>this.props.navigateScene("LoginPage")}>
                        <IconContext.Provider value ={{className : "svg-style"}}>
                            <AiOutlineLogout/>
                        </IconContext.Provider></a>
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
export default connect(mapStateToProps,mapDispatchToProps) (header);