import InitialState from './initialState';

const reducer =(state = InitialState, action) =>{
    switch(action.type){
        
        case 'NAVIGATE':
            return{
                ...state,
                currentScreen:action.screenName,
            }

        default:{
        return {
            ...state
        }}
    }
}

export default reducer;
